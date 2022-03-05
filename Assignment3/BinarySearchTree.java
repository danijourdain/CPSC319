public class BinarySearchTree {
    private Node root;

    /**
     * Default constructor for a Binary search tree
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * This method takes the data, creates a new Node for it, and inserts it in the
     * appropriate spot in the binary search tree.
     * @param data The data to be added to a Node.
     */
    public void insert(String data) {
        /* code adapted from February 16 lecture notes, Slide 19 */
        Node current = this.root;
        Node parent = null;

        String lName = Data.findLastName(data);
        Node newNode = new Node(data);
        //create the new node to be inserted

        while(current != null) {
            parent = current;
            if(lName.compareTo(current.getData().getLastName()) > 0) {
                current = current.getRight();
            }
            else {
                current = current.getLeft();
            }
            //traverse through the tree, moving left if the new last name is less than
            //the current one, and moving right if it's greater
        }

        if(root == null) {
            root = newNode;
            //if the list is empty, insert as the root
        }
        else if(lName.compareTo(parent.getData().getLastName()) > 0) {
            parent.setRight(newNode);
        }
        else {
            parent.setLeft(newNode);
        }
        //otherwise insert on the current side of the parent node
    }

    /**
     * This method deletes a Node from the binary search tree. 
     * @param key The last name of the node to be deleted.
     */
    public void delete(String key) {
        
    }

    /**
     * Getter method for root of the tree
     * @return the value of root
     */
    public Node getRoot() {
        return this.root;
    }
}
