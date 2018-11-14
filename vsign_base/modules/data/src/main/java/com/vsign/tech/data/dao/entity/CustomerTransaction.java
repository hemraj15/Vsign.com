/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Hemraj
 *
 */
@Entity
public class CustomerTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	
	private Long transactionNo;
	private String custEmailId;
	private String custFirstName;
	private String custLastName;
	private Date transactonDate;
	protected Date transactionUpdateDate;
	
	private Long paymentGatewayTrxId;
	private Double amountToBePaid;
	//private String bankRefNum;
	//private String bankCode;
	//private String cardNumber;
	//private String cardType;
	private Double discount;
	private Double totalAmount;
	//private Long payYouMoneyId;
	private String errorMessage;
	private String errorCode;
	//private String successMessage;
	//private String successCode;
	private Long transactionOrderId;
	private String trxMessage;
	private String trxStatus;
	//private String trxStatusCode;
	private Double netAmountPaid;
	private String paymentMode;
	private String custTrxAction;

	private Double surcharge ;
	private String prod;
	private Long bank_txn;
	private String f_code;
	private String clientcode;
	private String bankName;
	//NB
	private String discriminator;
	private String CardNumber;
	private String signature;
	
	
	
	
	
	
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public Double getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}
	public Long getBank_txn() {
		return bank_txn;
	}
	public void setBank_txn(Long bank_txn) {
		this.bank_txn = bank_txn;
	}
	public String getF_code() {
		return f_code;
	}
	public void setF_code(String f_code) {
		this.f_code = f_code;
	}
	public String getClientcode() {
		return clientcode;
	}
	public void setClientcode(String clientcode) {
		this.clientcode = clientcode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getDiscriminator() {
		return discriminator;
	}
	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/*public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}*/
	public Long getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(Long transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getCustEmailId() {
		return custEmailId;
	}
	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	public String getCustFirstName() {
		return custFirstName;
	}
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	public String getCustLastName() {
		return custLastName;
	}
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	@Temporal(TemporalType.DATE)
	@PrePersist
	public Date getTransactonDate() {
		return transactonDate == null ? new Date() : transactonDate ;
	}
	public void setTransactonDate(Date transactonDate) {
		this.transactonDate = transactonDate;
	}
	
	@Temporal(TemporalType.DATE)
	@PrePersist
	@PostUpdate
	public Date getTransactionUpdateDate() {
		return transactionUpdateDate == null ? new Date() : transactionUpdateDate;
	}
	public void setTransactionUpdateDate(Date transactionUpdateDate) {
		this.transactionUpdateDate = transactionUpdateDate;
	}
	public Long getPaymentGatewayTrxId() {
		return paymentGatewayTrxId;
	}
	public void setPaymentGatewayTrxId(Long paymentGatewayTrxId) {
		this.paymentGatewayTrxId = paymentGatewayTrxId;
	}
	public Double getAmountToBePaid() {
		return amountToBePaid;
	}
	public void setAmountToBePaid(Double amount) {
		this.amountToBePaid = amount;
	}
	/*public String getBankRefNum() {
		return bankRefNum;
	}
	public void setBankRefNum(String string) {
		this.bankRefNum = string;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}*/
	/*
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	*/public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	/*public Long getPayYouMoneyId() {
		return payYouMoneyId;
	}
	public void setPayYouMoneyId(Long payYouMoneyId) {
		this.payYouMoneyId = payYouMoneyId;
	}*/
	/**
	 * @return the trxMessage
	 */
	public String getTrxMessage() {
		return trxMessage;
	}
	/**
	 * @param trxMessage the trxMessage to set
	 */
	public void setTrxMessage(String trxMessage) {
		this.trxMessage = trxMessage;
	}
	/**
	 * @return the trxStatus
	 */
	public String getTrxStatus() {
		return trxStatus;
	}
	/**
	 * @param trxStatus the trxStatus to set
	 */
	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}
	/**
	 * @return the trxStatusCode
	 */
	/*public String getTrxStatusCode() {
		return trxStatusCode;
	}
	*//**
	 * @param trxStatusCode the trxStatusCode to set
	 *//*
	public void setTrxStatusCode(String trxStatusCode) {
		this.trxStatusCode = trxStatusCode;
	}*/
	/**
	 * @return the netAmountPaid
	 */
	public Double getNetAmountPaid() {
		return netAmountPaid;
	}
	/**
	 * @param netAmountPaid the netAmountPaid to set
	 */
	public void setNetAmountPaid(Double netAmountPaid) {
		this.netAmountPaid = netAmountPaid;
	}
	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}
	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	/**
	 * @return the custTrxAction
	 */
	public String getCustTrxAction() {
		return custTrxAction;
	}
	/**
	 * @param custTrxAction the custTrxAction to set
	 */
	public void setCustTrxAction(String custTrxAction) {
		this.custTrxAction = custTrxAction;
	}
	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the transactionOrderId
	 */
	public Long getTransactionOrderId() {
		return transactionOrderId;
	}
	/**
	 * @param transactionOrderId the transactionOrderId to set
	 */
	public void setTransactionOrderId(Long transactionOrderId) {
		this.transactionOrderId = transactionOrderId;
	}
	
	
	

}
