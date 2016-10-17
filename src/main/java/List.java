public class List<E extends Comparable<E>> implements ListInterface<E> {
	Node current, head, tail;
	int numberOfElements;

	class Node {

		E data;
		Node prior, next;

		public Node(E d) {
			this(d, null, null);
		}

		public Node(E data, Node prior, Node next) {
			this.data = data == null ? null : data;
			this.prior = prior;
			this.next = next;
		}
	}

	List() {

	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public List<E> init() {
		head = null;
		tail = null;
		current = null;
		numberOfElements = 0;

		return this;
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public List<E> insert(E d) {
		goToFirst();

		if (isEmpty()) {
			current = new Node(d);
			head = current;
			tail = current;
			numberOfElements++;
			return this;
		}

		while (current.next != null && current.data.compareTo(d) < 0) {
			current = current.next;
		}

		if (current.next == null && current.data.compareTo(d) < 0) { // new tail node
			tail = new Node(d, tail, null);
			tail.prior.next = tail;
			current = tail;
		} else if (current.prior == null) { // new head node
			head = new Node(d, null, head);
			head.next.prior = head;
			current = head;
		} else { // new inner node
			current = current.prior = current.prior.next = new Node(d, current.prior, current);
		}

		if (numberOfElements == 1) {
			head.next = tail;
			tail.prior = head;
		}

		numberOfElements++;
		return this;
	}

	@Override
	public E retrieve() {
		return isEmpty() == true ? null : current.data;
	}

	@Override
	public List<E> remove() {
		if (isEmpty()) {
			return null;
		}

		if (numberOfElements == 1) {
			init();
			return this;
		}

		if (current.prior == null) {
			current = head = head.next;
		} else if (current.next == null) {
			current = tail = tail.prior;
		} else {
			current.next.prior = current.prior;
			current.prior.next = current = current.next;
		}

		numberOfElements--;
		return this;
	}

	@Override
	public boolean find(E d) {
		if (isEmpty()) {
			return false;
		}

		goToFirst();

		if (current.data.compareTo(d) == 0) {
			return true;
		}

		while (current.next != null) {
			current = current.next;

			if (current.data.compareTo(d) == 0) {
				return true;
			} else if (current.data.compareTo(d) > 0) {
				current = current.prior;
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean goToFirst() {
		if (isEmpty()) {
			return false;
		}

		current = head;
		return true;
	}

	@Override
	public boolean goToLast() {
		if (isEmpty()) {
			return false;
		}

		current = tail;
		return true;
	}

	@Override
	public boolean goToNext() {
		if (isEmpty() || current.next == null) {
			return false;
		}
		
		current = current.next;
		return true;

	}

	@Override
	public boolean goToPrevious() {
		if (isEmpty() || current.prior == null) {
			return false;
		}
		
		current = current.prior;
		return true;
	}

	@Override
	public ListInterface<E> copy() {
		List<E> result = new List<E>();

		goToFirst();

		for (int i = 0; i < size(); i++) {
			result.insert(retrieve());
			goToNext();
		}

		return result;
	}

	@Override
	public String toString() {
		goToFirst();

		String result = "";

		for (int i = 0; i < size(); i++) {
			result += retrieve() + " ";
			goToNext();
		}

		return result;
	}

}
