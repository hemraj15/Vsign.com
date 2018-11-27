/**
 * 
 */
package com.vsign.tech.rest.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.vsign.tech.data.dao.OrderDao;
import com.vsign.tech.data.dao.PaymentDao;
import com.vsign.tech.data.dao.TransacationOrderDao;
import com.vsign.tech.data.dao.entity.CustOrder;
import com.vsign.tech.data.dao.entity.Customer;
import com.vsign.tech.data.dao.entity.CustomerTransaction;
import com.vsign.tech.data.dao.entity.TransacationOrder;
import com.vsign.tech.data.dto.PaymentRequestDto;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.rest.constant.CommonStatus;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.constant.PaymentConstants;
import com.vsign.tech.rest.exception.CustomerOrderNotFound;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.InvalidTransactionException;
import com.vsign.tech.rest.exception.OrderStatusException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.PaymentResponseForm;
import com.vsign.tech.rest.form.TransacationOrderForm;
import com.vsign.tech.rest.form.TransactionResponseForm;
import com.vsign.tech.rest.utils.SignatureConst;
import com.vsign.tech.rest.utils.SignatureGenerate;

/**
 * @author Hemraj
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	private Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private OrderDao ordDao;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private TransacationOrderDao trxOrderDao;

	

	@Transactional 
	public Map<String, Object> initiateCartTrx(Long trxOrderId)
			throws DatabaseException, InstanceNotFoundException, OrderStatusException {

		Map<String, Object> map = new HashMap<>();
		CustOrder ord = new CustOrder();
		Customer cust = null;
		Long trxId = null;
		TransacationOrder trxOrder = null;
		CustomerTransaction trx = new CustomerTransaction();
		try {

			trxOrder = getTrxOrderByTrxOrderId(trxOrderId);
			List<CustOrder> ordList = new ArrayList<>();

			if (trxOrder != null) {

				ordList = trxOrder.getOrders();

				if (!CollectionUtils.isEmpty(ordList)) {

					ord = ordList.get(0);

					if (ord != null) {

						cust = ord.getCustomer();

						if (cust != null) {

							// map.put("merchantKey",
							// PaymentConstants.merchantKey);
							// map.put("merchantId",
							// PaymentConstants.merchantId);
							// map.put("authHeader",
							// PaymentConstants.authHeader);
							// map.put("merchantSalt",
							// PaymentConstants.merchantSalt);
							map.put("customerEmail", cust.getEmail());
							map.put("customerId", cust.getId());
							map.put("customerFirstName", cust.getFirstName());
							map.put("custLastName", cust.getLastName());
							map.put("custContactNum", cust.getContactNumber());
							map.put("orderPrice", trxOrder.getOrderValue());
							map.put("transactionOrderId", trxOrderId);

							trx.setAmountToBePaid(trxOrder.getOrderValue());
							// trx.setNetAmountPaid(ord.getPaidAmount());
							trx.setCustEmailId(cust.getEmail());
							trx.setCustFirstName(cust.getFirstName());
							trx.setCustLastName(cust.getLastName());
							trx.setTransactionOrderId(trxOrderId);
							trx.setTrxStatus(CommonStatus.INITIATED.toString());
							trx.setDiscount(trxOrder.getDiscAmount());

							LOGGER.info("saving transaction for order id ::" + trxOrderId);
							trxId = paymentDao.save(trx);
							LOGGER.info("saved transaction for order id ::" + trxOrderId
									+ " generated transaction id is " + trxId);

							map.put("tansactionId", trxId);

						} else {

							LOGGER.info("Customer not found for the request order id " + trxOrderId);
							throw new UserNotFoundException("customer not found for the request order id",
									ErrorCodes.USER_NOT_FOUND_ERROR);
						}

					}
				} else {

					LOGGER.info("There are no orders for the transaction CustOrder id ::" + trxOrderId);
				}
			} else {

				LOGGER.info("Transaction CustOrder is null to initiate transaction ");
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while initiating transaction for order " + trxOrderId + " in database", e);
			throw new DatabaseException("Error occured while initiating transaction", ErrorCodes.DATABASE_ERROR);
		}

		return map;
	}

	/**
	 * @param trxOrderId
	 * 
	 * 
	 */
	private TransacationOrder getTrxOrderByTrxOrderId(Long trxOrderId) throws InstanceNotFoundException {

		return trxOrderDao.find(trxOrderId);
	}

	private CustOrder getOrderByOrderId(Long orderId)
			throws DatabaseException, InstanceNotFoundException, OrderStatusException {
		CustOrder ord = null;
		try {

			ord = ordDao.find(orderId);

			if (ord != null) {

				if (!ord.getStatus().equalsIgnoreCase(CommonStatus.INITIATED.toString())) {

					throw new OrderStatusException("CustOrder status in not initiated ",
							ErrorCodes.INVALID_ORDER_STATUS);

				}
			}

		} catch (InstanceNotFoundException e) {

			LOGGER.info("fetch order to initiate transaction >> instance not found for order id ::" + orderId);

			throw new InstanceNotFoundException("CustOrder Not Found to initiate transaction",
					ErrorCodes.ORDER_NOT_FOUND_ERROR);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching order for initiating transaction for order" + orderId
					+ " in database", e);
			throw new DatabaseException("Error occured while fetching order from database", ErrorCodes.DATABASE_ERROR);
		}
		return ord;
	}

	@Override
	@Transactional
	public Map<String, Object> transactionComplete(PaymentResponseForm paymentRespForm)
			throws DatabaseException, InvalidTransactionException, InstanceNotFoundException {
		Long trxOrderId = null;
		Map<String, Object> map =new HashMap<>();
		CustomerTransaction trxObj = new CustomerTransaction();
		try {

			trxObj = paymentDao.find(paymentRespForm.getMer_txn());

			if (trxObj != null) {

				LOGGER.info("initiated transaction found to complete the transaction id ::" + trxObj.getTransactionNo());
				// trxObj.setBankCode(completTrxForm.getBankCode());
				// trxObj.setBankRefNum(completTrxForm.getBankRefNum());
				String f_code =paymentRespForm.getF_code();
				
				trxObj.setErrorCode( f_code !=null && f_code.equalsIgnoreCase("F") ? "F" : " " );
				//trxObj.setErrorMessage(completTrxForm.getErrorMessage());

				// SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-DD
				// HH:MM:SS");

				// Date
				// trxDate=dateFormat.parse(completTrxForm.getTransactonDate());
				// Date
				// trxDate=DateTimeUtils.getStringFromDate(completTrxForm.getTransactonDate(),
				// "YYYY-MM-DD HH:MM:SS");
				// trxObj.setTransactonDate(trxDate);
				// trxObj.setDiscount(completTrxForm.getDiscount());
				//trxObj.setCustTrxAction(completTrxForm.getCustTrxAction());
				// trxObj.setNetAmountPaid(completTrxForm.getNetAmountPaid());
				trxObj.setPaymentGatewayTrxId(paymentRespForm.getMmp_txn());
				trxObj.setPaymentMode(paymentRespForm.getDiscriminator());
				trxObj.setTrxStatus(f_code !=null && f_code.equalsIgnoreCase("OK") ? "Ok" : " ");
				// trxObj.setPayYouMoneyId(completTrxForm.getPayYouMoneyId());
				trxObj.setTrxMessage(paymentRespForm.getDesc());
				trxObj.setNetAmountPaid(paymentRespForm.getAmt());
				trxObj.setBank_txn(paymentRespForm.getBank_txn());
				trxObj.setBankName(paymentRespForm.getBank_name());
				trxObj.setCardNumber(paymentRespForm.getCardNumber());
				trxObj.setClientcode(paymentRespForm.getClientcode());
				trxObj.setDiscriminator(paymentRespForm.getDiscriminator());
				trxObj.setTransactionUpdateDate(new Date());
				trxObj.setF_code(paymentRespForm.getF_code());
				trxObj.setProd(paymentRespForm.getProd());
				trxObj.setSignature(paymentRespForm.getSignature());
				trxObj.setSurcharge(paymentRespForm.getSurcharge());
				trxObj.setTrxStatus(CommonStatus.COMPLETE.toString());;

				paymentDao.saveOrUpdate(trxObj);
				trxOrderId = trxObj.getTransactionOrderId();
				map.put("transactionId", trxObj.getTransactionNo());
				map.put("transactioOrderId", trxOrderId);

				LOGGER.info("Transaction updated successfully :: " + trxOrderId);
			} else {

				LOGGER.info("Transaction Object is null for request trx no " +  paymentRespForm.getMer_txn());
				throw new InvalidTransactionException("Error occured while fetching transaction from database",
						ErrorCodes.DATABASE_ERROR);
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while updating transaction for transaction ::"
					+ paymentRespForm.getMer_txn() + " in database", e);
			throw new DatabaseException("Error occured while fetching transaction from database",
					ErrorCodes.DATABASE_ERROR);
		}
		LOGGER.error("Returning transaction order id ::" + trxOrderId);
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> initiateCartTransaction(TransacationOrderForm form) throws DatabaseException,
			UserNotFoundException, OrderStatusException, InstanceNotFoundException, EmptyListException {

		TransacationOrder trxOrder = new TransacationOrder();
		List<CustOrder> custOrders = new ArrayList<>();
		List<Long> orderList = new ArrayList<>();
		Long trxOrderId;
		CustOrder ord;
		Double amount = 0.0;
		Double discAmoiunt = 0.0;
		Map<String, Object> map = new HashMap<>();
		try {

			if (form != null && form.getOrderIdList() != null) {

				LOGGER.info("request form of ord id not null and order list is -" + form.getOrderIdList());
				orderList = form.getOrderIdList();

				Iterator<Long> itr = orderList.iterator();

				while (itr.hasNext()) {
					System.out.println("Hi got the Order ");
					ord = new CustOrder();
					ord = getOrderByOrderId(itr.next());
					amount = ord.getPaidAmount();
					discAmoiunt = ord.getDiscountAmount();
					custOrders.add(ord);
				}

				trxOrder.setOrders(custOrders);
				trxOrder.setOrderValue(amount);
				trxOrder.setDiscAmount(discAmoiunt);

				trxOrderId = trxOrderDao.save(trxOrder);

				map = initiateCartTrx(trxOrderId);

			} else {

				LOGGER.info("order list is empty");

				throw new EmptyListException("Error Occured while initiating transaction - request order list is empty",
						ErrorCodes.ORDER_LIST_EMPTY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while initiating CustOrder transaction  in database", e);
			throw new DatabaseException("Error occured while initiating CustOrder transaction  in database",
					ErrorCodes.DATABASE_ERROR);
		}

		return map;
	}

	@Override
	@Transactional
	public PaymentRequestDto initiateTransaction(Long orderId)
			throws DatabaseException, UserNotFoundException, OrderStatusException, InstanceNotFoundException {

		Map<String, Object> map = new HashMap<>();
		CustOrder custOrd = new CustOrder();
		Customer cust = null;
		Long trxId = null;
		Long trxOrdId = null;
		TransacationOrder trxOrder = null;
		CustomerTransaction trx = null;

		PaymentRequestDto payDto = null;
		try {

			custOrd = ordDao.find(orderId);

			if (custOrd != null) {
				trxOrder = new TransacationOrder();
				List<CustOrder> custOrder = new ArrayList<>();
				custOrder.add(custOrd);
				trxOrder.setOrders(custOrder);
				trxOrder.setOrderValue(custOrd.getOrderPrice());
				trxOrder.setStatus(CommonStatus.INITIATED.toString());
				trxOrdId = trxOrderDao.save(trxOrder);

				cust = custOrd.getCustomer();
				trx = new CustomerTransaction();
				trx.setAmountToBePaid(custOrd.getOrderPrice());
				trx.setCustEmailId(cust.getEmail());
				trx.setCustFirstName(cust.getFirstName());
				trx.setCustLastName(cust.getLastName());
			
				trx.setTransactionOrderId(trxOrdId);
				trx.setTrxStatus(CommonStatus.INITIATED.toString());
				trx.setTransactonDate(new Date());

				trxId = paymentDao.save(trx);
				LOGGER.error("new transaction id created : "+trxId);
				payDto = new PaymentRequestDto();
				
				payDto.setClientcode(getCustNameBase64Format(cust.getFirstName()));
				payDto.setCustacc(SignatureConst.custacc);

				payDto.setDate(new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(new Date()));
				payDto.setLogin(SignatureConst.login_id);
				payDto.setPass(SignatureConst.Password);
				payDto.setProdid(SignatureConst.prod_id);
				payDto.setRu(SignatureConst.ru);
				DecimalFormat df = new DecimalFormat("####0.00");
			     String amount= df.format(custOrd.getOrderPrice());
			     LOGGER.error("amount after format : "+amount);
				payDto.setSignature(generateRequestSignature(trxId,amount ));
				payDto.setAmt(amount);
				payDto.setTtype(SignatureConst.ttype);
				payDto.setTxncurr(SignatureConst.txncurr);
				payDto.setTxnid(trxId);
				payDto.setUdf1(cust.getFirstName());
				payDto.setUdf2(cust.getEmail());
				payDto.setUdf3(cust.getContactNumber());
				payDto.setUdf3(cust.getContactNumber());
				payDto.setUdf4(cust.getAddress().getHouseNo()+" ,"+cust.getAddress().getStreetNo()+" ,"+cust.getAddress().getArea()+" ,"+cust.getAddress().getPinCode());
				payDto.setMdd("NA");
				
				payDto.setUdf5(111111l);
				
				payDto.setUdf6(0l);
				
				payDto.setUdf9("Vsign Licence Service");
				
				
				
				//map.put("paymentRequestData", payDto);
				

			} else {

				LOGGER.error("Error occured while fetching customer order from data base for orderId : " + orderId);
				throw new CustomerOrderNotFound(
						"Customer order could not be found in data base for order id : " + orderId,
						ErrorCodes.DATABASE_ERROR);
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while initiating CustOrder transaction  in database", e);
			throw new DatabaseException("Error occured while initiating CustOrder transaction  in database",
					ErrorCodes.DATABASE_ERROR);
		}

		return payDto;
	}

	/**
	 * @param trxId
	 * @param amt
	 */
	private String generateRequestSignature(Long trxId, String amt) {
 //reqHashKey, login, pass, ttype, prodid, txnid, amt, txncurr
		
		LOGGER.info("transaction id : "+trxId+" amount : "+amt);
		        String login = SignatureConst.login_id;
				String pass = SignatureConst.Password;
				
				String ttype = SignatureConst.ttype;
				String prodid = SignatureConst.prod_id;
				//Long txnid = 50l;
				//Double amt = 0.0;
				/*DecimalFormat df = new DecimalFormat("####0.00");
			     String amount= df.format(amt);*/
				String txncurr = SignatureConst.txncurr;
				String reqHashKey = SignatureConst.ReqHashKey;
				// login,pass,ttype,prodid,txnid,amt,txncurr
				//getEncodedValueWithSha2(reqHashKey, login,pass,ttype,prodid,txnid,amt,txncurr);
				LOGGER.info(" login = "+login+" pass = "+pass+" ttype = "+ttype+" prodId = "+prodid+" txnid = "+trxId+" amt = "+amt+" txncurr = "+txncurr+" reqHashKey = "+reqHashKey );
				String signature = SignatureGenerate.getEncodedValueWithSha2(reqHashKey, login, pass, ttype, prodid, trxId.toString(), amt, txncurr);
				LOGGER.info("Request signature ::" + signature);
		/*
		String signature = SignatureGenerate.getEncodedValueWithSha2(SignatureConst.ReqHashKey.toString(), SignatureConst.login_id.toString(),
				SignatureConst.Password.toString(), SignatureConst.ttype.toString(), SignatureConst.prod_id.toString(), trxId.toString(),
				amt.toString(), SignatureConst.txncurr.toString());*/
		return signature;
	}

	private String getCustNameBase64Format(String firstName) {
		byte[] bytesEncoded = Base64.encodeBase64(firstName.getBytes());

		return new String(bytesEncoded);
	}

}
