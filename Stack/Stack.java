public interface Stack<E> {

    // Return the number of elements in the stack
    int size();

    // Return a boolean value whether the stack is empty
    boolean isEmpty();

    // Add to the top of the stack
    void push(E obj);

    // Return the top element of the stack
    E top();

    // Return and remove the top element of the stack
    E pop();

}
