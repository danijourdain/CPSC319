import java.io.BufferedReader;
import java.io.FileReader;

//this code is buggy and does not work

public class Mar8 {
    public static void main(String[] args) {
        BSTree bst = new BSTree();

        int[] array = {6, 2, 8, 1, 4, 3};

        for(int a: array) {
            bst.insert(a);
        }
        bst.remove(2);
        bst.printTree();
    }

    public static void readFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("a3input1.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                char[] studentNumber = new char[7];
                line.getChars(1, 8, studentNumber, 0);
                System.out.println(new String(studentNumber));
            }
        }
        catch(Exception e) {
            System.out.println("error");
        }
    }
}

class BSTree {
    private static class BSNode {
        int data;
        BSNode left, right;

        BSNode(int d, BSNode l, BSNode r) {
            data = d;
            left = l;
            right = r;
        }
    }

    BSNode root;

    BSTree() {
        root = null;
    }

    void insert(int d) {
        insert(d, root);
    }

    BSNode insert(int d, BSNode t) { // t is root of curent subtree
        if(t == null) {
            t = new BSNode(d, null, null);
        }
        else if (d < t.data) {
            t.left = insert(d, t.left);
        }
        else if (d > t.data) {
            t.right = insert(d, t.right);
        }
        return t;
    }

    void remove(int d) {
        root = remove(d, root);
    }

    BSNode remove(int d, BSNode t) {
        if(t == null) {
            //not found
            return t;
        }
        else if(d < t.data) {
            t.left = remove(d, t.left);
        }
        else if(d > t.data) {
            t.right = remove(d, t.left);
        }
        else if(t.left != null && t.right != null){
            //step 1:find min node on right subtree
            //step 2: copy min node to t, and delete min node from right subtree
            
            t.data = findMin(t.right).data;
            remove(t.data, t.right);


        }
        else { //t has < 2 children
            if(t.left != null) {
                return t.right;
            }
            else {
                return t.right;
            }
        }
        return null;
    }

    BSNode findMin(BSNode t) {
        if(t == null) { // no min found
            return t;
        }
        else if(t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    boolean contains(int d, BSNode t) {
        if(d < t.data) {
            return contains(d, t.left);
        }
        else if (d > t.data) {
            return contains(d, t.right); 
        }
        else {
            return true;
        }
    }

    void printTree() {
        printTree(root);
    }

    void printTree(BSNode t) {
        if(t != null) {
            printTree(t.left);
            System.out.println(t.data);
            printTree(t.right);
        }
    }
}