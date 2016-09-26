public class List<E extends Comparable> implements ListInterface<E>{
	List<E> list;
	Node head;
	Node current;
	int numberOfElements;

	private class Node {

		E data;
		Node prior,
		next;

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
		numberOfElements = 0;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public ListInterface<E> init() {
		head = new Node(null);
		return list;
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
		if(isEmpty()){
			return null;
		}
		return current.data;
	}

	@Override
	public ListInterface<E> remove() {
		if(isEmpty()){
			return null;
		}
		Node n = current;
		current = null;
		if(isEmpty()){
			return null;
		}
		if(n.next == null){
			current = n.prior;
		}
		current = n.next;
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
		if(isEmpty()){
			return false;
		}else{
			current = head;
			return true;
		}
	}

	@Override
	public boolean goToLast() {
		if(isEmpty()){
			return false;
		}else{
			while(current.next != null){
				current = current.next;
			}
			return true;
		}
	}

	@Override
	public boolean goToNext() {
		if(isEmpty() || goToLast()){
			return false;
		}else{
			current = current.next;
			return true;
		}

	}

	@Override
	public boolean goToPrevious() {
		if(isEmpty() || goToFirst()){
			return false;
		}else{
			current = current.prior;
			return true;
		}

	}
}
