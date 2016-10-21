import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class Main {
	/*
	 * Course: Advanced Programming
	 * Assignment 2: Set Calculator
	 * Created by: Marta Radziszewska (2575428), Shane Minnema (2572028)
	 * Date: 21/10/2016
	 */

	final static char PRINT_SIGN = '?', COMMENT_SIGN = '/', EQUAL_SIGN = '=', SET_OPEN = '{', SET_CLOSE = '}',
			COMPLEX_FACTOR_OPEN = '(', COMPLEX_FACTOR_CLOSE = ')', UNION_SIGN = '+', INTERSECTION_SIGN = '*',
			COMPLEMENT_SIGN = '-', SYMMETRIC_DIFFERENCE_SIGN = '|', COMA_SIGN = ',';

	HashMap<Identifier, Set<BigInteger>> hashMap;

	Main() {
		hashMap = new HashMap<Identifier, Set<BigInteger>>();
	}

	void program() throws APException {
		Scanner in = new Scanner(System.in);

		while (in.hasNextLine()) {
			try {
				String s = in.nextLine();
				Scanner lineScanner = new Scanner(s);
				statement(lineScanner);
			} catch (APException e) {
				System.out.println(e);
			}
		}
	}

	void statement(Scanner input) throws APException {
		readWhiteSpaces(input);

		if (nextCharIsLetter(input)) {
			assignment(input);
		} else if (nextCharIs(input, PRINT_SIGN)) {
			printStatement(input);
		} else if (nextCharIs(input, COMMENT_SIGN)) {
			comment(input);
		} else {
			throw new APException("error: no statement");
		}
	}

	void assignment(Scanner input) throws APException {
		Identifier i = identifier(input);

		readWhiteSpaces(input);
		character(input, EQUAL_SIGN);
		readWhiteSpaces(input);

		Set<BigInteger> b = expression(input);

		eoln(input);
		hashMap.put(i, b);

	}

	void printStatement(Scanner input) throws APException {
		character(input, PRINT_SIGN);
		readWhiteSpaces(input);

		Set<BigInteger> set = expression(input);

		if (set == null) {
			throw new APException("error: undefined variable");
		}

		eoln(input);
		System.out.println(set.toString());
	}

	void comment(Scanner input) throws APException {
		input.nextLine();
		eoln(input);
	}

	Identifier identifier(Scanner input) throws APException {
		StringBuffer sb = new StringBuffer();
		readWhiteSpaces(input);

		while (nextCharIsLetter(input) || nextCharIsDigit(input)) {
			if (nextCharIsLetter(input)) {
				sb.append(letter(input));
			} else {
				sb.append(number(input));
			}
		}

		return new Identifier(sb.toString());
	}

	Set<BigInteger> expression(Scanner input) throws APException {
		Set<BigInteger> firstTerm = term(input);

		readWhiteSpaces(input);

		while (additiveOperator(input)) {
			char operator = nextChar(input);
			Set<BigInteger> set2 = term(input);
			readWhiteSpaces(input);

			if (set2 == null) {
				return null;
			}

			if (operator == UNION_SIGN) {
				readWhiteSpaces(input);
				firstTerm = firstTerm.union(set2);
			} else if (operator == COMPLEMENT_SIGN) {
				readWhiteSpaces(input);
				firstTerm = firstTerm.complement(set2);
			} else if (operator == SYMMETRIC_DIFFERENCE_SIGN) {
				readWhiteSpaces(input);
				firstTerm = firstTerm.symmetricDifference(set2);
			}
		}

		return firstTerm;
	}

	Set<BigInteger> term(Scanner input) throws APException {
		Set<BigInteger> factor = factor(input);

		readWhiteSpaces(input);

		while (multiplicativeOperator(input)) {
			character(input, INTERSECTION_SIGN);
			Set<BigInteger> set2 = factor(input);
			readWhiteSpaces(input);

			factor = factor.intersect(set2);
		}

		return factor;
	}

	Set<BigInteger> factor(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		readWhiteSpaces(input);

		if (nextCharIsLetter(input)) {
			Identifier i = identifier(input);
			result = getSetFromHashMap(i);
		} else if (nextCharIs(input, COMPLEX_FACTOR_OPEN)) {
			result = complexFactor(input);
		} else if (nextCharIs(input, SET_OPEN)) {
			result = set(input);
		} else {
			throw new APException("error: incomplete statement");
		}

		return result;
	}

	Set<BigInteger> getSetFromHashMap(Identifier i) throws APException {
		if (!hashMap.containsKey(i) || hashMap.get(i) == null) {
			throw new APException("error: undefined variable");
		}

		return hashMap.get(i);
	}

	Set<BigInteger> complexFactor(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		readWhiteSpaces(input);
		character(input, COMPLEX_FACTOR_OPEN);
		result = (expression(input));
		readWhiteSpaces(input);
		character(input, COMPLEX_FACTOR_CLOSE);

		return result;
	}

	Set<BigInteger> set(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		readWhiteSpaces(input);
		character(input, SET_OPEN);
		result = rowNaturalNumbers(input);
		readWhiteSpaces(input);
		character(input, SET_CLOSE);

		return result;
	}

	Set<BigInteger> rowNaturalNumbers(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		readWhiteSpaces(input);

		if (nextCharIsDigit(input)) {
			result.add(naturalNumber(input));
			readWhiteSpaces(input);

			while (nextCharIs(input, COMA_SIGN)) {
				character(input, COMA_SIGN);
				readWhiteSpaces(input);
				result.add(naturalNumber(input));
			}
		}

		return result;
	}

	boolean additiveOperator(Scanner input) throws APException {
		return nextCharIs(input, UNION_SIGN) || nextCharIs(input, COMPLEMENT_SIGN)
				|| nextCharIs(input, SYMMETRIC_DIFFERENCE_SIGN);
	}

	boolean multiplicativeOperator(Scanner input) throws APException {
		return nextCharIs(input, INTERSECTION_SIGN);
	}

	BigInteger naturalNumber(Scanner input) throws APException {
		readWhiteSpaces(input);

		if (nextCharIsDigit(input)) {
			return new BigInteger(positiveNumber(input));
		}

		throw new APException("error: number expected");
	}

	String positiveNumber(Scanner input) throws APException {
		String s = "";

		while (nextCharIsDigit(input)) {
			s = s + number(input);

			if (s.startsWith("0") && s.length() > 1) {
				throw new APException("error: number expected");
			}
		}

		return s;
	}

	char number(Scanner input) throws APException {
		if (nextCharIs(input, '0')) {
			return zero(input);
		}

		return notZero(input);
	}

	char zero(Scanner input) throws APException {
		if (!nextCharIs(input, '0')) {
			throw new APException("error: number expected");
		}

		return nextChar(input);
	}

	char notZero(Scanner input) throws APException {
		if (nextCharIs(input, '0')) {
			throw new APException("error: number expected");
		}

		return nextChar(input);
	}

	boolean nextCharIsLetter(Scanner input) throws APException {
		input.useDelimiter("");

		return input.hasNext("[a-zA-Z]");
	}

	boolean nextCharIsDigit(Scanner input) throws APException {
		input.useDelimiter("");

		return input.hasNext("[0-9]");
	}

	boolean nextCharIs(Scanner input, char c) throws APException {
		input.useDelimiter("");

		return input.hasNext(Pattern.quote(c + ""));
	}

	char letter(Scanner input) throws APException {
		return nextChar(input);
	}

	char nextChar(Scanner input) throws APException {
		input.useDelimiter("");

		return input.next().charAt(0);
	}

	void character(Scanner input, char c) throws APException {
		if (!nextCharIs(input, c)) {
			throw new APException("error: missing '" + c + "'");
		}

		nextChar(input);
	}

	boolean eoln(Scanner input) throws APException {
		if (input.hasNext()) {
			throw new APException("error: no end of line");
		}

		return true;
	}

	void readWhiteSpaces(Scanner input) throws APException {
		while (nextCharIs(input, ' ')) {
			character(input, ' ');
		}
	}

	public static void main(String[] argv) throws APException {
		new Main().program();
	}
}