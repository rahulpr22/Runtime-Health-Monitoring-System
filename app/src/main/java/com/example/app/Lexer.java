package com.example.app;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@Deprecated
@SuppressWarnings("unused")
/**
 * LL1 Lexer that reads a file and generates Tokens.
 */
public class Lexer {
	static final String UNCHECKED = "unchecked";
	private Scanner scanner;
	private Queue<Token> tokenBuffer;
	private String outputFile;
	String m="";
	String tkn="";
	static int counter=0;
	static int clock=0;
	public String getMapping()
	{
		return m;
	}
	/**
	 * Construct a new LL1 LExer with the given file.
	 * @param f File to perform lexical analysis on.
	 */
	public Lexer(Scanner f) {

		scanner = f;
		tokenBuffer = new LinkedList<Token>();


		writeToOutputFile();
		//map();

	}

	/**
	 * Add a Token to the tokenBuffer based off the given String.
	 * @param s String to tokenize and add to the tokenBuffer
	 */
	public void fillTokenBuffer(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			// Loop through all characters in the string
			char c = s.charAt(i);

			if (c == ' ' || c == ',' || c == ';' || c == '&' || c == '+' || c == '-' || c == '(' || c == ')' || c == '%' || c == '*') {
				if (sb.toString().length() > 0) {
					tokenBuffer.add(new Token(sb.toString()));
				}
				tokenBuffer.add(new Token(new Character(c).toString()));
				sb.setLength(0);
			} else if (c == ':' && i + 1 < s.length() && s.charAt(i + 1) == '=') {
				if (sb.toString().length() > 0) {
					tokenBuffer.add(new Token(sb.toString()));
				}
				tokenBuffer.add(new Token(":="));
				i++;
				sb.setLength(0);
			} else {
				sb.append(c);
				if (i == s.length() - 1) {
					tokenBuffer.add(new Token(sb.toString()));

					Token temp =new Token(sb.toString());
					String y= temp.toString();

					if(isNumeric(y))
					{
						clock += Integer.parseInt(y);
						counter++;m += " "+y+" ]; clock: x = "+clock+" ; Next State: state-"+counter+" "+"\n";

					}
					else if(y.toString().equalsIgnoreCase("timegap"))
						m += " , guard: x ";
					else if(y.toString().equalsIgnoreCase("hr"))
					{
						if(counter==0)
							m+= "HeartRate :: {\n";

						m += "\t\tState - "+counter+" -->  Transition : [ Action : HR" ;
					}
					else if(y.toString().equalsIgnoreCase("bp"))
					{
						if(counter==0)
							m+="BloodPressure :: {\n";
						m += "\t\tState - "+counter+" --> Transition : [ Action : BP" ;
					}
					else if(y.toString().equalsIgnoreCase("temp"))
					{
						if(counter==0)
							m+="Temperature :: {\n";
						m += "\t\tState - "+counter+" -->  Transition : [ Action : TEMP" ;
					}
					else if(y.toString().equalsIgnoreCase("high")||y.toString().equalsIgnoreCase("low") ||y.toString().equalsIgnoreCase("normal") )
					{
						if(m.charAt(m.length()-1)=='/')
						{m +=  y.toLowerCase() ;}
						else
						{
							m += " -> "+ y.toLowerCase()+"" ;
						}

					}
					else if(y.toString().equalsIgnoreCase("safe") || y.toString().equalsIgnoreCase("unsafe"))
						m += "\t\tState - "+counter+" -->  Transition : [ Action : null , guard: null ] ; clock: x = "+clock+" ; Next State: state-"+counter + "\n";
					else if(y.toString().equals("=")||y.toString().equals(">") || y.toString().equals("<") || y.toString().equals("<=") || y.toString().equals(">="))
						m +=  s  ;
					else
						m= m.substring(0, m.length())+"/";
				}
			}
		}


	}
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	private void writeToOutputFile() {


		while (hasNext()) {
			String t= next().getType().getIdentifier() + " ";
			tkn+=t;
		}

	}

	public void print()
	{
		while(!tokenBuffer.isEmpty())
		{
			System.out.println(tokenBuffer.poll());
		}
	}

	/**
	 * Determine if the lexer has anything left to parse.
	 * @return Return true when the Scanner has items left or the tokenBuffer is not empty
	 */
	private boolean hasNext() {
		return scanner.hasNext() || !tokenBuffer.isEmpty();
	}

	/**
	 * Step through the Scanner until the tokenBuffer contains something.
	 * @return Return the first Token inserted in the tokenBuffer
	 */
	private Token next() {
		if (!scanner.hasNext() && tokenBuffer.isEmpty()) {
			return null;
		}

		while (tokenBuffer.isEmpty()) {
			fillTokenBuffer(scanner.next());
		}

		return tokenBuffer.remove();
	}

	public String getOutputFile() {
		return tkn;
	}
}

