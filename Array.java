public class Array<E> {
    private Object[] arr;
    private int head, tail;
    private int midIndex;
    private int size;
    private int counter = 0;


    public Array(int size) {
        // Calculate the middle point to start head and tail
        midIndex = (size % 2) == 0 ? (size / 2) : (size + 1) / 2;
        // Set head and tail to the middle index
        head = tail = midIndex;
        // Declare Object array with size
        arr = new Object[size];
        // Counter to keep track if array is filled
        this.size = size;
    }

    public void addFirst(E obj) {
        // Allow adding new element while counter < size
        if (counter < size) {
            // If there is no space on the left, move head to the end of the array
            if (head < 0)
                head = size - 1;
            // Adding new element to head position - 1
            arr[head] = obj;
            head--;
            counter++;
        }
    }

    public void addLast(E obj) {
        // Allow adding new element while counter < size
        if (counter < size) {
            // If there is no space on the right, move tail to the beginning
            if (tail == size)
                tail = 0;
            // Adding new element to tail position + 1
            arr[tail + 1] = obj;
            tail++;
            counter++;
        }
    }

    public E removeFirst() {
        // Store first element in temp before remove
        E temp = (E) arr[head];
        arr[head] = null;

        // If head is at the last position, move head to position 0, otherwise move head forward
        head = ((head + 1) == size) ? 0 : head + 1;
        return temp;
    }

    public E removeLast() {
        //Store the last element in temp before remove
        E temp = (E) arr[tail];
        arr[tail] = null;

        tail = ((tail - 1) < 0) ? (size - 1) : tail - 1;
        return temp;
    }

    public E getItem(int index) {
        return (E) arr[index]; // type cast the data of arr[index] to generic type E
    }
}
