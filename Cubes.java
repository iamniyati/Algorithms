
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

    public static void main(String args[]){
        ArrayList cubeList = new ArrayList();
        Cubes cube = new Cubes();
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        cube.findCubes(number, cubeList);
        cube.printCubes(cubeList);

    }


    /**
     * @description : mmthod to find all cubes less
     *                 than or equal to the given number
     *
     * @param : Given Number
     *
     * @return null
     */
    public  void findCubes(int number, ArrayList cubeList){
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
    public  void printCubes(ArrayList cubeList){
        for (int index = 0; index<cubeList.size();index++){
            System.out.println(cubeList.get(index));
        }
    }

}
