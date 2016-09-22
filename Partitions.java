
import java.io.IOException;
import java.util.Scanner;


/**
 * Program to calculate the number of even-sum partitions
 * possible & the number of odd-sum partitions possible
 * in the given array
 *
 *
 * @version   $Id$ 1.0 Partitions.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class Partitions {
    static int oddNumber = 0;
    static int sum;
    static int evenSumcount;
    static int [] oddSumCount;
    static int[] numbers;
    static int totalNumber;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        totalNumber = sc.nextInt();
        numbers = new int[totalNumber];
        for (int index = 0; index < totalNumber; index++) {
            numbers[index] = sc.nextInt();
        }
        findEvenSumSequence(numbers, totalNumber);
        findOddSumSequence(numbers, totalNumber);
    }

    /**
     * @description : Function to calculate the number of even-sum partitions.
     * @param :int number Array: Array of numbers
     * @param : int totalNumber: total number of numbers in the array
     * @return None
     *
     */

    public static void findEvenSumSequence(int[] numbers, int totalNumber) {

        evenSumcount = 1;
        sum = 0;
        for (int index = 0; index < totalNumber; index++) {
            sum = sum + numbers[index];
            if (sum % 2 == 0) {
                if (index + 1 != totalNumber) {
                    evenSumcount = 2 * evenSumcount;
                }
            }
            if (numbers[index] % 2 != 0) {
                oddNumber += 1;
            }

        }
            if (oddNumber % 2 != 0) {
                System.out.println("0");
            } else {

                System.out.println(evenSumcount);
            }
        }

    /**
     * @description : Function to calculate the number of odd-sum partitions.
     * @param :int number Array: Array of numbers
     * @param : int totalNumber: total number of numbers in the array
     * @return None
     *
     */

    public static void findOddSumSequence(int[] numbers, int totalNumber) {
        oddNumber = 0;
        boolean flag = true;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 != 0) {
                oddNumber += 1;
            }
        }
        if (oddNumber != 0) {
            oddSumCount = new int[totalNumber + 1];
            int[] temp = new int[oddNumber+1];
            oddSumCount[0] = 0;
            int j = 0;
            for (int i = 1; i <= totalNumber; i++) {

                if (i == 1 & numbers[i - 1] % 2 != 0) {
                    temp[j] = 1;
                    oddSumCount[i] = 1;
                    flag = false;
                } else {
                    if (numbers[i - 1] % 2 != 0) {

                        if(j==0 && flag ==true){
                            oddSumCount[i] = 1;
                        }else {
                            oddSumCount[i] = temp[j];
                        }
                        j++;
                        temp[j] = oddSumCount[i - 1] + oddSumCount[i];

                    } else {

                        oddSumCount[i] = oddSumCount[i - 1];
                        temp[j] = temp[j] + oddSumCount[i];

                    }
                }
            }
            System.out.println(oddSumCount[oddSumCount.length-1]);
        }else{
            System.out.println("0");
        }

    }
    }

