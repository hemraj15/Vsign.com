/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * @author Hemraj
 *
 */
@Entity
public class TransacationOrder extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CustOrder> custOrders=new ArrayList<>();
	
	private Double orderValue;
	
	private Double discAmount;

	/**
	 * @return the custOrders
	 */
	@OneToMany(cascade = CascadeType.ALL,targetEntity=CustOrder.class, fetch=FetchType.EAGER)
	public List<CustOrder> getOrders() {
		return custOrders;
	}

	/**
	 * @param custOrders the custOrders to set
	 */
	public void setOrders(List<CustOrder> custOrders) {
		this.custOrders = custOrders;
	}

	/**
	 * @return the orderValue
	 */
	public Double getOrderValue() {
		return orderValue;
	}

	/**
	 * @param orderValue the orderValue to set
	 */
	public void setOrderValue(Double orderValue) {
		this.orderValue = orderValue;
	}

	/**
	 * @return the discAmount
	 */
	public Double getDiscAmount() {
		return discAmount;
	}

	/**
	 * @param discAmount the discAmount to set
	 */
	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
	}
	
	

}
