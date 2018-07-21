package jkt.centre.controller;

public class Messages {

	/* *********************************************************************************** */
	// HTTP method internationalized messages
	/* *********************************************************************************** */
	
	/** An error occurred */
	public static final String ERROR_METHOD_UNKNWON = "error.method.unknown";
	
	/** Form validation error(s) */
	public static final String ERROR_METHOD_FORM_VALIDATION = "error.method.form.validation";
	
	/** Form missing parameter */
	public static final String ERROR_METHOD_FORM_MISSING_PARAMETER = "error.method.form.missing.parameter";
	
	/** User already exists */
	public static final String ERROR_METHOD_USER_ALREADY_EXISTS = "error.method.user.alreadyExists";
	
	/** User not exists */
	public static final String ERROR_METHOD_USER_UNKNWON = "error.method.user.notExists";

	
	/* *********************************************************************************** */
	// Form validation internationalized messages
	/* *********************************************************************************** */
	
	/** Unknown profile */
	public static final String VALID_PROFILE_UNKNWON = "error.valid.profile.unknown";
	
	/** Password doesn't respect password rules (characters authorized, size/complexity) */
	public static final String VALID_PASSWORD_NOTCOMPLIANT = "error.valid.password.notCompliant";
}
