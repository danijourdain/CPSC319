public class BinarySearchTree {
    private Node root;

    /**
     * Default constructor for the binary search tree
     */
    public BinarySearchTree() {
        this.root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    /**
     * Insertion function for the binary search tree.
     * @param data The data to be added to a new node in the tree.
     */
    public void insert(String data) {
        /* Code for insertion into a binary search tree adapted from slide 19 of Feb 16 lecture nodes */

        Node current = this.root;
        Node parent = null;

        String lastName = Data.getLastName(data);

        while(current != null) {
            //traverse through the tree moving left and right as appropriate until an empty spot is found
            parent = current;
            if(lastName.compareTo(current.getLastName()) > 0) {
                current = current.getRight();
            }
            else {
                current = current.getLeft();
            } 
        }

        Node newNode = new Node(data, parent, null, null);

        if(this.root == null) {
            this.root = newNode;
        }
        else if(lastName.compareTo(parent.getLastName()) > 0) {
            parent.setRight(newNode);
        }
        else {
            parent.setLeft(newNode);
        }
        //add the new node into the appropriate spot in the tree
    }

    public void inOrder(Node current) {
        /* Depth-first, in order traversal adapted from slide 27 of Feb 16 lecture notes */

        if(current != null) {
            inOrder(current.getLeft());

            current.printNode();
            inOrder(current.getRight());
        }
    }
}
