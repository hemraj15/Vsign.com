/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class StatusException extends Exception {
	
	private static final long serialVersionUID=1L;
	private String erroCode;
	
	public  StatusException(String specificMessage) {
		super(specificMessage);
	}
  public StatusException(String specificMessage , String erroCode) {
	
	  super(specificMessage);
	  this.erroCode=erroCode;
}

  public StatusException(String specificMessage , Throwable e) {
	
	  super(specificMessage ,e);
}
public String getErroCode() {
	return erroCode;
}
public void setErroCode(String erroCode) {
	this.erroCode = erroCode;
}
  
 
}
