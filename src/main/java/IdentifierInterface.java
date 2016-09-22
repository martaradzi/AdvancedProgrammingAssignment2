/**
 * @elements Characters of type char
 * @structure Linear
 * @domain One String
 */
public interface IdentifierInterface {
	
	/**
     * @pre -
     * @post The value associated with this identifier has been returned as a String.
     */
    String getValue();
}
