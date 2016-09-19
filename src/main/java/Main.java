import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;

public class Main {
	HashMap<Identifier, String> setList;
	
	Main() {
		setList = new HashMap<Identifier, String>();
	}
	
	public void parseInput(String input) {
		Scanner in = new Scanner(input);
		
		Identifier id = new Identifier(in.next());
		String data = in.next();
		
		setList.put(id, data);
		System.out.println(setList.get(id));
	}
	
	void start() {
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			String input = in.nextLine();
			parseInput(input);
		}
	}

	static public void main(String[] args) {
		new Main().start();
	}
}
