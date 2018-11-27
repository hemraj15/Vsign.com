/**
 * 
 */
package com.vsign.tech.rest.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemraj
 *
 */
//@JsonInclude(Include.NON_EMPTY)
@JsonInclude(value=Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponseForm {
	private Long mmp_txn;
	private Long mer_txn;
	private Double amt;
	private Double surcharge;
	private String prod;
	private String date;
	private Long bank_txn;
	private String f_code;
	private String clientcode;
	private String bank_name;
	private String discriminator;
	private String cardNumber;
	private String signature;
	private String udf1 ;
	private String udf2;
	private Long udf3;
	private String udf4;
	private Long udf5;
	private Long udf6;
	private String udf9;
	private String desc;
	private String ipg_txn_id;
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getMmp_txn() {
		return mmp_txn;
	}
	public void setMmp_txn(Long mmp_txn) {
		this.mmp_txn = mmp_txn;
	}
	public Long getMer_txn() {
		return mer_txn;
	}
	public void setMer_txn(Long mer_txn) {
		this.mer_txn = mer_txn;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	public Double getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getDiscriminator() {
		return discriminator;
	}
	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUdf1() {
		return udf1;
	}
	public void setUdf1(String udf1) {
		this.udf1 = udf1;
	}
	public String getUdf2() {
		return udf2;
	}
	public void setUdf2(String udf2) {
		this.udf2 = udf2;
	}
	public Long getUdf3() {
		return udf3;
	}
	public void setUdf3(Long udf3) {
		this.udf3 = udf3;
	}
	public String getUdf4() {
		return udf4;
	}
	public void setUdf4(String udf4) {
		this.udf4 = udf4;
	}
	public Long getUdf5() {
		return udf5;
	}
	public void setUdf5(Long udf5) {
		this.udf5 = udf5;
	}
	public Long getUdf6() {
		return udf6;
	}
	public void setUdf6(Long udf6) {
		this.udf6 = udf6;
	}
	public String getUdf9() {
		return udf9;
	}
	public void setUdf9(String udf9) {
		this.udf9 = "Vsign Licence System";
	}
	public String getIpg_txn_id() {
		return ipg_txn_id;
	}
	public void setIpg_txn_id(String ipg_txn_id) {
		this.ipg_txn_id = ipg_txn_id;
	}
	
	

}
