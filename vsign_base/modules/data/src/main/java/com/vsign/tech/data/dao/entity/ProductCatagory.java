/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hemraj
 *
 */

@Entity
@Table(name="product_catagory")
public class ProductCatagory extends VsignBaseEntity implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142218862072129199L;
	
	private String name;
	private Set<Product> products=new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "catagory")
	@JsonIgnore
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	

}
