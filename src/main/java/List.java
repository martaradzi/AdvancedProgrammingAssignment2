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
        return false;
    }

    @Override
    public ListInterface<E> init() {
    	head = new Node(null);
        return list;
    }

    @Override
    public int size() {
    	return 0;
    }

    @Override
    public ListInterface<E> insert(E d) {
    	Node n = new Node(d);
    	current = head;
    	
    	while (current.next != null) {
    		if (find(d)) {
    			System.out.println("FOUND");
    			break;
    		}
    		current = current.next;
    	}
    	
    	current = n;
    	System.out.print(n.data + " ");
    	
        return list;
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
    	while (current.next != null) {
    		if (d.compareTo(current) == 0) {
    			return true;
    		}
    		
    		current = current.next;
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

    @Override
    public ListInterface<E> clone() {
        return null;
    }
}
