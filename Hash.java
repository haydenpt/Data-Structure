/*
    Chain-Hash Class
    Each position of the array has a linked list
    Each object is called Hash Element with a Key and a Value (K,V)
 */

import java.util.Iterator;

public class Hash <K,V> implements HashI<K,V>{

    /*
            Implements Comparable so that we can use compareTo() method
            to compare whether 2 Hash Elements are the same
         */
    class HashElement<K,V> implements Comparable<HashElement<K,V>>{
        K key;
        V value;

        // Constructor of HashElement
        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /*  Compare the current self (this) to another HashElement<K,V> object
            We decide how to compare 2 objects. It could be:
            Two Keys are the same
            Two Values are the same
            Both Keys and Values are the same
         */
        @Override
        public int compareTo(HashElement<K,V> other) {
            /* Cast this.key to Comparable<K> then
                use the compareTo() method to compare with other.key
                This will return an integer
                0 if equal, -1 if 'this' comes before, 1 if 'this' comes after
                Comparable<K> ensure future HashElement has compareTo() method
             */
            return (((Comparable<K>)this.key).compareTo(other.key));
        }

    }

    // Global variables
    int numElements; // Current size
    int tableSize; // Size of the array
    double maxLoadFactor; // Point of resizing the table
    /*
        Array of linked lists with generic type of class HashElement<K,V>
     */
    LinkedList<HashElement<K,V>>[] hashArray;

    // Hash (outer class) Constructor
    public Hash(int tableSize) {
        /* If we initial table size with a large number (1000, 5000...)
            we won't have to resize frequently
         */
        this.tableSize = tableSize;
        /*
            Good overall load factor 0.75
            lambda = (# of elements / table size)
         */
        maxLoadFactor = 0.75;
        numElements = 0;

        /*
            Cast LinkedList array to LinkedList<HashElement<K,V>> generic type
            Because it's tricky in java to create an array of generic type
            -> Create an array of class type LinkedList
            -> Cast to LinkedList<HashElement<K,V>> type
         */
        hashArray = (LinkedList<HashElement<K,V>>[]) new LinkedList[tableSize];

        /* Initialize array with empty linked lists
            we don't have to check whether the position already has a linked list
            when we want to perform add, remove, find,...
         */
        for(int i = 0; i<tableSize; i++) {
            hashArray[i] = new LinkedList<HashElement<K,V>>();
        }
    }

    // Calculate load factor to increase size
    public double loadFactor() {
        return (double)numElements/tableSize;
    }

    public void resize(int newTableSize) {
        // Create a temporary empty array
        LinkedList<HashElement<K, V>>[] newArray = (LinkedList<HashElement<K, V>>[]) new LinkedList[newTableSize];

        // Populate new array with empty linked lists
        for(int i=0; i<newTableSize; i++) {
            newArray[i] = new LinkedList<HashElement<K,V>>();
        }

        // Iterate every key in current Hash table
        for(K key: this) {
            // Store value of the current key to value
            V value = getValue(key);
            //Make new Hash Element with the key and value
            HashElement<K,V> he = new HashElement<K,V>(key, value);
            // Get the new hashValue based on the new table size
            int hashValue = key.hashCode() & 0x7FFFFFFF % newTableSize;
            // Add he to the new array at position 'hashValue'
            newArray[hashValue].addFirst(he);
        }
        // Remove the original array and point old array to new array
        hashArray = newArray;
        // Set the global tableSize var to the new size
        tableSize = newTableSize;
    }

    // Add a Hash Element
    public boolean add(K key, V value) {
        // Check if we need to increase the size before adding
        // Call loadFactor() that return the loadFactor calculation
        if(loadFactor() > maxLoadFactor) {
            // Call resize() and double the current size
            resize(tableSize * 2);
        }

        // Create new object with Key and Value
        HashElement<K,V> hashElement = new HashElement(key, value);

        // Calculate hashValue and find the index
        int hashValue = key.hashCode();
        hashValue = hashValue & 0x7FFFFFFF;
        hashValue = hashValue % tableSize;

        // use addFirst so we don't worry about tail
        hashArray[hashValue].addFirst(hashElement);

        // Increase the size after adding
        numElements++;
        return true;
    }

    // Remove a Hash Element
    public boolean remove(HashElement hashElement) {
        // Calculate hashValue of the passed object key
        int hashValue = hashElement.key.hashCode();
        hashValue = hashValue & 0x7FFFFFFF;
        hashValue = hashValue % tableSize;

        // Call remove method and pass the hashElement object
        hashArray[hashValue].remove(hashElement);
        return true;
    }

    // Get value of a Hash Element
    // Given a key, return the value associated with that key
    public V getValue(K key) {
        int hashValue = key.hashCode() & 0x7FFFFFFF % tableSize;
        // Use the iterator helper in LinkedList
        for(HashElement<K,V> he : hashArray[hashValue]) {
            if(((Comparable<K>)key).compareTo(he.key) == 0) {
                return he.value; // if true return the value of the key
            }
        }
        // If after loop and can't find any, return null
        return null;
    }

    // Inner Class for iteration
    /*
        This class retrieves all the keys from the original array
     */
    class IteratorHelper<T> implements Iterator<T> {
        T[] keys; // temp array to store the keys
        int position; // keep track of where we're at

        // Constructor
        public IteratorHelper() {
            T[] keys = (T[]) new Object[numElements];
            int p = 0; // tracker
            for (int i = 0; i< tableSize; i++) {
                // Store the linked list at position i to a temp list
                LinkedList<HashElement<K,V>> list = hashArray[i];
                for(HashElement<K,V> he : list) {
                    keys[p++] = (T)he.key;
                }
            }
            position = 0;
        }

        public boolean hasNext() {
            // if position is less than the length, there are still objects to iterate
            return position < keys.length;
        }

        public T next() {
            if (!hasNext()) {
                return null;
            }
            return keys[position++];
        }
    }

    @Override
    public Iterator iterator() {
        return new IteratorHelper();
    }
}
