public interface Queue<E> { // FIFO
    // Return the number of elements in the queue
    int size();

    // Return whether the queue is empty
    boolean isEmpty();

    // Add an element to the end of the queue
    void enqueue(E obj);

    // Return the first element in the queue
    E first();

    // Return and remove the first element in the queue
    E dequeue();
}
