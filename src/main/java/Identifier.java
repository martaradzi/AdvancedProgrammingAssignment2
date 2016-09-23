
public class Identifier implements IdentifierInterface {

	String id;
	
	Identifier(String s) {
		id = s;
	}
	
	@Override
	public String toString() {
		return id;
	}

}
