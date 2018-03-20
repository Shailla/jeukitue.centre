package jkt.centre.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	/** No constructor */
	private ValidationUtil() {
	}

	/** Validate an email format */
	public static boolean validateEmail(final String email) {
		if(email == null) {
			return false;
		}
		
		final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
}
