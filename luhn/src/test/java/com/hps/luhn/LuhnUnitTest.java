package com.hps.luhn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LuhnUnitTest {

	Luhn luhn;

	@Before
	public void setUp() throws Exception {
		luhn = new Luhn();
	}

	@Test
	public void generateCheckDigit() {
		assertEquals(9, luhn.generateCheckDigit(92739871));

		// final digit being 5 exposes a bug in generateCheckDigit when the last digit is doubled
		assertEquals(0, luhn.generateCheckDigit(92739875));
	}

	@Test
	public void isValidLuhn() {
		assertFalse(luhn.isValidLuhn(927398710));
		assertFalse(luhn.isValidLuhn(927398711));
		assertFalse(luhn.isValidLuhn(927398712));
		assertFalse(luhn.isValidLuhn(927398713));
		assertFalse(luhn.isValidLuhn(927398714));
		assertFalse(luhn.isValidLuhn(927398715));
		assertFalse(luhn.isValidLuhn(927398716));
		assertFalse(luhn.isValidLuhn(927398717));
		assertFalse(luhn.isValidLuhn(927398718));
		assertTrue(luhn.isValidLuhn(927398719));
	}

	@Test
	public void countRange() {
		assertEquals(1, 927398710, 927398720);
	}

}
