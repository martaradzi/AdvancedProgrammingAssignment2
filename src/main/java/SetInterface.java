/**
 * @elements Sets of the type Set
 * @structure None
 * @domain One list of integers
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
	 *       sets and are of equal value. The new set contains no duplicate
	 *       elements.
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
	 * @post A new set is returned containing only the elements resulting in the
	 *       complement of the union and intersection of both sets.
	 */
	E symmetricDifference(E e);
}
