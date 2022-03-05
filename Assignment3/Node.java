public class Node {
    private Data data;
    private Node left;
    private Node right;
    private Node parent;

    /**
     * Constructor for the Node. Sets the child nodes to null.
     * @param number Student number.
     * @param lastName Student last name.
     * @param department Student department.
     * @param program Student program.
     * @param year Student year.
     */
    public Node(String data) {
        this.data = new Data(data);
        this.left = null;
        this.right = null;
        this.parent = null;
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
