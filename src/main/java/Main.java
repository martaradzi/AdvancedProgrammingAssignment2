import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class Main {
	
	final static char PRINT_SIGN = '?',
			COMMENT_SIGN = '/',
			EQUAL_SIGN = '=',
			SET_OPEN = '{',
			SET_CLOSE = '}',
			COMPLEX_FACTOR_OPEN = '(',
			COMPLEX_FACTOR_CLOSE = ')',
			UNION_SIGN = '+',
			INTERSECTION_SIGN = '*',
			COMPLEMENT_SIGN = '-',
			SYMMETRIC_DIFFERENCE_SIGN = '|',
			COMA_SIGN = ',';

	HashMap<Identifier, Set<BigInteger>> hashMap;

	Main() {
		hashMap = new HashMap<Identifier, Set<BigInteger>>();
	}

	void program(Scanner input) throws APException {
		statement(input);
	}

	void statement(Scanner input) throws APException {
		if (nextCharIsLetter(input)) {
			assignment(input);
		} else if (nextCharIs(input, PRINT_SIGN)) {
			printStatement(input);
		} else if (nextCharIs(input, COMMENT_SIGN)) {
			comment(input);
		}
	}

	void assignment(Scanner input) throws APException {
		Identifier i = identifier(input);

		character(input, ' ');
		character(input, EQUAL_SIGN);
		character(input, ' ');

		Set<BigInteger> b = expression(input);

		hashMap.put(i, b);
		//System.out.println("debug line");
	}

	void printStatement(Scanner input) throws APException {
		character(input, PRINT_SIGN);
		character(input, ' ');
		Set<BigInteger> b = expression(input);
		System.out.println(b);
	}

	void comment(Scanner input) throws APException {
		// DO NOTHING
	}

	Identifier identifier(Scanner input) throws APException {
		StringBuffer sb = new StringBuffer();
		sb.append(letter(input));

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
		Set<BigInteger> termOne = new Set<>(), result = new Set<>();

		termOne = term(input);

		while (nextCharIs(input, UNION_SIGN) || nextCharIs(input, COMPLEMENT_SIGN) || nextCharIs(input, SYMMETRIC_DIFFERENCE_SIGN)) {
			if (nextCharIs(input, UNION_SIGN)) {
				character(input, UNION_SIGN);
				result = termOne.union(term(input));
			} else if (nextCharIs(input, COMPLEMENT_SIGN)) {
				character(input, COMPLEMENT_SIGN);
				result = termOne.complement(term(input));
			} else if (nextCharIs(input, SYMMETRIC_DIFFERENCE_SIGN )) {
				character(input, SYMMETRIC_DIFFERENCE_SIGN );
				result = termOne.symmetricDifference(term(input));
			}

			return result;
		}

		return termOne;
	}

	Set<BigInteger> term(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>(), factor = new Set<>();

		factor = factor(input);

		while (nextCharIs(input, INTERSECTION_SIGN)) {
			character(input, INTERSECTION_SIGN);
			result = factor.intersect(factor(input));
			factor = result;
		}

		return factor;
	}

	Set<BigInteger> factor(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		if (nextCharIsLetter(input)) {
			result = hashMap.get(identifier(input));
		} else if (nextCharIs(input, COMPLEX_FACTOR_OPEN)) {
			result = complexFactor(input);
		} else if (nextCharIs(input, SET_OPEN)) {
			result = set(input);
		}

		return result;
	}

	Set<BigInteger> complexFactor(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		character(input, COMPLEX_FACTOR_OPEN);
		result = (expression(input));
		character(input, COMPLEX_FACTOR_CLOSE);

		return result;
	}

	Set<BigInteger> set(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		character(input, SET_OPEN);
		result = rowNaturalNumbers(input);
		character(input, SET_CLOSE);

		return result;
	}

	Set<BigInteger> rowNaturalNumbers(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		if (nextCharIsDigit(input)) {
			result.add(naturalNumber(input));

			while (nextCharIs(input, COMA_SIGN)) {
				character(input, COMA_SIGN);
				result.add(naturalNumber(input));
			}
		}

		return result;
	}

//	char additiveOperator(Scanner input) throws APException {
//		if (!nextCharIs(input, (char) (UNION_SIGN | SYMMETRIC_DIFFERENCE_SIGN | COMPLEMENT_SIGN))) {
//			throw new APException("Expected operator +, -, or |");
//		}
//
//		return nextChar(input);
//	}

//	char multipicativeOperator(Scanner input) throws APException {
//		if (!nextCharIs(input, '*')) {
//			throw new APException("Expected operator *");
//		}
//
//		return nextChar(input);
//	}

	BigInteger naturalNumber(Scanner input) throws APException {
		BigInteger result;

		if (nextCharIsDigit(input) && !nextCharIs(input, '0')) {
			result = new BigInteger(positiveNumber(input));
		} else {
			result = BigInteger.valueOf(zero(input));
		}

		return result;
	}

	String positiveNumber(Scanner input) throws APException {
		String s = "";

		while (nextCharIsDigit(input)) {
			s = s + number(input);
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
			throw new APException("Next char should be zero");
		}

		return nextChar(input);
	}

	char notZero(Scanner input) throws APException {
		if (nextCharIs(input, '0')) {
			throw new APException("Next char should not be zero");
		}

		return nextChar(input);
	}

	/////////////
	boolean nextCharIsLetter(Scanner in) throws APException {
		in.useDelimiter("");
		return in.hasNext("[a-zA-Z]");

	}

	boolean nextCharIsDigit(Scanner in) {
		in.useDelimiter("");

		return in.hasNext("[0-9]");
	}

	/////////////
	boolean nextCharIs(Scanner input, char c) {
		input.useDelimiter("");

		return input.hasNext(Pattern.quote(c + ""));
	}

	char letter(Scanner input) throws APException {
		if (!nextCharIsLetter(input)) {
			throw new APException("Next char should be a letter");
		}

		return nextChar(input);
	}

	char nextChar(Scanner in) {
		in.useDelimiter("");

		return in.next().charAt(0);
	}

	void character(Scanner input, char c) throws APException {
		if (!nextCharIs(input, c)) {
			throw new APException("Next char should be: " + c);
		}

		nextChar(input);
	}

	void start() throws APException {
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			String s = in.nextLine();
			Scanner line = new Scanner(s);
			program(line);
		}
	}

	public static void main(String[] argv) throws APException {
		new Main().start();
	}
}