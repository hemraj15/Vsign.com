/**
 * 
 */
package com.vsign.tech.rest.form;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Hemraj
 *
 */

public class CollegeSectionOrderForm {
	
	private String fieType;
	private String bindingType;
	private Integer totalPages;
	private Integer nonGlossyColorPages;
	private Integer glossyColorPages;
	private MultipartFile file;
	private File file1;
	private String anyOtherRequest;
	
	public String getFieType() {
		return fieType;
	}
	public void setFieType(String fieType) {
		this.fieType = fieType;
	}
	public String getBindingType() {
		return bindingType;
	}
	public void setBindingType(String bindingType) {
		this.bindingType = bindingType;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getNonGlossyColorPages() {
		return nonGlossyColorPages;
	}
	public void setNonGlossyColorPages(Integer nonGlossyColorPages) {
		this.nonGlossyColorPages = nonGlossyColorPages;
	}
	public Integer getGlossyColorPages() {
		return glossyColorPages;
	}
	public void setGlossyColorPages(Integer glossyColorPages) {
		this.glossyColorPages = glossyColorPages;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getAnyOtherRequest() {
		return anyOtherRequest;
	}
	public void setAnyOtherRequest(String anyOtherRequest) {
		this.anyOtherRequest = anyOtherRequest;
	}
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}

}
