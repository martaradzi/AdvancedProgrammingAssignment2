/**
 * @elements Sets of the type Set
 * @structure None
 * @domain Any elements of type E
 */
public interface SetInterface<E> {

	/**
	 * @pre -
	 * @post A new set is returned containing all elements in both sets. The new
	 *       set contains no duplicate elements.
	 */
	E union(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing only elements that exist in both
	 *       sets and are of equal value.
	 */
	E intersect(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing elements that exist in one set and not the other.
	 */
	E complement(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing only the elements resulting in the
	 *       complement of the union and intersection of both sets.
	 */
	E symmetricDifference(E e);
	
	/**	@pre -
     *  @post - FALSE: list is not empty.
     *  				TRUE:  list is empty.
     **/
    boolean isEmpty();

    /**	@pre -
     *	@post - The number of elements has been returned.
     **/
    int size();

    /** @pre -
     *	@post - Element d has been added to List-PRE.
     *    				current points to the newly added element.
     *   				list-POST has been returned.
     **/
    E insert(E d);


    /** @pre - The list is not empty.
     *	@post - The value of the current element has been returned.
     */
    E retrieve();


    /** @pre - The list is not empty.
     * 	@post - The current element of list-PRE is not present in list-POST.
     * 	    			current-POST points to
     *    					- if list-POST is empty
     *   						null
     *   					- if list-POST is not empty
     *     						if current-PRE was the last element of list-PRE
     *     							the last element of list-POST
     *     						else
     *     							the element after current-PRE
     *  				list-POST has been returned.
     **/
    E remove();


    /** @pre -
     *	@post - TRUE:  The list contains the element d.
     *	     			current-POST points to the first element in list that
     *	     			contains the element d.
     *     				FALSE: list does not contain the element d.
     *	     			current-POST points to
     *	      				- if list-POST is empty
     *                    		null
     *	      				- if the first element in list > d:
     *                    		the first element in list
     *        				else
     *	    					the last element in list with value < d
     **/
    boolean find(E d);


    /** @pre -
     *	@post - FALSE: list is empty
     *    				TRUE:  current points to the first element
     **/
    boolean goToFirst();


    /**	@pre -
     *	@post - FALSE: list is empty
     *     				TRUE:  current points to the last element
     */
    boolean goToLast();


    /** @pre -
     *	@post - FALSE: list is empty or current points to the last element
     *     				TRUE:  current-POST points to the next element of current-PRE
     */
    boolean goToNext();


    /** @pre -
     *	@post - FALSE: list is empty of current points to the first element
     *     				TRUE:  current-POST points to the prior element of current-PRE
     */
    boolean goToPrevious();
}
