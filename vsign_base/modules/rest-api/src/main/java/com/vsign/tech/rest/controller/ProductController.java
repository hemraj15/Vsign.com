/**
 * 
 */
package com.vsign.tech.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vsign.tech.data.dao.entity.PrintStore;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.FileUploadException;
import com.vsign.tech.rest.exception.ProductNotFoundException;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.VsignService;
import com.vsign.tech.rest.service.ProductCategoryService;
import com.vsign.tech.rest.service.ProductService;

/**
 * @author Hemraj
 *
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	private Logger					LOGGER	= LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService			prodService;

	@Autowired
	private ProductCategoryService	prodCatService;

	@Autowired
	private VsignService		printService;

	// @Secured(value = { SystemRoles.ADMIN,SystemRoles.CUSTOMER})
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Object fetchAllProducts(@RequestParam String status, HttpServletResponse response) {
		LOGGER.info(">> fetchAllProducts");

		LOGGER.info(">> fetchAllProducts for status : " + status);
		Object data = null;
		try {
			LOGGER.info("fetchOrders <<");
			data = prodService.fetchAllProducts(status);

			// data = prodService.fetchAllProductsWithCatagory(status);
			LOGGER.info("Get All Product data size " + data);
		} catch (EmptyListException e) {

			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	// @Secured(value = { SystemRoles.ADMIN,SystemRoles.CUSTOMER})
	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
	public Object fetchAllProductsByCategoryId(@PathVariable Long categoryId,
	        HttpServletResponse response) {
		LOGGER.info(">> fetchAllProductsByCategoryId");

		LOGGER.info(">> fetchAllProductsByCategoryId for category : " + categoryId);
		Object data = null;
		try {
			LOGGER.info("fetchAllProductsByCategoryId <<");
			data = prodService.fetchAllProductsByCategoryId(categoryId);

			LOGGER.info("Get All Product data size " + data);
		} catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	// @Secured(value = { SystemRoles.ADMIN,SystemRoles.CUSTOMER})
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public Object fetchAllProductCategories(@RequestParam String status,
	        HttpServletResponse response) {
		LOGGER.info(">> fetchAllProductCategories");

		LOGGER.info(">> fetchAllProductsCategories for  status : " + status);
		Object data = null;
		try {
			LOGGER.info("fetchAllProductCategories <<");
			data = prodCatService.fetchAllProductsCategories(status);

			LOGGER.info("Get All Product Catagory data size " + data);
		} catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	// @Secured(value = { SystemRoles.ADMIN,SystemRoles.CUSTOMER})
	@RequestMapping(value = "/{productId}/sample-upload", method = RequestMethod.POST)
	public Object uploadProductSample(@PathVariable Long productId, @RequestParam String fileType,
	        @RequestParam MultipartFile file, HttpServletResponse response,
	        HttpServletRequest request) {
		LOGGER.info(">> uploadProductSample ");

		LOGGER.info(">> Sample Upload for product  : " + productId);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Map<String, Object> map = new HashMap<>();
		Long fileId = null;

		Object data = null;
		if (!isMultipart || fileType == null) {
			data = new ErrorResponse();
			((ErrorResponse) data)
			        .setErrorCode(ErrorCodes.PRODUCT_SAMPLE_FILE_NOT_MULTIPART);
			((ErrorResponse) data).setMessage("uploadProductSample validation failed !");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				LOGGER.info("fetchOrders <<");
				map = printService.uploadProductSampleFile(fileType, file, productId);
				map.put("message", "sample file uploaded for product : "+productId);
				data=map;
			} catch (ProductNotFoundException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.PRODUCT_NOT_FOUND_IN_DATABASE);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (FileUploadException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.SAMPLE_FILE_UPLOAD_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			catch (Exception e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

		}
		return data;
	}
	// @Secured(value = { SystemRoles.ADMIN,SystemRoles.CUSTOMER})
		@RequestMapping(value = "/plans", method = RequestMethod.GET)
		public Object fetchAllPlans(@RequestParam String status, HttpServletResponse response) {
			LOGGER.info(">> fetchAllPlans");

			LOGGER.info(">> fetchAllPlans for status : " + status);
			Object data = null;
			try {
				LOGGER.info("fetchAllPlans <<");
				//data = prodService.fetchAllProducts(status);
				data = prodService.fetchAllProductsPlans(status);

				// data = prodService.fetchAllProductsWithCatagory(status);
				LOGGER.info("Get All Plans data size " + data);
			} catch (EmptyListException e) {

				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			catch (Exception e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			return data;
		}
}
