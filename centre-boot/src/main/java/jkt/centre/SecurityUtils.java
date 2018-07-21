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
	
	public static boolean checkPasswordComplexity(final String password) {
		// Vérifie l'absence d'espaces
		if(password.matches(".*\\s+.*")) {
			return false;
		}
		
		// Vérification de la taille du mot de passe
		if(password.length() < 8) {
			return false;
		}
		
		// Vérifie qu'au moins 3 types de caractères sont présents parmi minuscules, majuscules, chiffres, caractères spéciaux
		int count = 0;
		
		if(password.matches(".*[a-z]+.*")) {	// Présence de minuscules
			count++;
		}
		
		if(password.matches(".*[A-Z]+.*")) {	// Présence de majuscules
			count++;
		}
		
		if(password.matches(".*[0-9]+.*")) {	// Présence de chiffres
			count++;
		}
		
		if((password.replaceAll("[a-zA-Z0-9]", "").length() > 0)) {	// Présence de caractères qui ne sont ni des majuscules, minucules ou chiffres
			count++;
		}

		if(count < 3) {
			return false;
		}
		
		return true;
	}
	
	public static String hashWithSel(final String text, final String sel) {
		return hash(sel + "-" + text);
	}

	public static boolean checkHashWithSel(final String text, final String sel, final String hashReference) {
		return checkHash(sel + "-" + text, hashReference);
	}
	
	public static String hash(final String textToHash) {
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

	public static boolean checkHash(final String text, final String hashReference) {
		return hash(text).equals(hashReference);
	}
	
	public static void main(final String argv[]) {
		System.out.println(hashWithSel("admin", "admin"));
		System.out.println(hashWithSel("user", "user"));
	}
}
