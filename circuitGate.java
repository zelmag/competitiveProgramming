import java.io.*;
import java.util.*;

/*
 * The problem statement can be found at http://www.spoj.com/problems/AGGRCOW/
 *
 * The online judge gives TLE, but the sample test case works. Can you find the
 * bug?
 */
public class circuitGate {
    static int numberInputs;
    static boolean inputs[];

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        numberInputs = input.nextInt();
        inputs = new boolean[numberInputs];
        input.nextLine();
        String values = input.nextLine();
        StringBuilder valuesB = new StringBuilder(values);
        //DELETE SPACES
        for (int i = 0; i < valuesB.length(); i++) {
            if (valuesB.charAt(i) == ' ') {
                valuesB.deleteCharAt(i);
            }
        }
        //PUT IN THE LOOK UP TABLE
        for (int i = 0; i < valuesB.length(); i++) {
//           System.out.println("Current value "+valuesB.charAt(i));
            boolean currInput = valuesB.charAt(i) == 'T';
            inputs[i] = currInput;
        }
//       System.out.println(Arrays.toString(inputs));


        //next line of input...AB * CD + - +
        String gatesEval = input.nextLine();
        //DELETE SPACES FOR GATES
        StringBuilder s = new StringBuilder(gatesEval);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                s.deleteCharAt(i);
            }
        }
//        System.out.println("GATES EVAL "+s);
        //REPLACE WITH T AND F!!
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {

                boolean value = inputs[s.charAt(i) - 'A'];
                s.deleteCharAt(i);
                if (value) {
                    s.insert(i, "T");
                } else {
                    s.insert(i, "F");
                }

            }
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }
        StringBuilder temp = new StringBuilder();
        while (stack.size() > 0) {
            char curr = stack.peek();
            if (curr == '+' || curr == '*' || curr == '!') {
                char op = stack.pop();
                temp.insert(0, op);
            } else {
                //FOUND A VALUE! Can I do operations?
                temp.insert(0, stack.pop());
                for (int i = 0; i < temp.length(); i++) {
                    //is there a char after me and an operation of 2 after that OR
                    if (temp.charAt(i) == 'T' || temp.charAt(i) == 'F') {
                        //is there a - after me??
                        char me = temp.charAt(i);
                        if (i + 1 < temp.length() && temp.charAt(i + 1) == '-') {
                            temp.deleteCharAt(i);
                            temp.deleteCharAt(i);
                            if (me == 'T') {
                                stack.push('F');
                            } else {
                                stack.push('T');
                            }
                            for(int j=0; j<temp.length();j++){
                                char a = temp.charAt(0);
                                temp.deleteCharAt(0);
                                stack.push(a);
                            }
                        } else if (i + 1 < temp.length() && i + 2 < temp.length() && (temp.charAt(i + 1) == 'T' || temp.charAt(i + 1) == 'F') &&
                                (temp.charAt(i + 2) == '*' || temp.charAt(i + 2) == '+')) {
                            char a = temp.charAt(i);
                            boolean aBool = a == 'T';
                            char b = temp.charAt(i + 1);
                            boolean bBool = b == 'T';
                            char op = temp.charAt(i + 2);
                            temp.deleteCharAt(i);
                            temp.deleteCharAt(i);
                            temp.deleteCharAt(i);
                            boolean result;
                            if (op == '+') {
                                result = aBool || bBool;
                                if (result) {
                                    stack.push( 'T');
                                } else {
                                    stack.push( 'F');
                                }
                            } else if (op == '*') {
                                result = aBool && bBool;
                                if (result) {
                                    stack.push( 'T');
                                } else {
                                    stack.push( 'F');
                                }
                            }
                            for(int j=0; j<temp.length();j++){
                                b = temp.charAt(0);
                                temp.deleteCharAt(0);
                                stack.push(b);
                            }

                        }
                    }
                    //is there a - after me??
                }
            }
        }
        System.out.println(temp);
    }
}
