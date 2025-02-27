/**
 * @elements Objects of type E
 * @structure None
 * @domain Any elements of type E
 * @constructor - List();
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>		-
 *		<dt><b>POST-conditie</b><dd> 	The new Set-object is the empty set.
 * </dl>
 */
public interface SetInterface<E extends Comparable<E>> {
	/**
	 * @pre -
	 * @post A new set is returned containing all elements of both sets.
	 */
	SetInterface<E> union(SetInterface<E> set);

	/**
	 * @pre -
	 * @post A new set is returned containing only the common elements of both sets.
	 */
	SetInterface<E> intersect(SetInterface<E> set);

	/**
	 * @pre -
	 * @post A new set is returned containing elements that exist in one set and not the other.
	 */
	SetInterface<E> complement(SetInterface<E> set);

	/**
	 * @pre -
	 * @post A new set is returned containing the elements that are unique to both sets.
	 */
	SetInterface<E> symmetricDifference(SetInterface<E> set);
	
	/**
	 * 	@pre -
     *  @post - FALSE: set is not empty.
     *  		TRUE:  set is empty.
     **/
    boolean isEmpty();

    /**
     * 	@pre -
     *	@post - The number of elements in the set has been returned.
     **/
    int size();

    /**
	 * @pre - 
	 * @post - Element d exists in the set.
	 **/
    void add(E d);


    /**
     *  @pre - The set is not empty.
     *	@post - An element from the set has been returned.
     */
    E get();


    /**
     *  @pre - The set is not empty.
     * 	@post - Element d in set-PRE is not present in set-POST.
     *  				Set-POST has been returned.
     **/
    void remove(E d);


    /**
     *  @pre - 
     *	@post - TRUE:  The set contains the element d.
     *     				FALSE: The set is empty or does not contain the element d.
     **/
    boolean contains(E d);

    /**
	 * @pre - 
	 * @post - Returns a copy of the set.
	 **/
	SetInterface<E> copy();
	
	/**
	 * @pre - 
	 * @post - Returns the elements of a set as a String.
	 **/
	String toString();
}
