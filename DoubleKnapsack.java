import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program to calculate the maximum cost of items
 * that can be added to 2 knapsacks of weight less
 * than the individual knapsacks
 *
 *
 * @version   $Id$ 1.0 DoubleKnapsack.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class DoubleKnapsack {


    public static void main(String[] args)throws IOException {
        int totalNumber;
        int Knapsack1;
        int Knapsack2;
        Node [] itemsArray;
        int [][][] knapsackArray;
        DoubleKnapsack dk = new DoubleKnapsack();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int weight, cost;
        String line;
        String[] linesarray ;
        line = br.readLine();
        linesarray = line.split(" ");
        totalNumber = Integer.parseInt(linesarray[0]);
        Knapsack1 = Integer.parseInt(linesarray[1]);
        Knapsack2 = Integer.parseInt(linesarray[2]);
        itemsArray = new Node[totalNumber];
        for (int index = 0; index < totalNumber; index++) {
            line = br.readLine();
            linesarray = line.split(" ");
            weight = Integer.parseInt(linesarray[0]);
            cost = Integer.parseInt(linesarray[1]);
            itemsArray[index] = new Node(weight,cost);

        }
        knapsackArray = new int[totalNumber+1][Knapsack1+1][Knapsack2+1];
        dk.CalculateKnapSack(itemsArray, knapsackArray, totalNumber, Knapsack1,  Knapsack2);
    }

    /**
     * @description : This method calculates the maximum cost of things that can be
     *                  added to both the knapsack
     * @param :int 1D Array: itemsArray: contains the weight and cost of item
     * @param : int 3D array: dynamic array, will contain maximum cost of things that can
     *                 added to both the knapsack
     * @param : int Number: maximum number of items
     * @param : int Knapsack1 : maximum weight of knapsack 1
     * @param : int Knapsack2 : maximum weight of knapsack 2
     * @return None
     *
     */
    public  void CalculateKnapSack(Node[] itemsArray,int[][][] knapsackArray,int Number,int Knapsack1, int Knapsack2){
        int weight, cost,final_cost;
        for(int indexi =1; indexi<=Number;indexi++){
            for(int indexj =0; indexj<=Knapsack1; indexj++){
        for(int indexk = 0;indexk<=Knapsack2;indexk++){

                        int cost1=0,cost2=0;
                        weight = itemsArray[indexi-1].weight;
                        cost = itemsArray[indexi-1].cost;
                        if(indexj>= weight){
                            cost1 = cost+knapsackArray[indexi-1][indexj-weight][indexk];
                        }
                        if(indexk>= weight){
                            cost2 = cost+knapsackArray[indexi-1][indexj][indexk-weight];
                        }
                        final_cost = Math.max(cost1,cost2);
                        knapsackArray[indexi][indexj][indexk] = Math.max(final_cost, knapsackArray[indexi-1][indexj][indexk]);
                }

            }

        }
       System.out.println(knapsackArray[Number][Knapsack1][Knapsack2]);

    }
}
