import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class prints out the sum of all even numbers
 *      from the given numbers
 *
 * @version   $Id$ 1.0 Evensum.java
 *
 * @author   Niyati Shah
 *
 * Revisions:
 *	$Log$
 */
public class Evensum {
    public static int evenSum = 0;
    public static ArrayList<Integer> numbers = new ArrayList();

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int totalNumber = scan.nextInt();

        for (int index=0;index<totalNumber;index++){
            numbers.add(index,scan.nextInt());
        }

        findSum();
        printSum();
    }


    /**
     * @description : method to add only
     *              even numbers
     *
     *
     * @return null
     */
    public static void findSum(){

        for (int index  =0; index< numbers.size();index++){

            if(numbers.get(index)%2 ==0){
                evenSum += numbers.get(index);
            }
        }

    }

    /**
     * @description : method to print the sum of
     *              even numbers
     *
     *
     * @return null
     */
    public static void printSum(){
        System.out.println(evenSum);
    }
}
