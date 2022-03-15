import java.util.Stack;

public class Mar15 {
    public static void main(String[] args) {
        BSTree bst = new BSTree();

        int[] array = {6, 2, 8, 1, 4, 3};

        for(int a: array) {
            bst.insert(a);
        }

        bst.inOrderIterative();
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
        root = insert(d, root);
    }

    BSNode insert(int d, BSNode t) { // t is root of curent subtree
        if(t == null) {
            return new BSNode(d, null, null);
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
        if(d < t.data) {
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
            return (t.left == null) ? t.right : t.left;
        }
        return t;
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
        if(t == null) {
            return false;
        }
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

    int findMinRecursive() {
        return findMin(root).data;
    }

    int findMaxRecursive() {
        return findMax(root).data;
    }

    BSNode findMax(BSNode current) {
        if(current.right == null) {
            return current;
        }
        else {
            return findMax(current.right);
        }
    }

    int findHeight() {
        return findHeight(root);
    }

    int findHeight(BSNode t) {
        if(t == null) {
            return 0;
        }
        return Math.max(findHeight(t.left), findHeight(t.right)) + 1;
    }

    void inOrderIterative() {
        Stack<BSNode> stack = new Stack<>();
        BSNode p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            }
            else {
                BSNode q = stack.pop();
                System.out.println(q.data);
                p = q.right;
            }
        }
    }    

    int heightIterative() {
        BSNode p = root;
        
        Stack<BSNode> stack = new Stack<>();
        Stack<Integer> heights = new Stack<>();

        int height = 0, answer = 0;

        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                heights.push(++height);
                answer = Math.max(height, answer);
                p = p.left;
            }
            else {
                BSNode q = stack.pop();
                p = q.right;
                height = heights.pop();
            }
        }

        return answer;
    }
}
