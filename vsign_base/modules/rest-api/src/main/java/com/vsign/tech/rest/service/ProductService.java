/**
 * 
 */
package com.vsign.tech.rest.service;

import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;

/**
 * @author Hemraj
 *
 */
public interface ProductService {

	Object fetchAllProducts(String status) throws DatabaseException ,EmptyListException;

	Object fetchAllProductsByCategoryId(Long catId)throws DatabaseException ,EmptyListException;

	Object fetchAllProductsWithCatagory(String status) throws EmptyListException;

	Object fetchAllProductsPlans(String status)throws DatabaseException ,EmptyListException ;

	

	//List<ProductCategoryDto> fetchAllProductsByCategoryIdNew(Long catId) throws DatabaseException, EmptyListException;

}
