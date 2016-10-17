import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This class finds all the points which by
 * folding the paper along a single
 * line we can make some of the points align.
 *
 * @version   $Id$ 1.0 AlignPoints.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *              
 *
 * Revisions:
 *	$Log$
 *
 *
 */

public class AlignPoints {
    public static int totalNumber;
    public static int[] xArray;
    public static  int[] yArray;
    public static LineNode[] Numbers;

     public static void main(String[] args)throws IOException{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         Scanner scan = new Scanner(System.in);
         totalNumber = Integer.parseInt(br.readLine());

         Numbers = new LineNode[totalNumber*(totalNumber-1)/2];
        xArray = new int[totalNumber];
         yArray = new int[totalNumber];
         String line;
         String[] linesarray ;
         for (int index = 0; index < totalNumber; index++) {
                line = br.readLine();
                linesarray = line.split(" ");
                xArray[index] = Integer.parseInt(linesarray[0]);
                yArray[index] = Integer.parseInt(linesarray[1]);
         }

         findSlopeIntercept();
         int max;
         if(totalNumber == 2){
             max = 1;
         }else {
             MergeSort(Numbers);
             max = findMax();
         }
         System.out.print(max);
         }



    /**
     * @description : method to get the slope and mid point of line
     *
     * @param : None
     *
     * @return None
     */
    public static void findSlopeIntercept(){
        float x1, x2,y1,y2 ;

        float slope,intercept, ym,xm;
        int i=0;
        for (int index =0; index<totalNumber;index++){
            for(int indexj =index + 1; indexj<totalNumber;indexj++){
                x1 = xArray[index];
                y1 = yArray[index];
                x2 = xArray[indexj];
                y2 = yArray[indexj];
                if(x2-x1==0){
                    slope = 9999;
                    intercept = (y2+y1)/2;
                    Numbers[i] = new LineNode(slope,intercept);

                    i++;

                }else{
                    slope = (y2-y1)/(x2-x1);
                    ym = (y2+y1)/2;
                    xm = (x2+x1)/2;
                    if(slope == 0)
                    {
                        intercept = (x2+x1)/2;
                    } else{
                        intercept = (ym) - (xm * (-1 / slope));
                    }

                    Numbers[i] = new LineNode(slope,intercept);
                    i++;
                }
            }
        }


    }

    /**
     * @description : method to perform merge sort of given array
     *
     * @param : Array
     *
     * @return None
     */
    public static void MergeSort(LineNode[] array) {

        int first = 0;
        if (array.length > 1) {
            int mid = array.length / 2;
            LineNode firstArray[] = new LineNode[mid];
            LineNode lastArray[] = new LineNode[array.length - firstArray.length];
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
                    if (firstArray[leftIndex].slope < lastArray[rightIndex].slope) {
                        array[resultIndex] = firstArray[leftIndex];
                        leftIndex++;
                    } else if(firstArray[leftIndex].slope == lastArray[rightIndex].slope){
                            if(firstArray[leftIndex].intercept < lastArray[rightIndex].intercept){
                                array[resultIndex] = firstArray[leftIndex];
                                leftIndex++;
                            }else{

                                array[resultIndex] = lastArray[rightIndex];
                                rightIndex++;
                            }
                        }else{

                        array[resultIndex] = lastArray[rightIndex];
                        rightIndex++;

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
    }

    /**
     * @description : method to get maximum number
     *                  of points that align.
     *
     * @param : None
     *
     * @return Max count
     */
    public static int findMax(){

        int i=0;
       int max=0 ;
        int count=1;


        float Sl= Numbers[i].slope;
        float in = Numbers[i].intercept;
        i = 1;
        while(i<Numbers.length-1){

            if((Sl == Numbers[i].slope) && (in == Numbers[i].intercept)){

                    count++;
                    i++;

            }else{
                if(max<count){
                    max =count;
                }
                i++;
                count = 1;
                 Sl= Numbers[i].slope;
                 in = Numbers[i].intercept;
            }

        }


        return max;

    }





}
