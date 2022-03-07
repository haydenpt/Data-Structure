import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements ListI<E> {

    // Inner class
    // Restrict access to the Node to only methods from the outer class
    /* Inner Class */
    class Node<E> {
        E data; // data with generic type
        Node<E> next; // our pointer for the node
        //Constructor
        public Node(E obj) {
            data = obj;
            next = null; // initialize pointer to null
        }
    }
    /* Inner Class Ends */
    /* Iterator inner class */
    class IteratorHelper implements Iterator<E> {
        Node<E> index;
        public IteratorHelper() {
            index = head;
        }
        @Override
        public boolean hasNext() {
            // When index is null (empty or last element), return false
            // If index has next, return true
            return (index != null);
        }
        @Override
        public E next() {
            // Check if we have an empty list
            if (!hasNext()) throw new NoSuchElementException();

            // If we have something, store the current data to val
            E val = index.data;
            // Move pointer forward and return val
            index = index.next;
            return val;
        }
    }
    /* Inner Class Ends */

    @Override
    public Iterator<E> iterator() {
        /* return an instance of class that implements Iterator<E> interface
            to use shortcode for(int i : list)
         */
        return new IteratorHelper();
    }

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

    // Get size of linked list
    public int getCurrentSize() {
        return currentSize;
    }

    /* Five boundaries:
     * 1. Empty
     * 2. Single Element
     * 3. Working in Middle
     * 4. Working at the beginning
     * 5. Working at the end
     */

    public void addFirst(E obj) {
        // Create the new node
        Node<E> node = new Node<E>(obj);
        if (head == null) {
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

	/* Add Last with O(n) complexity
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
	 */

    // Add last with O(1) complexity
    public void addLast(E obj) {
        Node<E> node = new Node<E>(obj);
        if (head == null) {
            // When list is empty, head and tail point to node
            head = tail = node;
            currentSize++;
            return;
        }
        tail.next = node; // Set the last element to point to new node
        tail = node; // Set the tail point to new node
        currentSize++;
    }

    /*
    If list length is even, add after length/2
    If list length is odd, add after the mean (length+1)/2
 */
    public void addMiddle(E obj) {
        // If list is empty, use addFirst() to add the element
        if (head == null) {
            addFirst(obj); // addFirst for empty list has return statement to break out of the method
        } else
        // If list only has 1 element, use addLast to add to the 2nd position
        if (head == tail) { // addLast for empty list has return statement to break out of the method
            addLast(obj);
        } else {
            // Make new node for object
            Node<E> node = new Node<E>(obj);

            // Set stop for pointer
            int stop = (currentSize % 2 == 0) ? (currentSize / 2) : ((currentSize + 1) / 2);
            // Make temp pointers and counter for pointer
            Node<E> current = head, previous = null;
            int counter = 1;

            // Start from head, move pointer forward while count < stop
            while (counter++ <= stop) {
                previous = current;
                current = current.next;
            }
            // After reach stop, add new node
            previous.next = node;
            node.next = current;
            currentSize++;
        }
    }

    public E removeFirst() {
        // If the list is empty, return null
        if (head == null)
            return null;
        // Store the data of first node to temp
        E temp = head.data;

        // Check single element list using head == tail or head.next == null or currentSize == 1
        if (head == tail)
            head = tail = null;

            // If regular list, point head to the second element to remove first
        else
            head = head.next;
        // Decrement the current size
        currentSize--;
        return temp;
    }

    public E removeLast() {
        // If list is empty return null
        if (head == null)
            return null;

        // Check single element list head == tail or head.next == null or currentSize == 1
        // If single element then call removeFirst()
        if (head == tail)
            return removeFirst();

        // Create 2 temp pointers current and previous
        Node<E> current = head, previous = null;
        while (current != tail) {
            previous = current;
            current = current.next; // points current to null
        }
        // After get to the end, make the 2nd last element become the last element
        previous.next = null;
        // Make tail point to previous to clear the point of the last element
        tail = previous;
        currentSize--;
        // Return the data of the deleted element
        return current.data;
    }

    public E remove(E obj) {
        // Create 2 temp pointers current and previous
        Node<E> current = head, previous = null;
        while (current != null) {
            if (((Comparable<E>) obj).compareTo(current.data) == 0) {
                if (current == head)
                    return removeFirst(); // handles both empty and single list
                if (current == tail)
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
        while (current != null) {
            if (((Comparable<E>) obj).compareTo(current.data) == 0)
                return true;
            // If current doesn't match then move current pointer forward
            current = current.next;
        }
        // When no match at all
        return false;
    }

    // View the data of first
    public E peekFirst() {
        if (head == null) // if list is empty
            return null;
        return head.data;
    }

    // View the data of last
    public E peekLast() {
        if (tail == null) // if list is empty
            return null;
        return tail.data;
    }

}

