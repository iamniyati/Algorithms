

import java.util.Scanner;

/**
 * This class prints out yes if
 * a) A number occurs more than n/2 times
 * b) A number occurs more than n/3 times
 *
 * @version   $Id$ 1.0 MajorityNew.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *              & Rudresh Pandit(rmp7494) Section2
 *
 * Revisions:
 *	$Log$
 *
 *
 */

public class Majority {
    public static int[] numbers;
    public static int totalNumber;
    public static int count;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        totalNumber = scan.nextInt();
        numbers = new int[totalNumber];
        for (int index = 0; index < totalNumber; index++) {
            numbers[index] = (scan.nextInt());
        }

        int mid = (numbers.length)/2;
        int pivot = getElement(numbers,mid);
        checkMajority(pivot,numbers);
    }

    /**
     * @description : method to get the median in the array
     *
     * @param : array of numbers, mid Index of the array
     *
     * @return int median number
     */
    public static int getElement(int []numbers, int median){
        int pivot = numbers[median];
        int count=0;
        int[] tempArray = new int[numbers.length];
        int temp;
        int firstIndex=0;
        int lastIndex = tempArray.length-1;

        for(int index =0;index<numbers.length;index++) {
                if (numbers[index]< numbers[median] ) {
                    tempArray[firstIndex] = numbers[index];
                    firstIndex++;
                } else if(numbers[index]> numbers[median] ) {
                    tempArray[lastIndex] = numbers[index];
                    lastIndex--;
                }   else if(numbers[index]==numbers[median]){
                    count++;
            }
        }
        temp = firstIndex;

        while(count!=0){
            tempArray[temp]=pivot;
            temp++;
            count--;
        }
        if(median<firstIndex){
            int [] firstHalf = new int[firstIndex];
            for(int index = 0;index<firstIndex;index++){
                firstHalf[index]= tempArray[index];
            }
            getElement(firstHalf,median);
        }else if(median>=firstIndex && median<=lastIndex){

                return tempArray[firstIndex];
        }else{
            int[] lastHalf = new int[lastIndex];
            int arrayIndex =0;
            for(int index = median;index<lastIndex;index++){
                lastHalf[arrayIndex]= tempArray[index];
                arrayIndex++;
            }
            getElement(lastHalf,median-lastIndex-1);

        }
    return tempArray[firstIndex];
    }

    /**
     * @description : method to check if the median in the array
     *                 has the recurance of more than half the array
     *                 and more than a third of the array
     *
     * @param : median element,array of numbers
     *
     * @return none
     */
    public static void checkMajority(int pivot, int[] numbers){
        for(int index =0;index<numbers.length;index++){
            if(numbers[index] == pivot){
                count++;
            }
        }
        if (count>numbers.length/2){
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
        if(count>numbers.length/3){
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
    }

}

