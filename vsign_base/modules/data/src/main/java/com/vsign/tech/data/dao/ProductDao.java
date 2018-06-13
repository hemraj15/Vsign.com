/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.vsign.tech.data.dao.entity.Product;
import com.vsign.tech.data.dto.ProductDto;

/**
 * @author Hemraj
 *
 */
public interface ProductDao extends GenericDao<Product, Long>{

	List<Product> fetchAllProducts(String status);

	List<ProductDto> fetchAllProductsByCategoryId(Long catId);

	Criteria getByProductCode(String productCode);

	List<Product> fetchAllProductsWithCatagory(String status);

}
