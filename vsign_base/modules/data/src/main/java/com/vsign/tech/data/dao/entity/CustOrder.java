/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Hemraj
 *
 */

@Entity
@Table(name="cust_order")
public class CustOrder extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8995896702674504742L;
	
	private Double orderPrice;
	private Double paidAmount; 
	private String machinId;
	private Long planId;
	private String planName;
	private Customer customer;
	//private Set<CustomerFiles> fileId;
    private Double discount;
    private Double discountAmount;
//@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER,targetEntity=Product.class)
	/*
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "order_products", joinColumns = {
	        @JoinColumn(name = "order_id", nullable = false, updatable = false) }, inverseJoinColumns = {
	                @JoinColumn(name = "product_id", nullable = false, updatable = false) })

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
*/


	@Column(name="price")
	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

/*	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Orde_Documents", joinColumns = {
	        @JoinColumn(name = "ord_id", nullable = false, updatable = false) }, inverseJoinColumns = {
	                @JoinColumn(name = "file_id", nullable = false, updatable = false) })
	public Set<CustomerFiles> getFileId() {
		return fileId;
	}

	public void setFileId(Set<CustomerFiles> fileId) {
		this.fileId = fileId;
	}
*/
	

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getMachinId() {
		return machinId;
	}

	public void setMachinId(String machinId) {
		this.machinId = machinId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	

}
