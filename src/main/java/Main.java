import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;

public class Main {

	private void start() {
		// Create a scanner on System.in

		// While there is input, read line and parse it.
		
		BigInteger bi1 = BigInteger.valueOf(10);
		BigInteger bi2 = BigInteger.valueOf(20);
		BigInteger bi3 = BigInteger.valueOf(30);
		
		Set<BigInteger> s = new Set<BigInteger>();
		Set<BigInteger> s2 = new Set<BigInteger>();
		Set<BigInteger> s3 = new Set<BigInteger>();
		
		s.add(bi1);
		s.add(bi2);
		s2.add(bi3);
		
		s3 = s.union(s2);
		
		System.exit(0);
		
		
		
		
		
		
		
//		Scanner in = new Scanner(System.in);
//
//		while (in.hasNext()) {
//			BigInteger i = in.nextBigInteger();
//			s.add(i);
//		}
		
	}

	public static void main(String[] argv) {
		new Main().start();
	}
}
