/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import com.vsign.tech.data.dao.entity.ProductCatagory;
import com.vsign.tech.data.dto.ProductCategoryDto;

/**
 * @author Hemraj
 *
 */
public interface ProductCategaryDao extends GenericDao<ProductCatagory, Long> {

	//List<ProductCategoryDto> fetchAllProductsByCategoryStatus(String status);

	List<ProductCatagory> fetchAllProductsCategories(String status);

	List<ProductCategoryDto> fetchAllProductsCategoriesByStatus(String status);

}
