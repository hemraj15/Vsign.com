/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hemraj
 *
 */

@Entity
@Table(name="products")
public class Product extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3708581940150054655L;
	
	private ProductCatagory catagory;
	private String name;
	private boolean isFavourite;
	private SampleFileRecord sampleFileId;
	private String productCode;
	
	//private ProductSamples sample;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="product_catagory")
	public ProductCatagory getCatagory() {
		return catagory;
	}

	public void setCatagory(ProductCatagory catagory) {
		this.catagory = catagory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	/*@OneToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="sample")
	public ProductSamples getSample() {
		return sample;
	}

	public void setSample(ProductSamples sample) {
		this.sample = sample;
	}
*/	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public SampleFileRecord getSampleFileId() {
		return sampleFileId;
	}

	public void setSampleFileId(SampleFileRecord sampleFileId) {
		this.sampleFileId = sampleFileId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	
}
