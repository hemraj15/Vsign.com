/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Hemraj
 *
 */

@Entity(name = "url_type")
public class UrlType extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4001090034029840972L;

	private String				urlTypeName;

	@Column(name = "url_type_name", length = 25)
	public String getUrlTypeName() {
		return urlTypeName;
	}

	public void setUrlTypeName(String urlTypeName) {
		this.urlTypeName = urlTypeName;
	}

	
}
