package com.vsign.tech.rest.constant;

public class ErrorCodes {

	private ErrorCodes() {
		// To stop instantiation of this constant class
	}

	public final static String	VALIDATION_ERROR								= "validation_error";

	public final static String	SIGNUP_EMAIL_EMPTY								= "signup_email_empty_error";

	public final static String	SIGNUP_EMAIL_NULL								= "signup_email_null_error";

	public final static String	SIGNUP_EMAIL_INVALID							= "signup_email_invalid_error";

	public final static String	DATABASE_ERROR									= "database_error";

	public final static String	EMAIL_ERROR										= "email_error";

	public final static String	SERVER_ERROR									= "server_error";

	public final static String	USER_NOT_FOUND_ERROR							= "user_not_found_error";

	public final static String	SIGNUP_FIRST_NAME_EMPTY							= "signup_firstname_empty_error";

	public final static String	SIGNUP_FIRST_NAME_NULL							= "signup_firstname_null_error";

	public final static String	SIGNUP_LAST_NAME_EMPTY							= "signup_lastname_empty_error";

	public final static String	SIGNUP_LAST_NAME_NULL							= "signup_lastname_null_error";

	public final static String	SIGNUP_PASSWORD_EMPTY							= "signup_password_empty_error";

	public final static String	SIGNUP_PASSWORD_NULL							= "signup_password_null_error";

	public final static String	SIGNUP_PASSWORD_INVALID							= "signup_password_invalid_error";

	public static final String	SIGNUP_ALREADY_INITIATED						= "signup_already_initiated_error";

	public static final String	SIGNUP_ALREADY_ACTIVE							= "signup_already_active_error";

	public static final String	SIGNUP_ACCOUNT_DEACTIVATED						= "signup_account_deactivated_error";

	public final static String	SIGNUP_COMPANY_NAME_EMPTY						= "signup_company_name_empty_error";

	public final static String	SIGNUP_COMPANY_NAME_NULL						= "signup_company_name_null_error";

	public final static String	SIGNUP_COMPANY_ADDRESS_EMPTY					= "signup_company_address_empty_error";

	public final static String	SIGNUP_COMPANY_ADDRESS_NULL						= "signup_company_address_null_error";

	public final static String	SIGNUP_COMPANY_COUNTRY_EMPTY					= "signup_company_country_empty_error";

	public final static String	SIGNUP_COMPANY_COUNTRY_NULL						= "signup_company_country_null_error";

	public final static String	SIGNUP_COMPANY_STATE_EMPTY						= "signup_company_state_empty_error";

	public final static String	SIGNUP_COMPANY_STATE_NULL						= "signup_company_state_null_error";

	public final static String	SIGNUP_COMPANY_CITY_EMPTY						= "signup_company_city_empty_error";

	public final static String	SIGNUP_COMPANY_CITY_NULL						= "signup_company_city_null_error";

	public final static String	SIGNUP_USER_TYPE_								= "signup_company_zipcode_empty_error";

	public final static String	SIGNUP_COMPANY_ZIPCODE_NULL						= "signup_company_zipcode_null_error";

	public final static String	SIGNUP_COMPANY_CONTACTNO_EMPTY					= "signup_company_contactno_empty_error";

	public final static String	SIGNUP_COMPANY_CONTACTNO_NULL					= "signup_company_contactno_null_error";

	public final static String	SIGNUP_COMPANY_LOGO_NULL						= "signup_company_logo_null_error";

	public static final String	SIGNUP_COMPANY_EMAILTOKEN_EMPTY					= "signup_company_emailtoken_empty_error";

	public static final String	SIGNUP_COMPANY_URLS_INVALID						= "signup_company_urls_invalid_error";

	public static final String	SIGNUP_REQUEST_NOT_MULTIPART					= "signup_request_not_multipart_error";

	public final static String	URL_LIST_EMPTY									= "url_list_empty_error";

	public static final String	COMPANY_FILE_UPLOAD_FILE_TYPE_EMPTY				= "company_file_upload_file_type_empty_error";

	public static final String	SIGNUP_COMPANY_FILE_UPLOAD_FAIL					= "signup_company_file_upload_fail";

	public static final String	SIGNUP_COMPANY_NOT_FOUND						= "signup_company_not_found";

	public static final String	SIGNUP_COMPANY_NOT_ACTIVE						= "signup_company_not_active";

	public static final String	SIGNUP_COMPANY_EMAILTOKEN_NULL					= "signup_company_emailtoken_null_error";

	public static final String	SIGNUP_URL_TYPE_INVALID							= "signup_url_type_invalid_error";

	public static final String	COMPANY_FILE_UPLOAD_FILE_TYPE_INVALID			= "company_file_upload_file_type_invalid_error";

	public final static String	FORGOT_PASSWORD_INVALID							= "forgot_password_invalid_error";

	public static final String	FORGOT_PASSWORD_EMAILTOKEN_NULL					= "forgot_password_emailtoken_null_error";

	public static final String	FORGOT_PASSWORD_EMAILTOKEN_EMPTY				= "forgot_password_emailtoken_empty_error";

	public static final String	FORGOT_PASSWORD_NULL							= "forgot_password_null_error";

	public static final String	RESET_PASSWORD_NULL								= "reset_password_null_error";

	public static final String	RESET_PASSWORD_EMPTY							= "reset_password_empty_error";

	public static final String	RESET_PASSWORD_INVALID							= "reset_password_invalid_error";

	public static final String	FORGET_PASSWORD_INITIATED						= "forgot_password_initiated_error";

	public static final String	RESET_PASSWORD_USER_ALREADY_ACTIVE				= "reset_password_user_active_error";

	public static final String	RESET_PASSWORD_ALREADY_ACTIVE					= "reset_password_user_already_active_error";

	public static final String	FORGOT_REQUEST_SIGNUP_INITIATED					= "forgot_paswword_signup_initiated_error";

	public static final String	SKILLSET_CATEGORY_LIST_EMPTY					= "skillset_category_list_empty";

	public static final String	SKILLSET_SUB_CATEGORY_LIST_EMPTY				= "skillset_sub_category_list_empty";

	public static final String	JOB_TITLE_NULL									= "job_title_null_error";

	public static final String	JOB_TITLE_EMPTY									= "job_title_empty_error";

	public static final String	JOB_USER_NULL									= "job_user_null_error";

	public static final String	JOB_USER_EMPTY									= "job_user_empty_error";

	public static final String	JOB_DATE_OF_EXPIRY_NULL							= "job_date_of_expiry_null_error";

	public static final String	JOB_DATE_OF_EXPIRY_EMPTY						= "job_date_of_expiry_empty_error";

	public static final String	JOB_CATEGORY_NULL								= "job_category_null_error";

	public static final String	JOB_CATEGORY_EMPTY								= "job_category_empty_error";

	public static final String	JOB_SKILL_SET_NULL								= "job_skill_set_null_error";

	public static final String	JOB_SKILL_SET_EMPTY								= "job_skill_set_empty_error";

	public static final String	JOB_FROM_EXPERIENCE_NULL						= "job_from_experience_null_error";

	public static final String	JOB_FROM_EXPERIENCE_EMPTY						= "job_from_experience_empty_error";

	public static final String	JOB_TO_EXPERIENCE_NULL							= "job_to_experience_null_error";

	public static final String	JOB_TO_EXPERIENCE_EMPTY							= "job_to_experience_empty_error";

	public static final String	JOB_DESCRIPTION_NULL							= "job_description_null_error";

	public static final String	JOB_DESCRIPTION_EMPTY							= "job_description_empty_error";

	public static final String	JOB_COUNTRY_NULL								= "job_country_null_error";

	public static final String	JOB_COUNTRY_EMPTY								= "job_country_empty_error";

	public static final String	JOB_STATE_NULL									= "job_state_null_error";

	public static final String	JOB_STATE_EMPTY									= "job_state_empty_error";

	public static final String	JOB_CITY_NULL									= "job_city_null_error";

	public static final String	JOB_CITY_EMPTY									= "job_city_empty_error";

	public static final String	RECRUIERS_LIST_EMPTY							= "recruiter_list_empty_error";

	public static final String	COUNTRY_LIST_EMPTY								= "country_list_exception";

	public static final String	JOB_LIST_EMPTY									= "job_list_empty";

	public static final String	CANDIDATE_LIST_EMPTY							= "candidate_list_empty";

	public static final String	ONDEMAND_INTERVIEW_LIST_EMPTY					= "ondemand_interview_list_empty";

	public static final String	LIVE_INTERVIEW_LIST_EMPTY						= "live_interview_list_empty";

	public static final String	USER_TYPE_NULL									= "user_type_null";

	public static final String	USER_TYPE_EMPTY									= "user_type_empty";

	public static final String	SIGNUP_ZIPCODE_EMPTY							= "signup_zipcode_empty";

	public static final String	EMPLOYEE_NOT_FOUND_ERROR						= "employee_not_found_error";
	public static final String	CUSTOMER_NOT_FOUND_ERROR						= "customer_not_found_error";

	public static final String	PRODUCT_LIST_EMPTY								= "product_list_empty";

	public static final String	LOGIN_USER_ID_NULL								= "login_user_id_null";

	public static final String	LOGIN_USER_ID_EMPTY								= "login_user_id_empty";

	public static final String	LOGIN_USER_PASSWORD_NULL						= "login_user_password_null";

	public static final String	LOGIN_USER_PASSWORD_EMPTY						= "login_user_password_empty";

	public static final String	PASSWORD_INVALID								= "password_invalid";

	public static final String	LOGIN_EMAIL_INVALID								= "login_email_invalid";

	public static final String	ZIPCODE_LENGTH_INVALID							= "zipcode_length_invalid";

	public static final String	SIGNUP_PASSWORD_LENGTH_INVALID					= "signup_password_length_invalid";

	public static final String	CONTACT_NUMBER_LENGTH_INVALI					= "contact_number_maxlength_exeeded";

	public static final String	HOUSE_NUMBER_LENGTH_INVALID						= "house_number_maxlength_exeeded";

	public static final String	STREET_NUMBER_LENGTH_INVALID					= "street_number_maxlength_exeeded";

	public static final String	LANDMARK_LENGTH_INVALID							= "landmark_maxlength_exceeded";

	public static final String	AREA_LENGTH_INVALID								= "area_maxlength_invalid";

	public static final String	SIGNUP_INITIATED								= "signup_initiated_status";

	public static final String	CUSTOMER_FILE_UPLOAD_FILE_TYPE_EMPTY			= "customer_file_upload_file_type_empty";

	public static final String	CUSTOMER_FILE_UPLOAD_FILE_TYPE_INVALID			= "customer_file_upload_file_type_invalid";

	public static final String	CUSTOMER_FILE_UPLOAD_ERROR						= "customer_file_upload_error";

	public static final String	COLLEGE_SECTION_INITIATE_ORDER_NOT_MULTIPART	= "college_section_initiate_order_not_multipart";

	public static final String	INVALID_PRODUCT_ERROR							= "invalid_product_error";

	public static final String	INVALID_USER_TYPE_ERROR							= "invalid_user_type_error";

	public static final String	MAIL_NOT_SENT_ERROR								= "mail_not_send_error";

	public static final String	ORDER_NOT_FOUND_ERROR							= "order_not_found_error";

	public static final String	INVALID_ORDER_STATUS							= "invalid_order_status";

	public static final String	PRODUCT_NOT_FOUND_IN_DATABASE					= "product_not_found_in_database";

	public static final String	CUSTOMER_FILE_DOWNLOAD_ERROR					= "customer_file_download_error";

	public static final String	ORDER_STATUS_ERROR								= "order_status_is_not_as_expected";

	public static final String	INVALID_TRANSACTION_NUMBER_ERROR				= "invalid_transaction_number_error";

	public static final String	INVALID_PRINT_QUANTITY							= "invalid_print_quantity";

	public static final String	INVALID_NUMBER_OF_PAGES							= "invalid_number_of_pages";

	public static final String	ORDER_LIST_EMPTY_ERROR							= "order_list_empty_error";

	public static final String	TRX_ORDER_NOT_FOUND_ERROR						= "transaction_order_not_found_error";

	public static final String	FILE_ID_NULL_ERROR								= "file_id_null_error";

	public static final String	SAMPLE_FILE_UPLOAD_ERROR						= "sample_file_upload_error";

	public static final String	PRODUCT_SAMPLE_FILE_NOT_MULTIPART				= "product_sample_file_not_multipart";

	public static final String	INVALID_PAGENUM_OR_COUNT						= "invalid_pagenum_or_count";

	public static final String	TRANSACTION_ORDER_LIST_EMPTY					= "transaction_order_list_empty";

	public static final String SIGNUP_MACHINE_ID_NULL = "signup_machine_id_null";
	public static final String SIGNUP_MACHINE_ID_EMPTY = "signup_machine_id_empty";

	public static final String MACHINE_ID_EMPTY_OR_NULL = "machine_id_null_or_empty";

	public static final String MACHINE_ID_NULL = "machine_id_null";
	public static final String MACHINE_ID_EMPTY = "machine_id_empty";

	public static final String GUEST_FIRST_NAME_NULL = "guest_first_name_null";
	public static final String GUEST_FIRST_NAME_EMPTY = "guest_first_name_empty";

	public static final String INVALID_MACHINE_ID = "invalid_machine_id";

	public static final String PLAN_LIST_EMPTY = "plans_list_emppty";

	public static final String MACHINE_ID_CAN_NOT_BE_NULL = "machine_id_can_not_be_null";

	public static final String INVALID_PLAN_ERROR = "invalid_plan_error";

	public static final String PLAN_NOT_FOUND_IN_DATABASE = "plan_not_found_in_database";

	public static final String ORDER_INITIATE_ERROR = "order_initiate_error";

}
