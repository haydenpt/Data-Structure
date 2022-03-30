/*
    Array stack is simple and efficient but relies on fixed size
    which limits the ultimate size of stack concept
    All methods have time complexity of O(1), space complexity O(n)
 */
public class ArrayStack<E> implements Stack<E> {
    public static final int SIZE = 10;
    private E[] data;
    private int top = -1; // Start with an empty array, so the top index is -1

    // Constructor

    ArrayStack(int size) {
        // Create a new object array and cast to type E
        data = (E[]) new Object[size];
    }

    @Override
    public int size() {
        //  Return the size of the stack
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        // If top = -1 then the stack is empty then return true, else return false
        return top == -1;
    }

    @Override
    public void push(E obj) {
        if( size() == data.length) // If size reach the length of the stack
            throw new IllegalStateException("Stack is full");
        data[++top] = obj; // Increment top and then store obj to the position
    }

    @Override
    public E top() {
        return (isEmpty() ? null : data[top]);
    }

    @Override
    public E pop() {
        // Return null if stack is empty
        if (isEmpty())
            return null;

        // Store the top element to temp before remove it
        E temp = data[top];

        // Remove the top element by pointing it to null
        data[top] = null;

        // Decrement the top index
        top--;

        return temp;
    }

    public static <E> void reverse(E[] data) {
        Stack<E> temp = new ArrayStack<>(data.length);

        // Copy the data array to the temp array
        for(int i = 0; i < data.length; i++)
            temp.push(data[i]);

        // Replace data array with temp array element in descending order
        for(int i = 0; i < data.length; i++)
            data[i] = temp.pop();
    }

}
