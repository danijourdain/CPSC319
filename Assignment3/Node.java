public class Node {
    private Data data;
    private Node left, right, parent;
    private int balance = 0;
    //this Node class is used for both Binary Search Tree and AVL Tree implementations
        //when it is used for the BST, the balance field is simply ignored

    /**
     * The main constructor for the Node class.
     * @param data The data to be added to the Node.
     * @param parent the parent node of the new node.
     * @param left the left child node of the new node.
     * @param right the right child node of the new node.
     */
    public Node(String data, Node parent, Node left, Node right) {
        this.data = new Data(data);
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /**
     * Secondary constructor for the Node class. Sets all Node pointers to null.
     * @param data The data to be added to the node.
     */
    public Node(String data) {
        this.data = new Data(data);
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * This method returns the data stored in a node.
     */
    public String getNode() {
        return this.data.getData();
    }

    /**
     * This method gets the data stored in a Node as a formatted string.
     * @return the formatted data as a string.
     */
    public String getNodeData() {
        return this.data.getData();
    }
    
    /**
     * Getter method for Node data.
     * @return Value of data.
     */
    public Data getData() {
        return this.data;
    }

    /**
     * Getter method for last name in the Node's data. This method was added since this value is frequently needed.
     * @return The lastName String in the Data.
     */
    public String getLastName() {
        return this.data.getLastName();
    }

    /**
     * Getter method for left child node.
     * @return left child node.
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * Getter method for right child node.
     * @return right child node.
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * Getter method for parent node.
     * @return parent node.
     */
    public Node getParent() {
        return this.parent;
    }

    /**
     * Getter method for the balance field.
     * @return The current balance value of the node.
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Setter method for the data variable.
     * @param data The new data for the node.
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Setter method for right child node.
     * @param newRight new right child node.
     */
    public void setRight(Node newRight) {
        this.right = newRight;
    }

    /**
     * Setter method for left child node.
     * @param newLeft new child node.
     */
    public void setLeft(Node newLeft) {
        this.left = newLeft;
    }

    /**
     * Setter method for parent node.
     * @param newParent new parent node.
     */
    public void setParent(Node newParent) {
        this.parent = newParent;
    }

    /**
     * Setter method for the balance of the node.
     * @param balance The new balance value for the node.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
}
