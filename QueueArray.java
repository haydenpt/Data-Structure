public class QueueArray<E> implements Queue<E> {
    private E[] data;
    private int front = 0; // Index of the first element
    private int currentSize = 0;

    // Constructor
    QueueArray(int size) {
        data = (E[]) new Object[size];
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public void enqueue(E obj) throws IllegalStateException{
        if (size() == data.length) throw new IllegalStateException("Queue is full");

        /*
            Find the next available space using modulo
            (Front + size) % length will give us the index after the range [front:size]
            If we dequeue, front index will move forward, leaving us space before front
            If we reach the last index of the array and have space at the front
            (front + size) % length will circle back to the front of the array
         */
        int available = (front + currentSize) % data.length;
        data[available] = obj;
        currentSize++;
    }

    @Override
    public E first() {
        // Return null if empty, else return the first element
        return (isEmpty() ? null : data[front]);
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            return null;
        E temp = data[front]; // Store first element to temp before removing
        data[front] = null; // Remove data
        /*
            Move the front pointer 1 step forward
            Front % length will return the remainder which is the currentindex number
            Ex: 1 % 10 will return 1, meaning index 1 in array of size 10
            So (front + 1) % length will always return the next index
         */
        front = (front + 1) % data.length;

        currentSize--; // Decrease the current size
        return temp; // Return the old front element
    }
}
