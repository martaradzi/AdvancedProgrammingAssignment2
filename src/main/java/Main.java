import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class Main {

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
		} else if (nextCharIs(input, '?')) {
			printStatement(input);
		} else if (nextCharIs(input, '/')) {
			comment(input);
		}
	}

	void assignment(Scanner input) throws APException {
		Identifier i = identifier(input);

		character(input, ' ');
		character(input, '=');
		character(input, ' ');

		Set<BigInteger> b = expression(input);

		hashMap.put(i, b);
		//System.out.println("debug line");
	}

	void printStatement(Scanner input) throws APException {
		character(input, '?');
		character(input, ' ');
		Set<BigInteger> b = expression(input);
		
		System.out.println(b.get());
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

		while (nextCharIs(input, '+') || nextCharIs(input, '-') || nextCharIs(input, '|')) {
			if (nextCharIs(input, '+')) {
				character(input, '+');
				result = termOne.union(term(input));
			} else if (nextCharIs(input, '-')) {
				character(input, '-');
				result = termOne.complement(term(input));
			} else if (nextCharIs(input, '|')) {
				character(input, '|');
				result = termOne.symmetricDifference(term(input));
			}

			return result;
		}

		return termOne;
	}

	Set<BigInteger> term(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>(), factor = new Set<>();

		factor = factor(input);

		while (nextCharIs(input, '*')) {
			character(input, '*');
			result = factor.intersect(factor(input));
			factor = result;
		}

		return factor;
	}

	Set<BigInteger> factor(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		if (nextCharIsLetter(input)) {
			result = hashMap.get(identifier(input));
		} else if (nextCharIs(input, '(')) {
			result = complexFactor(input);
		} else if (nextCharIs(input, '{')) {
			result = set(input);
		}

		return result;
	}

	Set<BigInteger> complexFactor(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		character(input, '(');
		result = (expression(input));
		character(input, ')');

		return result;
	}

	Set<BigInteger> set(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		character(input, '{');
		result = rowNaturalNumbers(input);
		character(input, '}');

		return result;
	}

	Set<BigInteger> rowNaturalNumbers(Scanner input) throws APException {
		Set<BigInteger> result = new Set<>();

		if (nextCharIsDigit(input)) {
			result.add(naturalNumber(input));

			while (nextCharIs(input, ',')) {
				character(input, ',');
				result.add(naturalNumber(input));
			}
		}

		return result;
	}

	char additiveOperator(Scanner input) throws APException {
		if (!nextCharIs(input, (char) ('+' | '|' | '-'))) {
			throw new APException("Expected operator +, -, or |");
		}

		return nextChar(input);
	}

	char multipicativeOperator(Scanner input) throws APException {
		if (!nextCharIs(input, '*')) {
			throw new APException("Expected operator *");
		}

		return nextChar(input);
	}

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