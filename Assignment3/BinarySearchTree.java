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
     * Insertion function for the binary search tree. Adds a new node to the tree with the data included in data.
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
        else if(lastName.compareTo(parent.getLastName()) < 0) {
            parent.setLeft(newNode);
        }
        else {
            System.out.println("Error! Duplicate last name! Skipping insertion for " + lastName);
            return;
        }
        //add the new node into the appropriate spot in the tree
    }

    /**
     * This method will take a key and delete the node associated with that key.
     * @param key The last name of the node to be deleted.
     */
    public void delete(String key) {
        /* Code for deleting a node based off of descriptions from Feb 18 Lecture Notes */
        
        Node doomed = search(this.root, key);
        //find the node to be deleted

        if(doomed == null) {
            //if the key is not found, print a message and leave the delete function
            System.out.println(key + " is not in tree");
            return;
        }

        if(doomed.getLeft() == null && doomed.getRight() == null) {
            if(doomed.getLastName().compareTo(doomed.getParent().getLastName()) < 0) {
                doomed.getParent().setLeft(null);
                //if the doomed node is the left child of it's parent, set the parent's left child to null
            }
            else {
                //otherwise set the right child to null
                doomed.getParent().setRight(null);
            }
        }
        else if(doomed.getLeft() == null) {
            //if the dooomed node only has a right child, use the deleteOneChild helper function
            deleteOneChild(doomed, doomed.getRight());
        }
        else if(doomed.getRight() == null) {
            //if the doomed node only has a legt child, use th deleteOne Child helper function
            deleteOneChild(doomed, doomed.getLeft());
        }
        else {
            Node successor = minNode(doomed.getRight());
            //find the smallest node in the right subtree to fill the spot being deleted\

            delete(successor.getLastName());
            //splice the successor out of its current spot in the tree recursively

            successor.setLeft(doomed.getLeft());
            successor.setRight(doomed.getRight());
            //set the children of the successor to be the children of the doomed node

            if(doomed.equals(this.root) == false && doomed.getLastName().compareTo(doomed.getParent().getLastName()) < 0) {
                doomed.getParent().setLeft(successor);
                successor.setParent(doomed.getParent());
                //if the doomed node is the left child of it's parent, set the parent's left child to the successor
            }
            else if (doomed.equals(this.root) == false) {
                //otherwise set the right child to successor
                doomed.getParent().setRight(successor);
                successor.setParent(doomed.getParent());
            }
            else {
                this.root = successor;
            }
        }

        doomed.setLeft(null);
        doomed.setRight(null);
        doomed.setParent(null);
        //set all pointers for the doomed node to null as the last step
    }

    /**
     * This method will delete a node if it has one child node. This is a helper method for the main delete method.
     * @param doomed The node to be deleted.
     * @param child The child node of the doomed node.
     */
    private void deleteOneChild(Node doomed, Node child) {
        if(doomed.getLastName().compareTo(doomed.getParent().getLastName()) < 0) {
            //if the doomed node is the left child of it's parent, set the parent's left child to the child of the doomed node
            doomed.getParent().setLeft(child);
        }
        else {
            //otherwise set the parent's righ child to the doomed node's child
            doomed.getParent().setRight(child);
        }
    }

    /**
     * This is a helper method for delete. This method finds the successor node if the doomed node has two children. 
     * It does this by finding the smallest value on the right subtree of the doomed node.
     * @param start The doomed node.
     * @return The smallest node in the right subtree of the doomed node.
     */
    private Node minNode(Node start) {
        /* Code for minNode adapted from geeksforgeeks.org/binary-search-tree-set-2-delete/ */

        while(start.getLeft() != null) {
            start = start.getLeft();
        }

        return start;
    }

    /**
     * This method searches for a Node in a binary search tree.
     * @param current The node to start searching from.
     * @param key The value to search for.
     * @return The node where the key was found, or if it does not exist, return null.
     */
    private Node search(Node current, String key) {
        /* Code for searching taken from Feb 18 lecture notes, slide 38 */

        while(current != null) {
            if(key.equals(current.getLastName())) {
                return current;
            }
            else if(key.compareTo(current.getLastName()) < 0) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }

        return null;
    }

    /**
     * This method traverses the binary search tree depth-first, in order and stores the result in a string.
     * @param current The node to begin the traversal at.
     * @return The results of the depth-first traversal of the tree where each node's data is on its own line.
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
     * This method traverses the tree breadth-first and stores the results in a multi-line string
     * @param size the max size a queue will need to be to store the data
     * @return the results of the traversal as a multi-line string.
     */
    public String breadthFirst(int size) {
        /* code for breadth first traversal adapted from https://www.geeksforgeeks.org/level-order-tree-traversal/ */

        Queue queue = new Queue(size);
        StringBuilder result = new StringBuilder();

        queue.enqueue(this.root);
        //add the root to the queue

        while(queue.isEmpty() == false) {
            Node temp = queue.dequeue();
            //take the first element out of the queue

            result.append(temp.getNodeData() + "\n");
            //add the current element's data to the result 

            if(temp.getLeft() != null) {
                queue.enqueue(temp.getLeft());
            }
            if(temp.getRight() != null) {
                queue.enqueue(temp.getRight());
            }
            //add the children to the queue if they exist
        }

        return result.toString();
    }
}
