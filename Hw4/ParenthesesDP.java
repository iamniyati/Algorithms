import java.util.ArrayList;
import java.util.Scanner;


/**
 * Program to calculate the largest value of the
 * given expression
 *
 * @version   $Id$ 1.0 ParenthesesDP.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *            
 *
 * Revisions:
 *	$Log$
 *
 *
 */
//5 - 8 + 7 - 4 - 8 + 9
public class ParenthesesDP {
    public static ArrayList<String> expression;
    public static int[][] minArray;
    public static int[][] maxArray;
    public static ArrayList<Integer> number ;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();
        String[] line = expr.split(" ");
        expression = new ArrayList<String>();
        number = new ArrayList<Integer>();
        int j=0, k=0;
        for(int i =0;i < line.length; i++){
            if(Character.isDigit(line[i].charAt(0))){
                number.add(j,Integer.parseInt(line[i]));
                j++;
            }else {
                expression.add(k, line[i]);
                k++;
            }
        }
        findMaxValue(expression, number);
    }
    /**
     * @description : Function to calculate the largest possible value
     *                  of the given expression
     * @param : Arraylist expression: String of expression
     * @return None
     *
     */
    public static void findMaxValue(ArrayList<String> expr, ArrayList<Integer> number){
        int length = number.size()+1;
        minArray = new int[length][length];
        maxArray = new int[length][length];
        int j=0;
        int tmp1=0, tmp2=0, tmp3=0,tmp4=0,tmp5= 0,tmp6 =0;

        for(int i=1;i<length;i++){
            minArray[i][i] = number.get(j);
            maxArray[i][i] = number.get(j);
            j++;
        }

        for(int s =1;s<length-1;s++){
            for(int i =1;i<length-s;i++){
              j = i+s;
                maxArray[i][j] = -99999999;
                minArray[i][j] = 99999999;
                for(int k=i;k<=j-1;k++){
                    if(expr.get(k-1).equals("+")){
                        tmp1 = maxArray[i][k]+maxArray[k+1][j];
                        tmp2 = maxArray[i][k]+minArray[k+1][j];
                        tmp3 = minArray[i][k]+maxArray[k+1][j];
                        tmp4 = minArray[i][k]+minArray[k+1][j];

                        tmp5 = Math.min(tmp1,Math.min(tmp2,Math.min(tmp3,tmp4)));
                        tmp6 = Math.max(tmp1,Math.max(tmp2,Math.max(tmp3,tmp4)));
                        if(tmp6>maxArray[i][j]){
                            maxArray[i][j] =tmp6;
                        }
                        if(tmp5<minArray[i][j]){
                            minArray[i][j] =tmp5;
                        }

                    }else if(expr.get(k-1).equals("-")){
                        tmp1 = maxArray[i][k]-maxArray[k+1][j];
                        tmp2 = maxArray[i][k]-minArray[k+1][j];
                        tmp3 = minArray[i][k]-maxArray[k+1][j];
                        tmp4 = minArray[i][k]-minArray[k+1][j];
                        tmp5 = Math.min(tmp1,Math.min(tmp2,Math.min(tmp3,tmp4)));
                        tmp6 = Math.max(tmp1,Math.max(tmp2,Math.max(tmp3,tmp4)));
                        if(tmp6>maxArray[i][j]){
                            maxArray[i][j] =tmp6;
                        }
                        if(tmp5<minArray[i][j]){
                            minArray[i][j] =tmp5;
                        }
                    }

                }

            }
        }

        System.out.println(maxArray[1][length-1]);
    }




}
