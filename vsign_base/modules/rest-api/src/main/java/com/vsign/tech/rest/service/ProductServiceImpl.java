/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.dao.PlansDao;
import com.vsign.tech.data.dao.ProductCategaryDao;
import com.vsign.tech.data.dao.ProductDao;
import com.vsign.tech.data.dao.entity.Product;
import com.vsign.tech.data.dto.PlansDto;
import com.vsign.tech.data.dto.ProductDto;
import com.vsign.tech.message.utils.ReadConfigurationFile;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;

/**
 * @author Hemraj
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private Logger				LOGGER	= LoggerFactory.getLogger(ProductServiceImpl.class);
	private static String		BASE_UPLOAD_PATH	= "";
	
	@Autowired
	private ProductDao prodDao;
	
	@Autowired
	private ProductCategaryDao prodCatDao;
	@Autowired
	private PlansDao plansDao;
	
	static {
		Properties props = ReadConfigurationFile.getProperties("file-upload.properties");
		BASE_UPLOAD_PATH = props.getProperty("base_upload_path");
	}
	
	@Override
	@Transactional
	public List<Product> fetchAllProducts(String status) throws DatabaseException, EmptyListException {
	
		List<Product> dtos=null;
		try {
			LOGGER.info("fetchingAllProducts <<");
			dtos = prodDao.fetchAllProducts(status);

		} catch (Exception e) {
			LOGGER.error("Error occured while getting ProductDto list through database", e);
			throw new EmptyListException("Error occured while getting ProductDto list through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		if (dtos == null || dtos.isEmpty()) {
			throw new EmptyListException("ProductDto List is empty", ErrorCodes.PRODUCT_LIST_EMPTY);
		}

		
		LOGGER.info("fetchAllProducts <<<<");
		
		return dtos;
	}


	@Override
	@Transactional
	public List<ProductDto> fetchAllProductsByCategoryId(Long catId) throws DatabaseException, EmptyListException {
		 
		List<ProductDto> prodDtos=null;
		try {

			prodDtos = prodDao.fetchAllProductsByCategoryId(catId);
			
			

		} catch (Exception e) {
			LOGGER.error("Error occured while getting ProductDto list through database", e);
			throw new EmptyListException("Error occured while getting ProductDto list through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		if (prodDtos == null || prodDtos.isEmpty()) {
			throw new EmptyListException("ProductDto List is empty for catagory Id "+catId, ErrorCodes.PRODUCT_LIST_EMPTY);
		}

		LOGGER.info("fetchAllProductsByCategoryId <<");
				
		return prodDtos;
	}


	@Override
	@Transactional
	public Object fetchAllProductsWithCatagory(String status) throws EmptyListException {
		List<Product> list=new ArrayList<>();
		
		try {
			
			list=prodDao.fetchAllProductsWithCatagory( status);
		} catch (Exception e) {
			LOGGER.error("Error occured while getting Product list through database", e);
			throw new EmptyListException("Error occured while getting Product list through database",
			        ErrorCodes.DATABASE_ERROR);
		}
		return list;
	}


	@Override
	@Transactional
	public Object fetchAllProductsPlans(String status) throws DatabaseException, EmptyListException {
		

		List<PlansDto> dtos=null;
		try {
			LOGGER.info("fetchAllProductsPlans <<");
			dtos = plansDao.fetchAllPlans(status);

		} catch (Exception e) {
			LOGGER.error("Error occured while getting PlansDto list through database", e);
			throw new EmptyListException("Error occured while getting PlanstDto list through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		if (dtos == null || dtos.isEmpty()) {
			throw new EmptyListException("PlansDto List is empty", ErrorCodes.PLAN_LIST_EMPTY);
		}

		
		LOGGER.info("fetchAllProducts <<<<");
		
		return dtos;

	}
	
	
	
	

}
