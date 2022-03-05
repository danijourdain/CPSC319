public class LinkedList {
    /* Note that this implementatio of LinkedList does not include many of the common
     * methods associated with Linked Lists. The methods here are only the methods 
     * needed to complete Assigment 2. 
     */

    private Node head;

    /**
     * Constructor for LinkedList.
     * @param data A String to be the first element in the list
     */
    public LinkedList(String data) {
        this.head = new Node(data);
        //call the Node constructor for the new element
        //then set head to point at the new Node
    }

    /**
     * Default constructor for LinkedList. Will set head to null
     */
    public LinkedList() {
        this.head = null;
    }

    /**
     * Setter method for head.
     * @param newHead The new Node for head to point at.
     */
    public void setHead(Node newHead) {
        this.head = newHead;
    }

    /**
     * Getter for head.
     * @return The Node that head is pointing at.
     */
    public Node getHead() {
        return this.head;
    }

    /**
     * This method will insert a new Node in the appropriate spot in the Linked List.
     * I chose to insert the Node in the proper spot rather than inserting in a pre-chosen spot
     * and using InsertionSort on the LinkedList at the end.
     * @param data The String to be added to the LinkedList
     */
    public void insertNode(String data) {
        Node newNode = new Node(data);
        //create a new Node for the String

        Node current = this.head;
        //use current to point at the current position in the LinkedList

        if(this.head == null || data.compareTo(current.getData()) < 0) {
            newNode.setNext(current);
            this.head = newNode;
            //if the list is empty or the node should go at the front of the list, insert specially
        }
        else{
            while(data.compareTo(current.getData()) > 0) {
                if(current.getNext() == null) {
                    current.setNext(newNode);
                    return;
                    //if the node should go at the end of the list, traverse to the end and insert
                }

                current = current.getNext();
            }

            newNode.setNext(current.getNext());
            current.setNext(newNode);
            //insert the new node in the correct spot and adjust next Nodes appropriately
        }
    }
}
