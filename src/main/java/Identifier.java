
public class Identifier implements IdentifierInterface {

	String variableName;
	
	Identifier(String s) {
		variableName = s;
	}
	
	@Override
	public String getValue() {
		return variableName;
	}

}
