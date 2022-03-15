public class AVLTree {
    private Node root;

    /**
     * Default constructor for AVL Trees. Sets root node to null.
     */
    public AVLTree() {
        this.root = null;
    }

    /**
     * Getter method for the root node of the AVL Tree.
     * @return The Node that the root pointer is pointing to.
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * Driver code for the insertion into an AVL Tree
     * @param data The data to be added to the tree.
     */
    public void insert (String data) {
        this.root = insert(this.root, data);
    }

    /**
     * The main code for the insertion into the AVL Tree.
     * @param node The starting node.
     * @param data The data to be added to the tree.
     * @return The inserted node.
     */
    private Node insert(Node node, String data) {
        /* insertion code for AVL Trees, including all helper functions adapted from 
         * https://www.geeksforgeeks.org/avl-tree-set-1-insertion/ */

        if(node == null) {
            return (new Node(data));
            //if the tree is empty, create a new node, and return it
        }

        String lastName = Data.getLastName(data);

        //recursively insert the new node into the tree
        if(lastName.compareTo(node.getLastName()) < 0) {
            node.setLeft(insert(node.getLeft(), data));
        }
        else if(lastName.compareTo(node.getLastName()) > 0) {
            node.setRight(insert(node.getRight(), data));
        }
        else {
            return node;
        }

        //update balance of ancestor node
        node.setBalance(max(node.getLeft(), node.getRight()) + 1);

        //get balance of ancestor node
        int balance = balance(node);
 
        //if the node is unmalances, there are 2 main cases
        
        //case 3A - outside subtree is on the left
        if (balance > 1 && (node.getLeft() == null || lastName.compareTo(node.getLeft().getLastName()) < 0))
            return rightRotate(node);
 
        //case 3A - outside subtree is on the right
        if (balance < -1 && (node.getLeft() == null || lastName.compareTo(node.getLeft().getLastName()) > 0))
            return leftRotate(node);
 
        //case 3B - inside subtree is on the right
        if (balance > 1 && (node.getRight() == null || lastName.compareTo(node.getLeft().getLastName()) > 0)) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
 
        //case 3B - inside subtree is on the left
        if (balance < -1 && (node.getRight() == null || lastName.compareTo(node.getLeft().getLastName()) < 0)) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        //return the unchanged node pointer
        return node;
    }

    /**
     * This is a helper method for the insertion. It finds the max balance of two nodes.
     * @param a The first node to be compared.
     * @param b The second node to be compared.
     * @return The balance of the node with the larger balance.
     */
    private int max(Node a, Node b) {
        if(a == null && b == null) {
            return -1;
        }
        else if(a == null) {
            return b.getBalance();
        }
        else if(b == null) {
            return a.getBalance();
        }
        else {
            return Math.max(a.getBalance(), b.getBalance());
        }
    }
    
    /**
     * This is another helper method for the insertion into the AVL Tree. It finds the method for a given node.
     * @param n The node to find the balance for.
     * @return The balance of the node.
     */
    private int balance(Node n) {
        if(n == null) {
            return 0;
        }
        
        return (balance(n.getLeft()) - balance(n.getRight()));
    }

    /**
     * This is a helper method for the insert method. It completes a right rotation on the given node.
     * @param node The node to start the right rotation from.
     * @return The left child of the node.
     */
    private Node rightRotate(Node node) {
        Node leftChild = node.getLeft();
        Node grandchild = leftChild.getRight();

        node.setRight(leftChild);
        leftChild.setLeft(grandchild);

        node.setBalance(max(node.getLeft(), node.getRight()) + 1);
        leftChild.setBalance(max(leftChild.getLeft(), leftChild.getRight()) + 1);

        return leftChild;
    }

    /**
     * This is another helper method for insertion. It completes a left rotation on the given node.
     * @param node The node to be rotated left.
     * @return The right child of the node.
     */
    private Node leftRotate(Node node) {
        Node rightChild = node.getRight();
        Node grandchild = rightChild.getLeft();

        rightChild.setLeft(node);
        node.setRight(grandchild);

        node.setBalance(max(node.getLeft(), node.getRight()) + 1);
        rightChild.setBalance(max(rightChild.getLeft(), rightChild.getRight()) + 1);

        return rightChild;
    }

    /**
     * This method traverses the tree depth-first in-order and prints the results .
     * @param current The node to start at.
     */
    public String inOrder(Node current) {
        /* Depth-first, in order traversal adapted from slide 27 of Feb 16 lecture notes */

        StringBuilder result = new StringBuilder();

        if(current != null) {
            result.append(inOrder(current.getLeft()));
            //check the left node

            result.append(current.getNode() + "\n");
            //add the current node

            result.append(inOrder(current.getRight()));
            //check the right node

            return result.toString();
            //convert the StringBuilder to a String and return
        }

        return "";
        //if the current node is null, return an empty string
    }

    /**
     * This method traverses the tree using a breadth-first traversal.
     * @param size The max size of the data.
     * @return The results of the traversal as a multi-line string
     */
    public String breadthFirst(int size) {
        /* code for breadth first traversal adapted from https://www.geeksforgeeks.org/level-order-tree-traversal/ */

        Queue queue = new Queue(size);
        StringBuilder result = new StringBuilder();

        queue.enqueue(this.root);

        while(queue.isEmpty() == false) {
            Node temp = queue.dequeue();

            result.append(temp.getNodeData() + "\n");

            if(temp.getLeft() != null) {
                queue.enqueue(temp.getLeft());
            }
            if(temp.getRight() != null) {
                queue.enqueue(temp.getRight());
            }
        }

        return result.toString();
    }
}
