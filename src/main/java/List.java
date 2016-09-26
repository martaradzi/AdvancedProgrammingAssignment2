import java.math.BigInteger;

public class List<E extends Comparable> implements ListInterface<E> {
	Node list;

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
		return false;
	}

	@Override
	public ListInterface<E> init() {
		list = null;
		return null;
	}

	@Override
	public int size() {
		int counter = 1;
		Node n = list;

		if (list == null) {
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
		Node n = list;
		Node toInsert = new Node(d);

		if (list == null) {
			list = new Node(d);
		} else {
			while (!find(toInsert.data) && n.next != null) {
					n = n.next;			
			}
			list = new Node(d, null, list);
		}
		System.out.println("element: " + list.data);
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
		Node n = list;
		
		while (n.data != null) {
			if (d.compareTo(n) == 0) {
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

	@Override
	public ListInterface<E> clone() {
		return null;
	}

	public int get(BigInteger i) {

		return 0;
	}
}
