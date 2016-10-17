
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class prints out perfect cubes less than or equal
 *      to the given value
 *
 * @version   $Id$ 1.0 Cubes.java
 *
 * @author   Niyati Shah
 *
 * Revisions:
 *	$Log$
 */
public class Cubes {
    public static ArrayList cubeList = new ArrayList();
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        findCubes(number);
        printCubes();

    }


    /**
     * @description : mmthod to find all cubes less
     *                 than or equal to the given number
     *
     * @param : Given Number
     *
     * @return null
     */
    public static void findCubes(int number){
        int temp;
        int ArrayIndex=0;
        for( int index = 0; index <= Math.cbrt(number);index++){
            temp = index*index*index;
            cubeList.add(ArrayIndex,temp);
            ArrayIndex++;
        }
    }

    /**
     * @description : mmethod to print all the cubes
     *
     *
     * @return null
     */
    public static void printCubes(){
        for (int index = 0; index<cubeList.size();index++){
            System.out.println(cubeList.get(index));
        }
    }

}
