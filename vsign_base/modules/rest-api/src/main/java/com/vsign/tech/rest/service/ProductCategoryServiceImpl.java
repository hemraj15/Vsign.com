/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.dao.ProductCategaryDao;
import com.vsign.tech.data.dao.ProductCategaryDaoImpl;
import com.vsign.tech.data.dao.ProductDao;
import com.vsign.tech.data.dao.entity.Product;
import com.vsign.tech.data.dao.entity.ProductCatagory;
import com.vsign.tech.data.dto.ProductCategoryDto;
import com.vsign.tech.data.dto.ProductDto;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;

/**
 * @author Hemraj
 *
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private Logger				LOGGER	= LoggerFactory.getLogger(ProductCategoryServiceImpl.class);
	
    @Autowired	
	private ProductCategaryDao prodCatDao;
    
    @Autowired
    private ProductDao prodDao;
	
	@Override
	@Transactional
	public List<ProductCatagory> fetchAllProductsCategories(String status) throws DatabaseException, EmptyListException {
		 
		List<ProductCategoryDto> prodCatDtos=null;
		List<ProductCatagory> prodCat=null;
		List<ProductCatagory> prodCat1=new ArrayList<>();
		Set<Product> set=new LinkedHashSet<>();
		try {

			//prodCatDtos = prodCatDao.fetchAllProductsCategoriesByStatus(status);
			prodCat=prodCatDao.fetchAllProductsCategories(status);
			LOGGER.info("Product categories : "+prodCat);
			Map<String,Object> map=new LinkedHashMap<>();
			
			
			for (ProductCatagory cat1 :prodCat) {
				ProductCatagory cat=new ProductCatagory();
				cat.setId(cat1.getId());
				cat.setName(cat1.getName());
				cat.setStatus(cat1.getStatus());
				//set=prodDao.fetchAllProductsByCategoryId(cat1.getId());
				LOGGER.info("Set of Products for "+cat1.getId()+" are : "+set.size());
				cat.setProducts(set);
				
				prodCat1.add(cat);
				
			}
			

		} catch (Exception e) {
			LOGGER.error("Error occured while getting ProductCategoryDto list through database", e);
			throw new EmptyListException("Error occured while getting ProductCategoryDto list through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		if (prodCat == null || prodCat.isEmpty()) {
			throw new EmptyListException("ProductCategoryDto List is empty  ", ErrorCodes.PRODUCT_LIST_EMPTY);
		}

		
				
		return prodCat;
	}
/*	@Override
	public List<ProductCategoryDto> fetchAllProductsCategories(String status)
			throws DatabaseException, EmptyListException {
		
		return null;
	}*/

}
