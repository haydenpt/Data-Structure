import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements ListI<E>{

    /* Iterator inner class */
    class IteratorHelper implements Iterator<E> {
        NodeDoubly<E> index;
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
    /* Inner Class IteratorHelper ends */

    @Override
    public Iterator<E> iterator() {
        /* return an instance of class that implements Iterator<E> interface
            to use shortcode for(int i : list)
         */
        return new IteratorHelper();
    }


    // Inner class Node scope
    class NodeDoubly<E> {
        E data;
        NodeDoubly<E> next, prev;
        // Inner class constructor
        public NodeDoubly(E obj) {
            data = obj;
            next = null;
            prev = null;
        }
    }
    // Inner class Node ends

    // Outer class scope
    private NodeDoubly<E> head, tail;
    private int currentSize;

    // Outer class constructor
    public DoublyLinkedList() {
        head = null;
        currentSize = 0;
        tail = null;
    }

    // Get current size
    public int getCurrentSize() {
        return currentSize;
    }

    @Override
    public void addFirst(E obj) {
        // Create the new node
        NodeDoubly<E> nodeDoubly = new NodeDoubly<E>(obj);
        if (head == null) {
            /* When list is empty, head and tail point to node

             */
            head = tail = nodeDoubly;
            currentSize++;
            return;
        }
        // Point the new node to the previous node, which is the current head
        nodeDoubly.next = head;
        // Point the prev of current node to new node
        head.prev = nodeDoubly;
        // Move head pointer to new node
        head = nodeDoubly;
        // Increment the size of the linked list
        currentSize++;
    }

    @Override
    public void addLast(E obj) {
        NodeDoubly<E> nodeDoubly = new NodeDoubly<E>(obj);
        if (head == null) {
            // When list is empty, head and tail point to node
            head = tail = nodeDoubly;
            currentSize++;
            return;
        }
        tail.next  = nodeDoubly; // Set the last element to point to new node
        nodeDoubly.prev = tail; // Set prev of last element to point to current last
        tail = nodeDoubly; // Set tail to point to new node
        currentSize++;
    }

    @Override
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
                NodeDoubly<E> node = new NodeDoubly<E>(obj);

                // Set stop for pointer
                int stop = (currentSize % 2 == 0) ? (currentSize / 2) : ((currentSize + 1) / 2);
                // Make temp pointers and counter for pointer
                NodeDoubly<E> current = head, previous = null;
                int counter = 1;

                // Start from head, move pointer forward while count < stop
                while (counter++ <= stop) {
                    previous = current;
                    current = current.next;
                }
                // After reach stop, add new node
                previous.next = node;
                current.prev = node;
                node.next = current;
                node.prev = previous;
                currentSize++;
            }
    }

    @Override
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
        else {
            head = head.next; // Make 2nd element become first
            head.prev = null; // Prev of first element now point to null
            // Decrement the current size
            currentSize--;
        }
        return temp;
    }

    @Override
    public E removeLast() {
        // If list is empty return null
        if (head == null)
            return null;

        // Check single element list head == tail or head.next == null or currentSize == 1
        // If single element then call removeFirst()
        if (head == tail)
            return removeFirst();
        // Store data of the last element to temp
        E temp = tail.data;
        // Make tail point to the 2nd element from the last
        tail = tail.prev;
        // Make new last element next point to null
        tail.next = null;
        currentSize--;
        return temp; // tail is the new last element
    }

    @Override
    public boolean contains(E obj) {
        // Start current at head
        NodeDoubly<E> current = head;
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

    @Override
    public E remove(E obj) {
        // Create 2 temp pointers current and previous
        NodeDoubly<E> current = head, previous = null;
        while (current != null) {
            if (((Comparable<E>) obj).compareTo(current.data) == 0) {
                if (current == head)
                    return removeFirst(); // handles both empty and single list
                if (current == tail)
                    return removeLast();
                currentSize--;
                previous.next = current.next; // point next pointer of previous to current.next to break the connection
                current.next.prev = previous; // point prev pointer of current.next to previous to break the connection
                return current.data;
            }
            // if no match, move previous and current 1 step forward
            previous = current;
            current = current.next;
        }
        // after reaching the end of the list and no match
        return null;
    }
}
