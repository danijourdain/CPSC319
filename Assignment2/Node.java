public class Node {
    private String data;
    private Node next;

    /**
     * Constructor for Node. The next variable will always be set to null by default
     * @param data The String to be added to the Node as data.
     */
    public Node(String data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Getter for data variable.
     * @return String value of data.
     */
    public String getData() {
        return this.data;
    }

    /**
     * Getter for next Node.
     * @return The node pointed to by next variable.
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Setter variable for next Node.
     * @param next The new Node for next to be pointing at
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
