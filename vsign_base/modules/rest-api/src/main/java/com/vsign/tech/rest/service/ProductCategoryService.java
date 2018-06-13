/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.List;

import com.vsign.tech.data.dao.entity.ProductCatagory;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;

/**
 * @author Hemraj
 *
 */
public interface ProductCategoryService {

	List<ProductCatagory> fetchAllProductsCategories(String status) throws DatabaseException, EmptyListException;
}
