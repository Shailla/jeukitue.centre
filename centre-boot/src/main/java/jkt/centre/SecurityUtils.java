package jkt.centre;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtils {
	static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);
	
	static final String SHA256_ALGO = "SHA-256";
	static final String SEL = "gr12sel-";

	public static String hashWithSel(String text, String sel) {
		return hash(sel + "-" + text);
	}

	public static boolean checkHashWithSel(String text, String sel, String hashReference) {
		return checkHash(sel + "-" + text, hashReference);
	}
	
	public static String hash(String textToHash) {
		String textWithSel = SEL + textToHash;
		String hashStr = null;

		try {
			MessageDigest digest = MessageDigest.getInstance(SHA256_ALGO);
			byte[] hash = digest.digest(textWithSel.getBytes(StandardCharsets.UTF_8));
			hashStr = Base64.getEncoder().encodeToString(hash);
		}
		catch(NoSuchAlgorithmException exception) {
			LOGGER.error("This should never happen", exception);
		}

		return hashStr;
	}

	public static boolean checkHash(String text, String hashReference) {
		return hash(text).equals(hashReference);
	}
	
	public static void main(String argv[]) {
		System.out.println(hashWithSel("admin", "admin"));
		System.out.println(hashWithSel("user", "user"));
	}
}
