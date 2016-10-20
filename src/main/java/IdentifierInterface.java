/**
 * @elements Characters of type char
 * @structure Linear
 * @domain Must start with a letter and must not be empty. Can contain alphanumeric characters
 *
 * @constructor - Identifier(String s);
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>	String is not empty, begins with a letter, and only contains alphanumeric characters.
 *		<dt><b>POST-conditie</b><dd> The new Identifier-object is created.
 * </dl>
 **/
public interface IdentifierInterface {
	
	
	/**
     * @pre -
     * @post The value associated with this identifier has been returned as a String.
     */
    String toString();
    
    /**
     * @pre -
     * @post The hashcode value has been returned.
     */
    int hashCode();
    
    /**
     * @pre -
     * @post TRUE: The two objects being compared are of the same type.
     * 			FALSE: The two objects being compared are not of the same type.
     */
    boolean equals(Object obj);
}
