import java.util.Scanner;

/**
 * This class counts the number of swaps required to
 * arrange the 7 yr old students in ascending order of height
 *  then the professor and then the 8 year old students in
 *  descending order of height.
 *
 *
 * @version   $Id$ 1.0 Picture.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *           
 *
 * Revisions:
 *	$Log$
 */
public class Picture {

    int total;
    static Node[] people;
    static int count = 0;
    static Node node;
    static int eightYearOldCount;
    static int sevenYearOldsCount;

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        Picture pic = new Picture();

        pic.total = sc.nextInt();
        pic.people = new Node[pic.total];

         for (int i = 0; i < pic.total; i++) {
             int age = sc.nextInt();
             double height = sc.nextDouble();
             people[i] = new Node(age, height);
         }
        count = MergeSort(pic.people);
        System.out.println(count);
    }


    /**
     * @description : method to perform merge sort in  array
     *
     *
     * @param : array of numbers
     *
     * @return none
     */
    public static int MergeSort(Node[] array) {

        int first = 0;
        if (array.length > 1) {
            int mid = array.length / 2;
            Node firstArray[] = new Node[mid];
            Node lastArray[] = new Node[array.length - firstArray.length];
            for (int index = 0; index < mid; index++) {
                firstArray[index] = array[index];
            }
            for (int index = mid; index < array.length; index++) {
                lastArray[first] = array[index];
                first++;
            }

            MergeSort(firstArray);
            MergeSort(lastArray);

            int leftIndex = 0;
            int rightIndex = 0;
            int resultIndex = 0;

            while (leftIndex < firstArray.length && rightIndex < lastArray.length) { 
                if(firstArray[leftIndex].age==lastArray[rightIndex].age){
                    if(firstArray[leftIndex].age ==7){
                        if (firstArray[leftIndex].height < lastArray[rightIndex].height) {
                            array[resultIndex] = firstArray[leftIndex];
                            leftIndex++;
                        } else {
                            array[resultIndex] = lastArray[rightIndex];
                            rightIndex++;
                            count = count+(firstArray.length-leftIndex);
                        }
                    }else {
                        if (firstArray[leftIndex].height > lastArray[rightIndex].height) {
                            array[resultIndex] = firstArray[leftIndex];
                            leftIndex++;
                        } else {
                            array[resultIndex] = lastArray[rightIndex];
                            rightIndex++;
                            count = count+(firstArray.length-leftIndex);
                        }
                    }
                    }else if(firstArray[leftIndex].age ==7 && lastArray[rightIndex].age ==8){
                        array[resultIndex] = firstArray[leftIndex];
                        leftIndex++;

                    }else if(firstArray[leftIndex].age ==8 && lastArray[rightIndex].age ==7) {
                        array[resultIndex] = lastArray[rightIndex];
                        rightIndex++;
                    count = count+(firstArray.length-leftIndex);
                    }else if((firstArray[leftIndex].age ==7 && lastArray[rightIndex].age ==44 )||
                        (firstArray[leftIndex].age ==44 && lastArray[rightIndex].age ==8 )){
                        array[resultIndex] = firstArray[leftIndex];
                        leftIndex++;

                    } else if ((firstArray[leftIndex].age ==44 && lastArray[rightIndex].age ==7 )||
                        (firstArray[leftIndex].age ==8 && lastArray[rightIndex].age ==44 )) {
                        array[resultIndex] = lastArray[rightIndex];
                        rightIndex++;
                    count = count+(firstArray.length-leftIndex);
                    }
            resultIndex++;
            }
            while (leftIndex < firstArray.length) {
                array[resultIndex] = firstArray[leftIndex];
                leftIndex++;
                resultIndex++;
            }
            while (rightIndex < lastArray.length) {
                array[resultIndex] = lastArray[rightIndex];
                rightIndex++;
                resultIndex++;
            }
        }
        return count;
    }

}
