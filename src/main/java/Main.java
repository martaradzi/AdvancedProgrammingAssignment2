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
		
	}

	public static void main(String[] argv) {
		new Main().start();
	}
}
