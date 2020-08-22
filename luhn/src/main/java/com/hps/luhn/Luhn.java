package com.hps.luhn;

/**
 * @see https://en.wikipedia.org/wiki/Luhn_algorithm#Description
 */
public class Luhn {

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
	public boolean isValidLuhn(int cardNumber) {
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
	public int generateCheckDigit(int cardNumber) {
		return getLuhnSum(cardNumber, true) * 9 % 10;
	}


	/**
	 * TODO
	 * 
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
	public int countRange(int startRange, int endRange) {
		return 1;
	}


	/**
	 * Calculates the Luhn sum for a given credit card number.
	 *
	 * @param cardNumber
	 * 			  the credit card number to process
	 *
	 * @param doubleDigit
	 *            Used to determine if the least significant (right-most) digit
	 *            or second digits is doubled. This allows for isLuhnValid and
	 *            getCheckDigit to use the same code to calculate the Luhn sum.
	 *
	 * @return the calculated Luhn sum value for the given credit card number
	 */
	private int getLuhnSum(int cardNumber, boolean doubleDigit) {
		int sum = 0;

		while (cardNumber > 0) {
			// starting from the right (rightmost is the unknown check digit)
			long digit = cardNumber % 10;

			if (doubleDigit) { // double the value of every second digit
				digit *= 2;

				// if two digits, use the sum of the digits
				if (digit >= 10) {
					digit = digit / 10 + digit % 10;
				}
			}
			doubleDigit = !doubleDigit;

			sum += digit;

			cardNumber /= 10; // remaining digits to the left
		}

		return sum;
	}
}
