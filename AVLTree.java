public class AVLTree<E> {
    class Node<E> {
        E data; // Data of the node
        Node<E> left; // pointer to left child
        Node<E> right; // pointer to right child
        Node<E> parent; //pointer to parent

        // Constructor for Node inner class
        public Node(E obj) {
            data = obj;
            // for first node, parent left right is null
            parent = left = right = null;
        }

    }
    // Attributes of the AVL tree
    Node<E> root;
    int currentSize;

    // Constructor for AVL class
    public AVLTree() {
        root = null;
        currentSize = 0;
    }

    public void addNode(E obj) {
        Node<E> node = new Node<E>(obj);
        if (root == null) {
            root = node;
            currentSize++;
            return;
        }
        add(root, node);
        // recursively traverse the tree from root to find where to add

    }

    // Recursive add method
    public void add(Node<> parent, Node<E> newNode) {
        // If newNode.data > parent.data then add to the right side
        if (((Comparable<E>)newNode.data).compareTo(parent.data) > 0) {
            // If at the bottom, add new node there
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent; // Set newNode's parent to current node, otherwise it's null
                currentSize++;
            }
            // If not at the bottom, call recursive add method
            else {
                add(parent.right, newNode);
            }
        }
        // If newNode.data < parent.data, add to the left side
        else {
            // If at the bottom, add new node there
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent; // Set newNode's parent to current node
                currentSize++;
            }
            // If not at the bottom, call recursive add method
            else {
                add(parent.left, newNode);
            }
        }
        // After adding the node
        checkBalance(newNode);
    }

    /* Checking the balance of the tree
        Starts at the node we just added, measure the height
        If the difference in height > 1, then rotate to re-balance
        Keep moving up until reach the root
     */
    public void checkBalance(Node<E> node) { // parameter is the recently added node
        // If one side has more children than the other, call re-balance at the current node
        if (
                (height(node.left) - height(node.right) > 1) ||
                (height(node.left) - height(node.right) < -1)
            ) {
                rebalance(node); // re-balance a subtree
            }
        // Check if we're at the root
        if (node.parent == null) {
            return;
        }
        checkBalance(node.parent);
    }

    /* Re-balance method
        Check the height of each side and figure out which one is imbalanced
        Go down the longer path and rotate
     */
    public void rebalance(Node<E> node) {
        // If left > right, go the left path
        if (height(node.left) - height(node.right) > 1) {
            // If left child left subtree -> right rotate
            if (height(node.left.left) > height(node.left.right)) {
                node = rightRotate(node); // replace node with new rotated values
            }
            else { // If left child right subtree -> left right rotate
                node = leftRightRotate(node); // replace node with new rotated values

            }
        }

        // If right > left, go the right path
        if (height(node.left) - height(node.right) > 1) {
            // If left child left subtree -> right rotate
            if (height(node.left.left) > height(node.left.right)) {
                node = rightRotate(node); // replace node with new rotated values
            }
            else { // If left child right subtree -> left right rotate
                node = leftRightRotate(node); // replace node with new rotated values

            }
        }
    }

}
