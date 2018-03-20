package centre.util;

import org.junit.Test;
import static org.junit.Assert.*;

import jkt.centre.util.ValidationUtil;

public class ValidationUtilTest {
	@Test
	public void validateEmailTest() {
		assertFalse(ValidationUtil.validateEmail(""));
		assertFalse(ValidationUtil.validateEmail("   "));
		assertFalse(ValidationUtil.validateEmail("homer simpson@gmail.com"));
		assertFalse(ValidationUtil.validateEmail("homer simpson@gmail.123"));
		assertTrue(ValidationUtil.validateEmail("homer.simpson@gmail.com"));
		assertTrue(ValidationUtil.validateEmail("homer_simpson@gmail.com"));
	}
}
