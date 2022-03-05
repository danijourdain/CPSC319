import java.util.LinkedList;

public class Mar1 {
    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(String t: tokens) {
            if(t.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            }
            else if(t.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            }
            else if(t.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            }
            else if(t.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            }
            else {
                stack.push(Integer.parseInt(t));
            }
        }

        return stack.peek();    //peek doesn't remove the element
    }

    public static boolean validParentheses(String token) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] array = token.toCharArray();
        for(char a: array) {
            if(a == '(' || a == '[' || a =='{') {
                stack.push(a);
            }
            else {
                char leftBracket = stack.pop();
                if(leftBracket != '(' && a == ')') {
                    return false;
                }

                else if(leftBracket != '{' && a == '}') {
                    return false;
                }
                
                else if(leftBracket != '[' && a == '}') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
