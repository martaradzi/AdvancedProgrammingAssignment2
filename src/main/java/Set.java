public class Set<E extends Comparable<E>> implements SetInterface<E> {
	List list;
	
	Set() {
		list = new List();
		list.init();
	}

	@Override
	public E union(E e) {
		Set set2 = (Set) e;
		Set result = new Set();
		result.list = list;
		
		while (!set2.list.isEmpty()) {
			result.add((E) set2.list.retrieve());
			set2.list.remove();
		}
		return (E) result;
	}

	@Override
	public E intersect(E e) {
		Set result = new Set();
		Set set2 = (Set) e;
		
		if (list.size() < set2.size()) {
			
		} else {
			
		}
		return (E) result;
	}

	@Override
	public E complement(E e) {
		List result = list;
		Set set2 = (Set) e;
		
		while (!set2.list.isEmpty() && !result.isEmpty()) {
			if (contains((E) set2.list.retrieve())) {
				result.remove();
			}
			set2.list.remove();
		}
		return (E) result;
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
	public E remove(E d) {
		list = list.find(d) ? list.remove() : list;
		return (E) list;
		
	}

	@Override
	public boolean contains(E d) {
		return list.find(d);
	}

	public Comparable compareTo(Set s2) {
		return list.equals(s2);
	}

}
