public class Node {
    private Data data;
    private Node left, right, parent;

    /**
     * Constructor for the Node
     * @param data The data to be added to the Node.
     */
    public Node(String data, Node parent, Node left, Node right) {
        this.data = new Data(data);
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /**
     * This method prints the data stored in a node.
     */
    public void printNode() {
        this.data.printData();
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
    
}
