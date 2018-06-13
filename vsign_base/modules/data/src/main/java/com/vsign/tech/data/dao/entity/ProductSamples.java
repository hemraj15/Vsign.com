/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hemraj
 *
 */
@Entity
@Table(name="product_samples")
public class ProductSamples extends VsignBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6030975521334358688L;
	
	private Product product;
	private String name ;
	private SampleFileRecord sampleFileId;

	@OneToOne(cascade=CascadeType.ALL)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	public SampleFileRecord getSampleFileId() {
		return sampleFileId;
	}

	public void setSampleFileId(SampleFileRecord sampleFileId) {
		this.sampleFileId = sampleFileId;
	}


}
