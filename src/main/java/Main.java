import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;

public class Main {

	private void start() {
		// Create a scanner on System.in

		// While there is input, read line and parse it.
		
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
