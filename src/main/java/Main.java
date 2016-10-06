import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class Main {

	HashMap hashMap;

	Main(){
		hashMap = new HashMap();
	}


	void program(Scanner input) throws APException {
		while(input.hasNextLine()){
			statement(input);
		}
	}

	void statement(Scanner input)throws APException{
		//Set<BigInteger> result = new Set<BigInteger>();

		if(nextCharIsLetter(input)){
			assignment(input);
		}else if(nextCharIs(input, '?')){
			printStatement(input);
		}else if(nextCharIs(input, '/')){
			comment(input);
		}
	}

	void assignment(Scanner input)throws APException{
		identifier(input);
		character(input, '=');
		expression(input);
		hashMap.put(identifier(input), expression(input));
		eoln(input);
	}

	void printStatement(Scanner input)throws APException{
		character(input, '?');
		expression(input);
		eoln(input);

	}


	void comment(Scanner input)throws APException{

		//DO NOTHING

	}

	StringBuffer identifier(Scanner input)throws APException{
		StringBuffer result = new StringBuffer();
		result.append(letter(input));
		while(nextCharIsLetter(input) | nextCharIsDigit(input)){
			if(nextCharIsLetter(input)){
				result.append(letter(input));
			}else{
				result.append(number(input));
			}
		}
		return result;
	}


	Set<BigInteger> expression(Scanner input) throws APException{
		Set<BigInteger> result = new Set<BigInteger>();
		Set<BigInteger> termOne = new Set<BigInteger>();
		termOne = term(input);
		while(nextCharIs(input, additiveOperator(input))){
			if(nextCharIs(input, '+')){
				character(input,'+');
				result = termOne.union(term(input));
			}else if(nextCharIs(input, '-')){
				character(input, '-');
				result = termOne.complement(term(input));
			}else{
				character(input, '|');
				result = termOne.symmetricDifference(term(input));
			}
			result = termOne;
		}
		return result;
	}


	Set<BigInteger> term(Scanner input)throws APException{
		Set<BigInteger> result = new Set<BigInteger>();
		Set<BigInteger> factor = new Set<BigInteger>();
		factor = factor(input);
		while(nextCharIs(input, multipicativeOperator(input))){
			character(input, '*');
			result = factor.intersect(factor(input));
			factor = result;
		}
		return result;
	}

	Set<BigInteger> factor(Scanner input)throws APException{
		Set<BigInteger> result = new Set<BigInteger>();
		if(nextCharIsLetter(input)){
			result = (Set<BigInteger>) (hashMap.get(identifier(input)));
		}else if(nextCharIs(input, '(')){
			result = complexFactor(input);
		}else if(nextCharIs(input, '{')){
			result = set(input);
		}
		return result;
	}

	Set<BigInteger> complexFactor(Scanner input)throws APException{
		Set<BigInteger> result = new Set<BigInteger>();
		character(input, '(');
		result = (expression(input));
		character(input, ')');
		return result;
	}

	Set<BigInteger> set(Scanner input) throws APException{
		Set<BigInteger> result = new Set<BigInteger>();
		character(input, '{');
		result = rowNaturalNumbers(input);
		character(input, '}');
		return result;
	}


	Set<BigInteger> rowNaturalNumbers(Scanner input) throws APException{
		Set<BigInteger> result = new Set<BigInteger>();
		if(nextCharIsDigit(input)){
			result.add(naturalNumber(input));
			while(nextCharIs(input, ',')){
				character(input, ',');
				result.add(naturalNumber(input));
			}
		}
		return result;
	}

	char additiveOperator(Scanner input)throws APException{
		if(!nextCharIs(input, (char)('+' | '|' | '-'))){
			throw new APException("............");
		}
		return nextChar(input);
	}

	char multipicativeOperator(Scanner input) throws APException{
		if(!nextCharIs(input, '*')){
			throw new APException("..........");
		}
		return nextChar(input);
	}


	BigInteger naturalNumber(Scanner input) throws APException{
		BigInteger result;
		if(nextCharIsDigit(input) && !nextCharIs(input, '0')){
			result =  new BigInteger(positiveNumber(input));
		}else{
			result = BigInteger.valueOf(zero(input));
		}	
		return result;
	}

	String positiveNumber(Scanner input)throws APException{
		String s = "" + notZero(input);
		while(nextCharIsDigit(input)){
			s = s + number(input);
		}
		return s;
	}

	char number(Scanner input) throws APException{
		if(nextCharIs(input, zero(input))){
			return zero(input);
		}else{
			return notZero(input);
		}
	}

	char zero(Scanner input) throws APException{
		if(! nextCharIs(input, '0')){
			throw new APException(".......");
		}
		return nextChar(input);
	}

	char notZero(Scanner input) throws APException{
		if(!nextCharIs(input, '0')){
			throw new APException(".......");
		}
		return nextChar(input);
	}

	boolean nextCharIsLetter (Scanner in) {
		return Character.isLetter(nextChar(in));
	}

	boolean nextCharIsDigit (Scanner in) {
		return Character.isDigit(nextChar(in));
	}

	boolean nextCharIs(Scanner input, char c){
		return input.hasNext(Pattern.quote(c + " "));
	}

	char letter (Scanner input) throws APException {
		if (! nextCharIsLetter(input)) {
			throw new APException("........");
		}
		return nextChar(input);
	}

	char nextChar (Scanner in) {
		return in.next().charAt(0);
	}

	void character (Scanner input, char c) throws APException {
		if (! nextCharIs(input, c)) {
			throw new APException("........");
		}
		nextChar(input);
	}

	void eoln (Scanner input) throws APException {
		if (input.hasNext()) {
			throw new APException("........");
		}
	}


	private void start() {
		// Create a scanner on System.in

		// While there is input, read line and parse it.
		
		BigInteger bi1 = BigInteger.valueOf(10);
		BigInteger bi2 = BigInteger.valueOf(20);
		BigInteger bi3 = BigInteger.valueOf(30);
		BigInteger bi4 = BigInteger.valueOf(40);
		
		Set<BigInteger> s = new Set<BigInteger>();
		Set<BigInteger> s2 = new Set<BigInteger>();
		Set<BigInteger> result = new Set<BigInteger>();
		
		s.add(bi1);
		s.add(bi2);
		s.add(bi3);
		
		s2.add(bi1);
		s2.add(bi2);
		s2.add(bi4);
		
		result = s.symmetricDifference(s2);
		
		result.list.goToFirst();
		
		while (!result.isEmpty()) {
			System.out.println(result.get());
			result.remove(result.get());
		}
		
		System.exit(0);
		
//		Scanner in = new Scanner(System.in);
//
//		while (in.hasNext()) {
//			BigInteger i = in.nextBigInteger();
//			s.add(i);
//		}
		

		List<BigInteger> l = new List<BigInteger>();
		l.init();

		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			BigInteger i = in.nextBigInteger();
			l.insert(i);
		}


	}

	public static void main(String[] argv) {
		new Main().start();
	}
}
