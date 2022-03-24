public class Tree<E> {
    /*
        Inner Node class for the Tree
     */
    class Node<E> {
        E data;
        Node<E> left, right; // pointers for left and right

        // Constructor for inner class
        /*
        First node will have left and right point to null
            null <--[left][data][right] --> null
         */
        public Node(E obj) {
            this.data = obj;
            left = right = null;
        }
    }

    /*
        Add method
        Recursive method that traverse to all level until it reaches null
        This method receives obj and node
            obj is the object to add
            node is the position to add the new object
     */
    public void add(E obj, Node<E> node) {
        if (((Comparable<E>)obj).compareTo(node.data) > 0) {
            /* '> 0' means if obj > node.data
                if the new object is bigger than the data, we will take the left path
             */
        }
    }
}
