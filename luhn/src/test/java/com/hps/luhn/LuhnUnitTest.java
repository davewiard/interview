package com.hps.luhn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigInteger;

public class LuhnUnitTest {

	Luhn luhn;

	@Before
	public void setUp() throws Exception {
		luhn = new Luhn();
	}

	@Test
	public void generateCheckDigit() {
		assertEquals(9, luhn.generateCheckDigit(new BigInteger("92739871")));
		assertEquals(0, luhn.generateCheckDigit(new BigInteger("92739875")));

		// using MasterCard missing last digit
		assertEquals(0, luhn.generateCheckDigit(new BigInteger("520082828282821")));
	}

	@Test
	public void isValidLuhn() {
		assertFalse(luhn.isValidLuhn(new BigInteger("927398710")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398711")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398712")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398713")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398714")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398715")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398716")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398717")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398718")));
		assertTrue(luhn.isValidLuhn(new BigInteger("927398719")));

		// using Visa
		assertTrue(luhn.isValidLuhn(new BigInteger("4242424242424242")));
		assertTrue(luhn.isValidLuhn(new BigInteger("4000056655665556")));

		// using MasterCard
		assertTrue(luhn.isValidLuhn(new BigInteger("378282246310005")));
		assertTrue(luhn.isValidLuhn(new BigInteger("2223003122003222")));
	}

	@Test
	public void countRange() {
		// testing startRange > endRange
		assertEquals(0, luhn.countRange(new BigInteger("927398720"),
												new BigInteger("927398710")));

		assertEquals(1, luhn.countRange(new BigInteger("927398710"),
												new BigInteger("927398720")));

		// using American Express
		assertEquals(844, luhn.countRange(new BigInteger("371449635390000"),
													new BigInteger("371449635398431")));
	}

}
