/**
 * @elements Sets of the type Set
 * @structure Linear
 * @domain One list of integers
 */
public interface SetInterface<E> {

	/**
	 * @pre -
	 * @post A new set is returned as a copy of 'e'.
	 */
	E copy(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing all elements in both sets. The new
	 *       set contains no duplicate elements.
	 */
	E union(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing only elements of equal value that
	 *       exist in both sets. The new set contains no duplicate elements.
	 */
	E intersect(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing only elements that are unique to
	 *       each set.
	 */
	E complement(E e);

	/**
	 * @pre -
	 * @post A new set is returned containing only elements as a result of the
	 *       complement of the union and intersection of the two sets.
	 */
	E symmetricDifference(E e);
}
