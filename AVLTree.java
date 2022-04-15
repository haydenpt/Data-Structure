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

    // Find the height of the current node
    // Height of empty tree is -1,
    //
    //
    public int height(Node<E> node) {
        // Check if tree is empty, height = 0 if true
        if (root == null) {
            return 0;
        }
        else {
            // Initialize the height of both side
            int leftHeight = 0, rightHeight = 0;

            // Trickle down the left subtree
            if(node.left != null) {
                leftHeight = height(node.left);
            }

            // Trickle down the right subtree
            if(node.right != null) {
                rightHeight = height(node.right);
            }

            // Take the higher value among 2 subtree
            int max = (leftHeight > rightHeight) ? leftHeight : rightHeight;

            // Add 1 to include the root -> height
            return (max + 1);
        }
    }

    // ROTATE METHODS

    public Node<E> leftRotate(Node<E> node) { // Parameter is grandparent node
        Node<E> temp = node.right; // Set temp to GP's right child
        node.right = temp.left; // Point GP's right to temp's left
        temp.left.parent = node; // Point parent of temp's left to node
        temp.left = node; // Set temp's left to GP
        return temp; // return the new grandparent
    }

    public Node<E> rightRotate(Node<E> node) {
        Node<E> temp = node.left; // Set temp to GP's left
        node.left = temp.right; // Set GP's left to temp's right
        temp.right.parent = node; // POin parant of temp's right to node
        temp.right = node; // Set temp's right to GP; temp is now the GP
        return temp;
    }

    public Node<E> rightLeftRotate(Node<E> node) { // Take GP node as parameter
        node.right = rightRotate(node.right); // Set GP's right with the new node after rotation
         return leftRotate(node); // Rotate the GP and return new GP
    }

    public Node<E> leftRightRotate(Node<E> node) { // Take GP node as parameter
        node.left = leftRotate(node.left);
        return rightRotate(node); // Rotate the GP and return new GP
    }
    // END OF ROTATE METHODS

    public void addNode(E obj) {
        Node<E> node = new Node<E>(obj);
        if (root == null) { // if tree is empty
            root = node;    // add node as the root node
            currentSize++;
            return;
        }
        add(root, node);
        // recursively traverse the tree from root to find where to add

    }

    // Recursive add method
    public void add(Node<E> parent, Node<E> newNode) {
        // If newNode.data > parent.data then add to the right side
        if (((Comparable<E>)newNode.data).compareTo(parent.data) > 0) {
            // If at the bottom right, add new node there
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent; // Set newNode's parent to current node, otherwise it's null
                currentSize++;
            }
            // If not at the bottom, call recursive add method to trickle down the tree
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
        // If one side has 2+ more children than the other, call re-balance at the current node
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
        else {
            // If right child left subtree -> left rotate
            if (height(node.right.left) > height(node.right.right)) {
                node = leftRotate(node); // replace node with new rotated values
            }
            else { // If right child right subtree -> left right rotate
                node = rightLeftRotate(node); // replace node with new rotated values

            }
        }

        // If we get to the root node, point root to the new node and exit
        if (node.parent == null) {
            root = node;
        }

    }

}
