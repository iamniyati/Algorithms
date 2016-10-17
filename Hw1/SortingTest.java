package sorting;

//import java.util.LinkedList;
import java.util.Random;
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

public class SortingTest {
    static long startTime, endTime;
    static Random randomn = new Random();

    public static void main(String[] args) {

        int[] input = {100, 1000, 10000, 100000, 100000, 1000000};
        System.out.println("Normal Distribution");
        for (int indexj = 0; indexj < input.length; indexj++) {
            test(input[indexj], 1);
        }
        System.out.println("Gaussian Distribution");


        for (int indexj = 0; indexj < input.length; indexj++) {
            test(input[indexj], 1);
        }

    }





    /**
     * @description : method to find the random in
     *                  normal and guassian form
     *
     * @param :  Number to choose guassian or random
     *              distribution
     *
     * @return random number
     */
    public static double getRandom(int distribution){
        if (distribution == 1){
            return randomn.nextDouble();
        }else {
            return (0.5)+randomn.nextGaussian()*0.001;

        }
    }

    /**
     * @description : method to test all the sorting algorithms
     *
     * @param: input: total number of inputs
     *
     * @param : distributon:  to choose guassian or random
     *              distribution
     *
     * @return random number
     */
    public static void test(int input, int distribution){
        double temp;
        double[] insertionnumbers = new double[input];
        double[] mergenumbers = new double[input];
        double[] bucketnumbers = new double[input];

        for (int index = 0; index < input; index++) {
            temp = getRandom(distribution);
            insertionnumbers[index] = temp;
            mergenumbers[index] = temp;
        }

        startTime = System.nanoTime();
        mergeSort(mergenumbers);
        endTime = System.nanoTime();
        System.out.println("Merge Sort for output "+ input+" is  " + (endTime-startTime) + " in nanosecond");

        System.out.println("---------");
        startTime = System.nanoTime();
        double[] sortedList= bucketsort(bucketnumbers);
        endTime = System.nanoTime();
        System.out.println("Bucket Sort for output "+ input+" is  " + (endTime-startTime) + " in nanosecond");
        System.out.println("---------");

        startTime = System.nanoTime();
        InsertionSort(insertionnumbers);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort for output "+ input+" is " + (endTime-startTime) + " in nanosecond");
        System.out.println("---------");
    }

    /**
     * @description : method to start mergesort
     *
     * @param : input array
     *
     * @return none
     */
    public static void mergeSort(double[] numbers) {
        mergeSortSplit(0, numbers.length-1,numbers);
    }

    /**
     * @description : method to split array
     *
     *
     * @param : firstIndex lastIndex and array of number
     *
     * @return none
     */
    public static void mergeSortSplit(int firstIndex, int lastIndex, double[] numbers) {

        if (firstIndex < lastIndex) {
            int midIndex = firstIndex + (lastIndex - firstIndex) / 2;
            mergeSortSplit(firstIndex, midIndex, numbers);
            mergeSortSplit(midIndex + 1, lastIndex, numbers);
            merge(firstIndex, midIndex, lastIndex, numbers);
        }
    }

    /**
     * @description : method to perform sorting on split array
     *
     *
     * @param : firstIndex, lastIndex, midIndex and array of number
     *
     * @return none
     */

    public static void merge(int firstIndex, int midIndex, int lastIndex, double[] numbers) {
        double [] tempArray = new double[numbers.length];
        for (int i = firstIndex; i < lastIndex; i++) {
            tempArray[i] = numbers[i];
        }
        int i = firstIndex;
        int j = midIndex + 1;
        int k = firstIndex;
        while (i < midIndex && j < lastIndex) {
            if (tempArray[i] <= tempArray[j]) {
                numbers[k] = tempArray[i];
                i++;
            } else {
                numbers[k] = tempArray[j];
                j++;
            }
            k++;
        }
        while (i <= midIndex) {
            numbers[k] = tempArray[i];
            k++;
            i++;
        }
    }

    /**
     * @description : method to perform bucket sort on array
     *
     *
     * @param : array of number
     *
     * @return sorted array
     */
    public static double[] bucketsort(double[] number) {

        int length = number.length;
        double max = getMax(number);
        linkedlist<Double>[] bucket = new linkedlist[number.length];

        for (int i = 0; i < number.length; i++) {
            bucket[i] = new linkedlist<Double>();
        }
        int index;
        for (int i = 0; i < number.length; i++) {
            if (max < 1)
                index = (int) Math.floor(number[i] * length);
            else
                index = (int) Math.floor(number[i] * length / max);
            bucket[index].add(number[i]);
        }
        int j = 0;
        linkedlist<Double> sortedList;
        double[] result = new double[number.length];
        for (int i = 0; i < bucket.length; i ++){
            sortedList = insertionSort(bucket[i]);

            while (sortedList.size()> 0){
                result[j] = sortedList.pop();
                j++;
            }
        }
        return result;
    }

    /**
     * @description : method to perform insertion sort on
     *                  linked list of numbers
     *
     *
     * @param : linkedlist of number
     *
     * @return sortedlist
     */
    public static linkedlist<Double> insertionSort(linkedlist<Double> lst){
        for(int i=0;i<lst.size();i++){
            double temp = lst.get(i);
            for(int j=i-1;j>=0 && temp<lst.get(i);j--){

                lst.add(j+1,lst.get(j+1));
                lst.add(j+1,temp);
            }
        }

        return lst;
    }

    /**
     * @description : method to get maximum number in array
     *
     *
     * @param : array of number
     *
     * @return maximum number
     */
    public  static double getMax (double[] a){
        double max = 0.0;
        for( int i = 0; i < a.length; i++){
            if(a[i]> max){
                max = a[i];
            }
        }
        return max;
    }

    /**
     * @description : method to perform insertion sort on array
     *
     *
     * @param : array of number
     *
     * @return none
     */
    public static void InsertionSort(double[] data) {

        double temp;
        for (int indexi = 1; indexi < data.length; indexi++) {
            for (int indexj = indexi; indexj > 0; indexj--) {
                if (data[indexj] < data[indexj - 1]) {
                    temp = data[indexj];
                    data[indexj] = data[indexj - 1];
                    data[indexj - 1] = temp;
                }
            }
        }


    }


}



