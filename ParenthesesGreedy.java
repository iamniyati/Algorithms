import java.util.ArrayList;
import java.util.Scanner;



/**
 * Program to calculate the largest value of the
 * given expression
 *
 * @version   $Id$ 1.0 ParenthesesGreedy.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class ParenthesesGreedy {
    public static ArrayList<String> expression;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();
        String[] line = expr.split(" ");
        expression = new ArrayList<>();
        for(int i =0;i<line.length;i++){
                expression.add(i,line[i]);

        }
        findMaxSum(expression);
    }
    /**
     * @description : Function to calculate the largest possible value
     *                  of the given expression
     * @param : Arraylist expression: String of expression
     * @return None
     *
     */
    public static void findMaxSum(ArrayList<String> expression ){
        int i=1;
      int a, b;
        String s;
        int sum, multiply=0;
        //Calculate all the additions
        // of values in the expression
        while(i!= expression.size()) {
            if (expression.size() < i) {
                break;
            } else {
                s = expression.get(i);
                if (s.equals("+")) {            // check if there the character is + sign
                    a = Integer.parseInt(expression.get(i - 1));
                    b = Integer.parseInt(expression.get(i + 1));
                    sum = a + b;
                    expression.set(i + 1, "" + sum);
                    expression.remove(i-1);
                    expression.remove(i-1);

                } else if (s.equals("*")) {         //    check if there the character is * sign
                    i =i+2;

                } else if (Character.isDigit(s.charAt(0))) {        //   check if there the character is digit
                    i ++;
                }
            }
        }


        i=1;
        //calculate all the multiplication
        // of values in the expression
        while(i!= expression.size()){
            if (expression.size() < i) {
                break;
            } else {
                s = expression.get(i);
                if (s.equals("*")){     //    check if there the character is * sign
                    a = Integer.parseInt(expression.get(i-1));
                    b = Integer.parseInt(expression.get(i+1));
                    multiply = a*b;
                    expression.set(i+1, "" + multiply);
                    expression.remove(i-1);
                    expression.remove(i-1);

                }else{              //    check if there the character is digit
                    i=i+1;
                }
            }
        }
        System.out.print(expression.get(0));
    }
}
