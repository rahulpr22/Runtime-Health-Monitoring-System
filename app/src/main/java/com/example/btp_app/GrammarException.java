package com.example.btp_app;

/**
 * Custom Exception for LL1 Grammar use only.
 */
public class GrammarException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a new LL1 Grammar Exception with the given message.
	 * @param string Message
	 */
	public GrammarException(String string) {
		super(string);
	}
}
