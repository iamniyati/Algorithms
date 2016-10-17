import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class prints out the duplicate in given set of sorted numbers
 * where the total numbers are between 0 and N (both inclusive)
 *
 * @version   $Id$ 1.0 OneDup.java
 *
 * @author   Niyati Shah
 *
 * Revisions:
 *	$Log$
 */
public class OneDup{
    public static int totalNumber;
    public static int[] numbers;

    public static void main(String[] args){

        Scanner scan        = new Scanner(System.in);
        totalNumber         = scan.nextInt();
        numbers = new int[totalNumber+2];
        for (int index=0;index<=totalNumber+1;index++){
            numbers[index] = scan.nextInt();
        }

        int ActualSum       = ActualSumofNumbers(numbers);
        int trueSum         = SumOfNNumbers(totalNumber);
        int duplicate       = findDuplicate(ActualSum,trueSum);
        printDuplicate(duplicate);
    }

    /**
     * @description : method to find the duplicate
     *                  number from the array
     *
     * @return Sum of N Numbers
     */
    public static int findDuplicate(int actualSum, int trueSum){
        return actualSum - trueSum;

    }

    /**
     * @description : method to sum N numbers
     *                  of the given array
     *
     *
     * @return Sum of the Numbers
     */
    public static int ActualSumofNumbers(int[] numbers){
        int tempSum      = 0;
        for (int index=0;index<numbers.length;index++){
            tempSum     += numbers[index];
        }
        return tempSum;
    }

    /**
     * @description : method to sum N numbers
     *
     *
     * @return Sum of N Numbers
     */
    public static int SumOfNNumbers( int number){
        int sum     = number*(number+1)/2;          //Summation of N Numbers
        return sum;
    }

    /**
     * @description : method to print the duplicate of
     *              given numbers
     *
     *
     * @return null
     */
    public static void printDuplicate(int number){
        System.out.println(number);

    }
}