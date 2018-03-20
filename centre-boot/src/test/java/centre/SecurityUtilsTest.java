package centre;

import org.junit.Test;

import jkt.centre.SecurityUtils;
import static org.junit.Assert.*;

public class SecurityUtilsTest {
	@Test
	public void checkPasswordComplexityTest() {
		assertTrue(SecurityUtils.checkPasswordComplexity("aC1aaaaa"));				// Contient minuscule, majuscule et chiffre accepté
		assertTrue(SecurityUtils.checkPasswordComplexity("aC!aaaaa"));				// Contient minuscule, majuscule et caractère spécial
		assertTrue(SecurityUtils.checkPasswordComplexity("a1!aaaaa"));				// Contient minuscule, chiffre et caractère spécial accepté
		assertTrue(SecurityUtils.checkPasswordComplexity("C1!CCCCC"));				// Contient majuscule, chiffre et caractère spécial accepté
		
		assertFalse(SecurityUtils.checkPasswordComplexity("           "));			// Espaces refusés
		assertFalse(SecurityUtils.checkPasswordComplexity("aaaXXX!!! 2"));			// Espaces à l'intérieur refusés
		assertFalse(SecurityUtils.checkPasswordComplexity("aX!2"));					// Moins de 8 caractères refusés
	}
}
