/*
    ROOT is black
    NEW NODES are red
    NULLS are black
    No two consecutive reds
    Same number of blacks on every path from root
    ----
    Black aunt -> rotate
        black
         /\
      red  red

    Red aunt -> color flip
         red
         /\
     black  black

 */

public class RedBlackTree<K, V> implements RedBlackI<K, V> {
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> left; // pointer to left child
        Node<K, V> right; // pointer to right child
        Node<K, V> parent; //pointer to parent
        boolean isLeftChild;
        boolean isBlack;

        // Constructor for Node inner class
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            // for first node, parent left right is null
            parent = left = right = null;
            // New nodes are red
            isBlack = false;
            // Unsure if new node is left or right child
            isLeftChild = false;
        }
    }

    Node<K, V> root; // root is null
    int size;

    // Add new nodes
    public void add(K key, V value) {
        Node<K, V> node = new Node<K, V>(key, value);
        if (root == null) {
            root = node;
            root.isBlack = true;
            size++;
        } else {
            // Recursive add method below
            add(root, node);
            size++;
        }
    }

    private void add(Node<K, V> parent, Node<K, V> newNode) {
        if (((Comparable<K>) newNode.key).compareTo(parent.key) > 0) {
            // If new node is > parent
            if (parent.right == null) { // Reach the end of the tree
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
            } else {
                add(parent.right, newNode);
            }
        } else {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = true;
            } else {
                add(parent.left, newNode);
            }
        }
        checkColor(newNode);
    }

    public void checkColor(Node<K, V> node) {
        if (node == root) {
            return;
        }
        // Check for two consecutive red
        if (!node.isBlack && !node.parent.isBlack) {
            correctTree(node);
        }
        checkColor(node.parent);
    }

    // This method check the color of the aunt node and determine balancing method
    public void correctTree(Node<K, V> node) {
        if (node.parent.isLeftChild) {
            // Aunt is node.parent.parent.right
            if (node.parent.parent.right == null || node.parent.parent.right.isBlack) {
                return rotate(node);
            }
            if (node.parent.parent.right != null) {
                node.parent.parent.right.isBlack = true;
            }
        } else {
            // Aunt is grandparent.left
            if (node.parent.parent.left == null || node.parent.parent.left.isBlack) {
                return rotate(node);
            }
            if (node.parent.parent != null) {
                node.parent.parent.left.isBlack = true;
            }
        }
        node.parent.parent.isBlack = false;
        node.parent.isBlack = true;
    }

    public void rotate(Node<K, V> node) {
        if (node.isLeftChild) {
            if (node.parent.isLeftChild) {
                rightRotate(node.parent.parent);
                node.isBlack = false;
                node.parent.isBlack = true;
                if (node.parent.right != null)
                    node.parent.right.isBlack = false;
                return;
            }
            if (!node.parent.isLeftChild) {
                rightLeftRotate(node.parent.parent);
                // Node we start at become the parent
                node.isBlack = true;
                node.right.isBlack = false;
                node.left.isBlack = false;
                return;
            }
        }
        if (!node.isLeftChild) {
            if (!node.parent.isLeftChild) {
                leftRotate(node.parent.parent);
                node.isBlack = false;
                node.parent.isBlack = true;
                if (node.parent.left != null)
                    node.parent.left.isBlack = false;
                return;
            }
            if (node.parent.isLeftChild) {
                leftRightRotate(node.parent.parent);
                node.isBlack = true;
                node.right.isBlack = false;
                node.left.isBlack = false;
                return;
            }
        }
    }

    public void leftRotate(Node<K, V> node) {
        Node<K, V> temp = node.right;
        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
            node.right.isLeftChild = false;
        }
        if (node.parent == null) {
            // At root node
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        temp.left = node;
        node.isLeftChild = true;
        node.parent = temp;
    }

    public void rightRotate(Node<K, V> node) {
        Node<K, V> temp = node.left;
        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
            node.left.isLeftChild = true;
        }
        if (node.parent == null) {
            // At root
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        temp.right = node;
        node.isLeftChild = false;
        node.parent = temp;
    }

    public void leftRightRotate(Node<K, V> node) {
        leftRotate(node.left);
        rightRotate(node);
    }

    public void rightLeftRotate(Node<K, V> node) {
        rightRotate(node.right);
        leftRotate(node);
    }

    public int height() {
        if (root == null) {
            return 0;
        }
        return height(root) - 1;
    }

    public int height(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left) + 1;
        int rightHeight = height(node.right) + 1;
        return Math.max(leftHeight, rightHeight);
    }

    public int blackNodes(Node<K, V> node) {
        if (node == null) {
            return 1;
        }
        int rightBlackNodes = blackNodes(node.right);
        int leftBlackNodes = blackNodes(node.left);
        if (rightBlackNodes != leftBlackNodes) {
            // throw error or fix the tree
            // black nodes on both side must be equal
        }
        if (node.isBlack) {
            return leftBlackNodes++;
        }
    }
}
