import java.math.BigInteger;

public class List<E extends Comparable> implements ListInterface<E> {
	Node head;

	private class Node {

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
	public ListInterface<E> init() {
		head = null;
		return null;
	}

	@Override
	public int size() {
		int counter = 1;
		Node n = head;

		if (isEmpty()) {
			return 0;
		}

		while (n.next != null) {
			n = n.next;
			counter++;
		}

		return counter;
	}

	@Override
	public ListInterface<E> insert(E d) {
		Node n = head;
		Node toInsert = new Node(d);

		if (isEmpty()) {
			head = new Node(d);
		} else if (!find(d)) {
			while (n.next != null) {
				n = n.next;
			}
			n.next = new Node(toInsert.data);
		}
		
		System.out.println("element: " + n.data + " ");
		
		return null;
	}

	@Override
	public E retrieve() {
		return null;
	}

	@Override
	public ListInterface<E> remove() {
		return null;
	}

	@Override
	public boolean find(E d) {
		Node n = head;
		
		while (n.next != null) {
			if (d.compareTo(n.data) == 0) {
				return true;
			}
			n = n.next;
		}
		
		return false;
	}

	@Override
	public boolean goToFirst() {
		return false;
	}

	@Override
	public boolean goToLast() {
		return false;
	}

	@Override
	public boolean goToNext() {
		return false;
	}

	@Override
	public boolean goToPrevious() {
		return false;
	}

	public int get(BigInteger i) {

		return 0;
	}
}
