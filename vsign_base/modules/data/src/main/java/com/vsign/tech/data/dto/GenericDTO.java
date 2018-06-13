/**
 * 
 */
package com.vsign.tech.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemraj
 *
 */



@JsonInclude(Include.NON_NULL)
public class GenericDTO {

	private Integer	totalCount;

	private List<?>	result;

	private Object	additionalInfo;

	public GenericDTO() {

	}

	public GenericDTO(Integer totalCount, List<?> result, Object additionalInfo) {
		this.totalCount = totalCount;
		this.result = result;
		this.additionalInfo = additionalInfo;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public Object getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(Object additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
