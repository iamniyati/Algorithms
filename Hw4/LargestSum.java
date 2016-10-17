import java.io.IOException;
import java.util.Scanner;

/**
 * Program to calculate the largest possible sum
 * of elements in an increasing subsequence in an
 * array.
 *
 * @version   $Id$ 1.0 LargestSum.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *              
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class LargestSum {
    static int totalNumber;
    static int[] numbers;
    static int[] solutionArray;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        totalNumber = sc.nextInt();
        numbers = new int[totalNumber];
        for (int index = 0; index < totalNumber; index++) {
            numbers[index] = sc.nextInt();
        }
        findLargestSun(numbers, totalNumber);
    }
    /**
     * @description : Function to calculate the largest possible sum
     *                  of elements in an increasing subsequence in an
     *                  array.
     * @param :int number Array: Array of numbers
     * @param : int totalNumber: total number of numbers in the array
     * @return None
     *
     */
    public static void findLargestSun(int[] numbers, int totalNumber){
        solutionArray = new int[totalNumber];
        for(int index =0; index<totalNumber;index++){
            solutionArray[index] = numbers[index];
        }
        for(int indexi = 1;indexi<totalNumber;indexi++){
            for(int indexj =0;indexj<indexi;++indexj){
                if(numbers[indexi]>numbers[indexj]){
                    if(solutionArray[indexi]<solutionArray[indexj]+numbers[indexi]){
                        solutionArray[indexi] = solutionArray[indexj]+numbers[indexi];
                    }
                }
            }
        }
        int max = 0;
        for(int index =0; index<totalNumber;index++){
            if(solutionArray[index]>max){
                max = solutionArray[index];
            }
        }
        System.out.println(max);

    }

}
