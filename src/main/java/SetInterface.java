/**
 * @elements Sets of the type Set
 * @structure None
 * @domain Any elements of type E
 */
public interface SetInterface<E extends Comparable<E>> {

	/**
	 * @pre -
	 * @post A new set is returned containing all elements of both sets.
	 */
	E union(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing only elements that exist in both
	 *       sets.
	 */
	E intersect(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing elements that exist in one set and not the other.
	 */
	E complement(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing the elements resulting in the
	 *       complement of the union and intersection of both sets.
	 */
	E symmetricDifference(E e);
	
	/**	@pre -
     *  @post - FALSE: set is not empty.
     *  		TRUE:  set is empty.
     **/
    boolean isEmpty();

    /**	@pre -
     *	@post - The number of elements in the set has been returned.
     **/
    int size();

    /**
	 * @pre - 
	 * @post - Element d exists in the set.
	 **/
    void add(E d);


    /** @pre - The set is not empty.
     *	@post - An element from the set has been returned.
     */
    E get();


    /** @pre - The set is not empty.
     * 	@post - Element d in set-PRE is not present in set-POST.
     *  				Set-POST has been returned.
     **/
    E remove(E d);


    /** @pre - 
     *	@post - TRUE:  The set contains the element d.
     *     				FALSE: The set is empty or does not contain the element d.
     **/
    boolean contains(E d);

}
