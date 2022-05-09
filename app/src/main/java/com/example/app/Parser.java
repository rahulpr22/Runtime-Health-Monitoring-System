package com.example.app;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

@Deprecated

public class Parser {
	private Grammar grammar;
	private ParsingTable parsingTable;
	private Lexer lexer;
	private Scanner lexerOutputScanner;
	public static boolean VERBOSE = false;
	public static String stk="";

	public static String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	static String map="";
	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public static boolean verifyProperty(InputStream dsl, InputStream test) {
		//run(dsl, test);
		//System.out.println(map);

		return run(dsl, test);
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public static boolean run(InputStream dsl, InputStream test) {
	boolean ans=false;
		Scanner dslscanner = new Scanner(dsl);
		Scanner testscanner = new Scanner(test);
		VERBOSE = true;
			
		Parser parser = new Parser(dslscanner,testscanner);
		try {
			ans=parser.parse();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ans;
	}

	
	/**
	 * Constructs a new LL1 Parser. Creates the grammar, parsing table, and the lexer.
	 * @param grammarFile Grammar file
	 * @param fileToParse Sample file of the language
	 */
 	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public Parser(Scanner grammarFile, Scanner fileToParse) {
		try {
			grammar = new Grammar(grammarFile);
		} catch (GrammarException e) {
			e.printStackTrace();
		}

		if (VERBOSE) {
			//System.out.println(grammar);
		}

		parsingTable = new ParsingTable(grammar);
		lexer = new Lexer(fileToParse);

		InputStream stream = new ByteArrayInputStream(lexer.getOutputFile().getBytes(StandardCharsets.UTF_8));

		lexerOutputScanner = new Scanner(stream);
		/*try {
			grammar = new Grammar(grammarFile);
		} catch (GrammarException e) {
			e.printStackTrace();
		}
		
		if (VERBOSE) {
			// System.out.println(grammar);
		}
		
		parsingTable = new ParsingTable(grammar);
		lexer = new Lexer(fileToParse);

		map=lexer.getMapping();

		InputStream stream = new ByteArrayInputStream(lexer.getOutputFile().getBytes(StandardCharsets.UTF_8));
		lexerOutputScanner = new Scanner(stream);
		Scanner sc = lexerOutputScanner;
		while(sc.hasNext())
		{
			String p=sc.next();
		}*/

	}
	
	/**
	 * Parse the sample file.
	 * @return Boolean of success
	 * @throws ParseException
	 */
	public boolean parse() throws ParseException {
		Stack<RuleElement> parsingStack = new Stack<RuleElement>();
		parsingStack.push(grammar.getStartVariable());

		TokenType tokenType = TokenType.T_END_INPUT;
		if (lexerOutputScanner.hasNext()) {
			tokenType = TokenType.tokenWithIdentifier(lexerOutputScanner.next());
		}

		while (!parsingStack.isEmpty()) {
			if (VERBOSE) {
				stk += parsingStack +"\n";
			}
			RuleElement re = parsingStack.pop();
			if (VERBOSE) {
				stk+= "Popped " + re + "\n";
			}
			if (re instanceof Variable) {
				Variable v = (Variable)re;
				List<RuleElement> newRuleElements = parsingTable.getRuleElements(v, tokenType);

				if (newRuleElements != null) {
					for (int i = newRuleElements.size() - 1; i >= 0; i--) {
						parsingStack.push(newRuleElements.get(i));
					}
				} else {
					throw new ParseException("Parsing failed with token of type " + tokenType + " and variable " + re);
				}
			} else if (re instanceof Terminal && !(((Terminal)re).isEmptyString())) {
				Terminal terminal = (Terminal)re;
				TokenType parsingStackTokenType = TokenType.tokenWithIdentifier(terminal.toString());
				if (parsingStackTokenType.equals(tokenType)) {
					if (parsingStack.isEmpty()) {
						// if token is matched and parsing stack is empty, no need to go further
						break;
					}

					if (VERBOSE) {
						stk += "Parsed " + tokenType +"\n";
					}

					if (lexerOutputScanner.hasNext()) {
						tokenType = TokenType.tokenWithIdentifier(lexerOutputScanner.next());
					} else {
						tokenType = TokenType.T_END_INPUT;
					}
				} else {
					throw new ParseException("Unexpected " + tokenType + " (expected "+ parsingStackTokenType + ")");
				}
			}
		}

		if (lexerOutputScanner.hasNext()) {
			throw new ParseException("Parsing ended with input remaining");
		}

		System.out.println("Valid input Property!");
		return true;
	}
}
/*
try
		{
			File file= new File("./test.txt");
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String line = "";
			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(dsl));
			while (true) {
				try {
					if ((line = reader.readLine()) == null) break;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				stringBuilder.append(line).append("\n");

				BufferedWriter writer = new BufferedWriter(new FileWriter("./testProperty.txt"));
				writer.write(stringBuilder.toString());
				writer.close();
				//run("./testProperty.txt");
			}
			fr.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
 */