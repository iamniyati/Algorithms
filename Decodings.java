
import java.util.Scanner;
/**
 *
 *
 * @version   $Id$ 1.0 Decodings.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class Decodings {



    public static void main(String[] args) {
        Decodings d = new Decodings();
        int[] array;
        Scanner scan = new Scanner(System.in);
        String line = scan.next();
        array = new int[line.length() + 1];
        array[0] = 1;
        d.decode(line, array);
    }

    /**
     * @description : This method searches for all the different possible
     * combinations of the given code
     * @param : String line: contains the given code
     * @param : int array: dynamic array, will contain the sum at the last
     *              array bit
     * @return None
     *
     */
    public  void decode(String line, int[] array) {
        String temp = "";
        int j = 1;
        int sum1 = 0;
        int sum2 = 0;
        int sum3 =0;
        for (int index = 0; index < line.length(); index++) {
            temp += "" + line.charAt(index);
            if (temp.length() == 1) {
                array[j] = array[j - 1];
                j++;
            } else if (temp.length() == 2) {

                if (temp.equals("10") || temp.equals("01")) {
                    sum1 = array[j-1];
                    sum2 = array[j-2];
                    array[j]= sum1+sum2;
                    j++;
                } else {
                    sum1 = array[j-1];
                    sum2 = 0;
                    array[j]=sum1+sum2;
                    j++;
                }
            } else if (temp.length() == 3) {
               if(temp.substring(2).equals("0") || temp.substring(2).equals("1")) {
                   sum1 = array[j - 1];
               }
               if(temp.substring(1).equals("01") || temp.substring(1).equals("10")){
                   sum2 = array[j-2];
               }else{
                   sum2 =0;

               }
               if(temp.equals("111") || temp.equals("011")){
                   sum3 = array[j-3];
               }else{
                   sum3 =0;
               }
                array[j]=sum1+sum2+sum3;
                j++;
                }
                if (temp.length() == 3) {
                    temp = temp.substring(1);
                }
            }

        System.out.println(array[j-1]);


        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }

        }
    }


