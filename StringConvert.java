import java.io.IOException;
import java.util.Scanner;

/**
 * Program to calculate the string conversion cost
 *
 * @version   $Id$ 1.0 StringConvert.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class StringConvert {
    public static String stringOne;
    public static String stringTwo;
    public static int[][] solutionArray;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
         stringOne= sc.nextLine();
         stringTwo = sc.nextLine();

        doStringConvert(stringOne,stringTwo);

    }

    /**
     * @description : Function to calculate the string conversion cost
     *
     * @param : String a:  first string
     * @param : String a:  second string
     * @return None
     *
     */
    public static void doStringConvert(String stringOne, String stringTwo){
        String[] stringa = new String[stringOne.length()+1];
        String[] stringb = new String[stringTwo.length()+1];

        // add the string into string array where
        // first element in the array is space
        for(int i =0; i<stringa.length;i++){
            if(i == 0){
                stringa[i] = " ";
            }else{
                    stringa[i] = ""+stringOne.charAt(i-1);
                }
            }
        // add the string into string array where
        // first element in the array is space
        for(int i =0; i<stringb.length;i++){
            if(i == 0){
                stringb[i] = " ";
            }else{
                stringb[i] = ""+stringTwo.charAt(i-1);
            }
        }

        // initialize the solution array
        // where 1st row is multiple of 3
        // and 1st column is multiple of 4
        solutionArray = new int[stringa.length][stringb.length];

        for(int i =0;i<stringa.length;i++){
            solutionArray[i][0] = 3*i;
        }
        for(int i =0;i<stringb.length;i++){
            solutionArray[0][i] = 4*i;
        }


        //perform the conversion cost
        for(int indexi=1;indexi<stringa.length;indexi++){
            for(int indexj =1;indexj<stringb.length;indexj++){

                if(stringa[indexi].equals(stringb[indexj])){
                    solutionArray[indexi][indexj] =solutionArray[indexi-1][indexj-1];
                }else {
                    if(indexi -2 < 0){
                        solutionArray[indexi][indexj] =     getmin( 3+solutionArray[indexi-1][indexj],
                                                                    4+solutionArray[indexi][indexj-1],999);
                    }else {
                        solutionArray[indexi][indexj] = getmin( 3 + solutionArray[indexi - 1][indexj],
                                                                4 + solutionArray[indexi][indexj - 1],
                                                                5 + solutionArray[indexi - 2][indexj - 1]);
                    }

                }
            }
        }


        System.out.println(solutionArray[stringa.length-1][stringb.length-1]);
    }

    /**
     * @description : Function to calculate minimum
     *                  of three values
     *
     * @param : int a:  first number
     * @param : int b:  second number
     * @param : int c:  third number
     * @return min of the three numbers
     *
     */
   public static int getmin(int a, int b, int c) {

       if (a <= b && a <= c) {
           return a;
       } else if (b <= a && b <= c) {
           return b;
       } else {
           return c;
       }
   }
}
