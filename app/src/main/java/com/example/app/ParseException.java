package com.example.btp_app;

/**
 * Custom Exception for LL1 Parsing use only.
 */
public class ParseException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a new LL1 Parse Exception with the given message.
	 * @param string Message
	 */
	public ParseException(String string) {
		super(string);
	}
}