public class Set<E extends Comparable<E>> implements SetInterface<E> {
	List<E> list;
	
	Set() {
		list = new List<E>();
		list.init();
	}
	
	@Override
	public Set<E> union(SetInterface<E> set) {
		Set<E> set1 = this.copy();
		Set<E> set2 = set.copy();
		
		while (!set2.isEmpty()) {
			// get a value from 'set' and add it to 'result', then remove it from 'set'.
			set1.add(set2.get());
			set2.remove(set2.get());
		}
		return set1;
	}

	@Override
	public E intersect(E e) {
		return null;
	}

	@Override
	public E complement(E e) {
		return null;
	}

	@Override
	public E symmetricDifference(E e) {
		return null;
	}
	
	
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void add(E d) {
		list = list.find(d) ? list : list.insert(d);
	}

	@Override
	public E get() {
		return (E) list.retrieve();
	}

	@Override
	public void remove(E d) {
		list = list.find(d) ? list.remove() : list;
		
	}

	@Override
	public boolean contains(E d) {
		return list.find(d);
	}

	@Override
	public Set<E> copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
