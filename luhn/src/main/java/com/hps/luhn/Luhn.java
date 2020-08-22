package com.hps.luhn;

import java.math.BigInteger;

/**
 * @see https://en.wikipedia.org/wiki/Luhn_algorithm#Description
 */
public class Luhn {

	public final BigInteger bigIntegerOne = new BigInteger("1");
	public final BigInteger bigIntegerTwo = new BigInteger("2");
	public final BigInteger bigIntegerTen = new BigInteger("10");
	public final BigInteger bigIntegerZero = new BigInteger("0");

	/**
	 * Accepts a card number and determines if the card number is a valid number
	 * with respect to the Luhn algorithm.
	 * 
	 * @param cardNumber
	 *            the card number
	 * 
	 * @return true if the card number is valid according to the Luhn algorithm,
	 *         false if not
	 */
	public boolean isValidLuhn(BigInteger cardNumber) {
		return ((getLuhnSum(cardNumber, false) % 10) == 0);
	}


	/**
	 * Accepts a partial card number (excluding the last digit) and generates
	 * the appropriate Luhn check digit for the number.
	 * 
	 * @param cardNumber
	 *            the card number (not including a check digit)
	 * 
	 * @return the check digit
	 */
	public int generateCheckDigit(BigInteger cardNumber) {
		return getLuhnSum(cardNumber, true) * 9 % 10;
	}


	/**
	 * Accepts two card numbers representing the starting and ending numbers of
	 * a range of card numbers and counts the number of valid Luhn card numbers
	 * that exist in the range, inclusive.
	 * 
	 * @param startRange
	 *            the starting card number of the range (may not be valid luhn)
	 * @param endRange
	 *            the ending card number of the range (may not be a valid luhn)
	 * 
	 * @return the number of valid Luhn card numbers in the range, inclusive
	 */
	public int countRange(BigInteger startRange, BigInteger endRange) {
		if (endRange.compareTo(startRange) < 0)
			return 0;

		int count = 0;
		BigInteger cardNumber = startRange;
		while (cardNumber.compareTo(endRange) <= 0) {
			if (isValidLuhn(cardNumber)) {
				count++;
			}

			cardNumber = cardNumber.add(this.bigIntegerOne);
		}

		return count;
	}


	/**
	 * Calculates the Luhn sum for a given card number.
	 *
	 * @param cardNumber
	 * 			  the card number to process
	 *
	 * @param doubleDigit
	 *            Used to determine if the least significant (right-most) digit
	 *            or second digits is doubled. This allows for isLuhnValid and
	 *            getCheckDigit to use the same code to calculate the Luhn sum.
	 *
	 * @return the calculated Luhn sum value for the given card number
	 */
	private int getLuhnSum(BigInteger cardNumber, boolean doubleDigit) {
		int sum = 0;

		while (cardNumber.compareTo(this.bigIntegerZero) > 0) {
			// starting from the right (rightmost is the unknown check digit)
			BigInteger digit = cardNumber.mod(this.bigIntegerTen);

			if (doubleDigit) { // double the value of every second digit
				digit = digit.multiply(this.bigIntegerTwo);

				// if two digits, use the sum of the digits
				if (digit.compareTo(this.bigIntegerTen) >= 0) {
					digit = digit.divide(this.bigIntegerTen).add(digit.mod(this.bigIntegerTen));
				}
			}

			doubleDigit = !doubleDigit;

			sum += digit.intValue();

			cardNumber = cardNumber.divide(this.bigIntegerTen); // remaining digits to the left
		}

		return sum;
	}
}
