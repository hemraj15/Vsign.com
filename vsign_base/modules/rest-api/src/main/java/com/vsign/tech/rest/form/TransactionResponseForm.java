/**
 * 
 */
package com.vsign.tech.rest.form;

/**
 * @author Hemraj
 *
 */
public class TransactionResponseForm {
	private Long transactionNo;
	//private String custEmailId;
//	private String custFirstName;
	//private String custLastName;
	//@Temporal(TemporalType.DATE)
	//private String transactonDate;
	//protected Date transactionUpdateDate;
	private Long paymentGatewayTrxId;
	private Double amount;
	private String bankRefNum;
	private String bankCode;
	//private String cardNumber;
	//private String cardType;
	private Double discount;
	private Long payYouMoneyId;
	private String errorMessage;
	private String errorCode;
	//private String successMessage;
	//private String successCode;
	//private Long transactionOrderId;
	private String trxMessage;
	private String trxStatus;
	//private String trxStatusCode;
	private Double netAmountPaid;
	private String paymentMode;
	private String custTrxAction;
	/**
	 * @return the transactionNo
	 */
	public Long getTransactionNo() {
		return transactionNo;
	}
	/**
	 * @param transactionNo the transactionNo to set
	 */
	public void setTransactionNo(Long transactionNo) {
		this.transactionNo = transactionNo;
	}
	/**
	 * @return the custEmailId
	 */
	/*public String getCustEmailId() {
		return custEmailId;
	}
	*//**
	 * @param custEmailId the custEmailId to set
	 *//*
	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	*//**
	 * @return the custFirstName
	 *//*
	public String getCustFirstName() {
		return custFirstName;
	}
	*//**
	 * @param custFirstName the custFirstName to set
	 *//*
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	*//**
	 * @return the custLastName
	 *//*
	public String getCustLastName() {
		return custLastName;
	}
	*//**
	 * @param custLastName the custLastName to set
	 *//*
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	*//**
	 * @return the transactonDate
	 */
	/*public String getTransactonDate() {
		return transactonDate;
	}
	*//**
	 * @param transactonDate the transactonDate to set
	 *//*
	public void setTransactonDate(String transactonDate) {
		this.transactonDate = transactonDate;
	}*/
	/**
	 * @return the paymentGatewayTrxId
	 */
	public Long getPaymentGatewayTrxId() {
		return paymentGatewayTrxId;
	}
	/**
	 * @param paymentGatewayTrxId the paymentGatewayTrxId to set
	 */
	public void setPaymentGatewayTrxId(Long paymentGatewayTrxId) {
		this.paymentGatewayTrxId = paymentGatewayTrxId;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the bankRefNum
	 */
	public String getBankRefNum() {
		return bankRefNum;
	}
	/**
	 * @param bankRefNum the bankRefNum to set
	 */
	public void setBankRefNum(String bankRefNum) {
		this.bankRefNum = bankRefNum;
	}
	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	/**
	 * @return the payYouMoneyId
	 */
	public Long getPayYouMoneyId() {
		return payYouMoneyId;
	}
	/**
	 * @param payYouMoneyId the payYouMoneyId to set
	 */
	public void setPayYouMoneyId(Long payYouMoneyId) {
		this.payYouMoneyId = payYouMoneyId;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
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
	 * @return the transactionOrderId
	 */
	/*public Long getTransactionOrderId() {
		return transactionOrderId;
	}
	*//**
	 * @param transactionOrderId the transactionOrderId to set
	 *//*
	public void setTransactionOrderId(Long transactionOrderId) {
		this.transactionOrderId = transactionOrderId;
	}*/
	
	
}
