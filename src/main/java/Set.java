public class Set<E extends Comparable<E>> implements SetInterface<E> {
	List<E> list;

	Set() {
		list = new List<E>();
		list.init();
	}

	@Override
	public Set<E> union(SetInterface<E> set) {
		Set<E> set1 = (Set<E>) this.copy();
		Set<E> set2 = (Set<E>) set.copy();
		Set<E> result = new Set<E>();

		// add elements from smaller set to larger set; return larger set
		if (set2.size() <= set1.size()) {
			set2.list.goToFirst();

			while (!set2.isEmpty()) {
				set1.add(set2.get());
				set2.remove(set2.get());
			}

			result = set1;
			
		} else {
			set1.list.goToFirst();

			while (!set1.isEmpty()) {
				set2.add(set1.get());
				set1.remove(set1.get());
			}

			result = set2;
		}

		return result;
	}

	@Override
	public Set<E> intersect(SetInterface<E> set) {
		Set<E> set1 = (Set<E>) this.copy();
		Set<E> set2 = (Set<E>) set.copy();
		Set<E> result = new Set<E>();

		set1.list.goToFirst();
		
		for (int i = 0; i < set1.size(); i++) {
			if (set2.contains(set1.get())) {
				result.add(set1.get());
			}
			
			set1.list.goToNext();
		}

		return result;
	}

	@Override
	public Set<E> complement(SetInterface<E> set) {
		Set<E> set1 = (Set<E>) this.copy();
		Set<E> set2 = (Set<E>) set.copy();
		Set<E> result = new Set<E>();

		set1.list.goToFirst();
		
		for (int i = 0; i < set1.size(); i++) {
			if (!set2.contains(set1.get())) {
				result.add(set1.get());
			}
			
			set1.list.goToNext();
		}

		return result;
	}

	@Override
	public Set<E> symmetricDifference(SetInterface<E> set) {
		Set<E> union = (Set<E>) this.copy().union(set.copy());
		Set<E> intersection = (Set<E>) this.copy().intersect(set.copy());
		Set<E> result = union.complement(intersection);

		return result;
	}

	@Override
	public SetInterface<E> copy() {
		Set<E> result = new Set<E>();

		list.goToFirst();

		for (int i = 0; i < list.numberOfElements; i++) {
			result.add(list.retrieve());
			list.goToNext();
		}

		return result;

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
}
