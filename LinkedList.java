public class LinkedList<E> {
	// Inner class
	// Restrict access to the Node to only methods from the outer class
	
	/* Inner Class */
	class Node<E> {
		E data;
		Node<E> next; // our point for the node
		
		//Constructor
		public Node(E obj) {
			data = obj;
			next = null; // initialize pointer to null
		}
	}
	/* Inner Class Ends */
	
	private Node<E> head, tail; // Head, tail has data type of a Node obj
	private int currentSize; 
	// Increment every time we add new node 
	// so that complexity is O(1) instead of O(n)

	
	// Outer class constructor
	public LinkedList() {
		head = null;
		currentSize = 0;
		tail = null;
	}
	
	public void addFirst(E obj) {
		// Create the new node
		Node<E> node = new Node<E>(obj);
		if(head == null) {
		// When list is empty, head and tail point to node
			head = tail = node;
			currentSize++;
			return;
		}
		
		// Point the new node to the previous node, which is the current head
		node.next = head;
		
		// Move head pointer to new node
		head = node;
		
		// Increment the size of the linked list
		currentSize++;
	}
	
	public void addLastOn(E obj) {
		Node<E> node = new Node<E>(obj);
		if(head == null) {
			head = node;
			currentSize++;
			return;
		}
		Node<E> temp = head; // Create a temp pointing to head
		
		while (temp.next != null) {
			temp = temp.next;
		} 
		temp.next = node;
		currentSize++;
	}
	
	public void addLastO1(E obj) {
		Node<E> node = new Node<E>(obj);
		if(head == null) {
		// When list is empty, head and tail point to node
			head = tail = node;
			currentSize++;
			return;
		}
		tail.next = node; // Set the last element to point to new node
		tail = node; // Set the tail point to new node
		currentSize++;
	}
	
	public E removeFirst() {
		if(head == null) 
			return null;
		// Store the data of first node to return
		E temp = head.data;
		
		// Check single element list head == tail or head.next == null or currentSize == 1
		if(head == tail) 
			head = tail = null;
		
		// If regular list, point head to the second element to remove first
		else
			head = head.next;
		// Decrement the current size
		currentSize--;
		return temp;
	}
	
	public E removeLast() {
		if(head == null)
			return null;

		// Check single element list head == tail or head.next == null or currentSize == 1
		if(head == tail) 
			return removeFirst();
		
		// Create 2 temp pointers current and previous
		Node<E> current = head, previous = null;
		while (current != tail) {
			previous = current;
			current = current.next;
		}
		// Make the 2nd last element become the last element
		previous.next = null;
		// Make tail point to previous
		tail = previous;
		currentSize--;
		// Return the data of the deleted element
		return current.data;
	}
	
	public E remove(E obj) {
		// Create 2 temp pointers current and previous
		Node<E> current = head, previous = null;
		while (current != null) {
			if(((Comparable<E>)obj).compareTo(current.data) == 0) {
				if(current == head)
					return removeFirst(); // handles both empty and single list
				if(current == tail)
					return removeLast();
				currentSize--;
				previous.next = current.next; // deletes the pointer from previous to current
				return current.data;
			}
			// if no match, move previous and current 1 step forward
			previous = current;
			current = current.next;
		}
		// after reaching the end of the list and no match
		return null;
	}
	
	public boolean contains(E obj) {
		// Start current at head
		Node<E> current = head;
		// Loop through the list
		while(current != null) {
			if (((Comparable<E>)obj).compareTo(current.data) == 0)
				return true;
			// If current doesn't match then move current pointer forward
			current = current.next;
		}
		// When no match at all
		return false;
	}

	public E peekFirst() {
		if(head == null) // if list is empty
			return null;
		return head.data;
	}

	public E peekLast() {
		if(tail==null) // if list is empty
			return null;
		return tail.data;
	}
}

