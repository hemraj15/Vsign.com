/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.FileDownloadException;
import com.vsign.tech.rest.exception.FileUploadException;
import com.vsign.tech.rest.exception.InvalidFieldLengthException;
import com.vsign.tech.rest.exception.InvalidPlanException;
import com.vsign.tech.rest.exception.MailNotSendException;
import com.vsign.tech.rest.exception.OrderInitiateException;
import com.vsign.tech.rest.exception.ProductNotFoundException;
import com.vsign.tech.rest.exception.SignUpException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.SignUpStep1Form;

/**
 * @author Hemraj
 *
 */
public interface VsignService {

	String completeSignup(SignUpStep1Form form1) throws SignUpException, InvalidFieldLengthException;

	Long uploadFile(String fileType, MultipartFile file)
	        throws FileUploadException, UserNotFoundException;

	Map<String, Object> downloadCollegeProjectFiles(Long order_id) throws DatabaseException ,FileDownloadException;

	Map<String, Object> uploadProductSampleFile(String fileType, MultipartFile file, Long productId) throws FileUploadException, ProductNotFoundException;

	Map<String, Object> initiateOrder(String machineId, Long planId) throws MailNotSendException ,InvalidPlanException ,UserNotFoundException, OrderInitiateException;

}
