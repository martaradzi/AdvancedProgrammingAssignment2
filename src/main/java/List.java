import java.math.BigInteger;

public class List<E extends Comparable> implements ListInterface<E> {
	List<E> list;
	Node current;
	int numberOfElements;
	BigInteger[] biggie = new BigInteger[100];

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
		return numberOfElements == 0;
	}

	@Override
	public ListInterface<E> init() {
		current = new Node(null);
		return null;
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public ListInterface<E> insert(E d) {
		Node n = new Node(d);

		if (isEmpty()) {
			current = n;
			biggie[numberOfElements] = (BigInteger) d;
			numberOfElements++;
		} else if (!find(d)) {
			n.prior = current;
			current.next = n;
			current = current.next;
			biggie[numberOfElements] = (BigInteger) d;
			numberOfElements++;
		}
		return null;
	}

	@Override
	public E retrieve() {
		return current.data;
	}

	@Override
	public ListInterface<E> remove() {
		// TODO Auto-generated method stub
		return null;
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

		while (current.prior != null) {
			current = current.prior;
		}
		return true;
	}

	@Override
	public boolean goToLast() {
		if (isEmpty()) {
			return false;
		}
		
		while (current.next != null) {
			current = current.next;
		}
		return true;
	}

	@Override
	public boolean goToNext() {
		if (isEmpty()) {
			return false;
		} else if (current.next != null) {
			current = current.next;
		}
		return true;
	}

	@Override
	public boolean goToPrevious() {
		if (isEmpty()) {
			return false;
		} else if (current.prior != null) {
			current = current.prior;
		}
		return true;
	}
}
