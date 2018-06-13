/**
 * 
 */
package com.vsign.tech.rest.form;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hemraj
 *
 */
public class TransacationOrderForm {

	private List<Long> orderIdList=new ArrayList<>();

	/**
	 * @return the orderIdList
	 */
	public List<Long> getOrderIdList() {
		return orderIdList;
	}

	/**
	 * @param orderIdList the orderIdList to set
	 */
	public void setOrderIdList(List<Long> orderIdList) {
		this.orderIdList = orderIdList;
	}
}
