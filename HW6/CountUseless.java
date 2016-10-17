import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Class to find out useless vertices in the
 * graph. I.E. find the number nodes which are
 * not part of the shortest path.
 *
 * @version   $Id$ 1.0 CountUseless.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *             
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class CountUseless {
    public static int edges;
    public static int nodes;
    public static double[][] graph;
    public static double[][][] solution;
    public static double[][] path;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] lines = line.split(" ");
        nodes = Integer.parseInt(lines[0]);
        edges = Integer.parseInt(lines[1]);
        graph = new double[nodes+1][nodes+1];
        int nodea, nodeb;
        double cost;
        solution = new double[nodes+1][nodes+1][nodes+1];
        path = new double[nodes+1][nodes+1];

        // initialize the array

        for(int i=0;i<edges;i++){
            line = br.readLine();
            lines =line.split(" ");
            nodea = Integer.parseInt(lines[0]);
            nodeb = Integer.parseInt(lines[1]);
            cost = Double.parseDouble(lines[2]);
            graph[nodea+1][nodeb+1] =cost;

        }
        findPath(solution,graph);
        getUnused(solution,graph);

    }

    /**
     * @description : Function to finad all pairs shortest
     *                 path using floyd warshals
     *
     * @param : Double array solution : calculates the solution
     * @param : double graph array : contains initial graph
     * @return None
     *
     */
    public static void findPath(double[][][] solution, double[][] graph){


        // initialize solution array
        for(int i=0;i<solution.length;i++) {
            for (int j = 0; j < solution.length; j++) {
                if(graph[i][j] == 0){               // If no path then cost is infinity
                    solution[i][j][0]= Integer.MAX_VALUE;
                }else {                             // If path initialize cost
                    solution[i][j][0] = graph[i][j];
                }if(i==j){                          // If own loop then no path
                    solution[i][j][0] = 0;
                }
            }
        }
        // Perform floyd warshal
        for(int k=1;k<solution.length;k++) {
            for (int i = 1; i < solution.length; i++) {
                for (int j = 1; j < solution.length; j++) {
                    solution[i][j][k] = Math.min(solution[i][j][k - 1], solution[i][k][k - 1] + solution[k][j][k - 1]);

                }

            }

        }

    }

    /**
     * @description : Function to finad all the paths
     *                 not used  in floyd warshals
     *
     * @param : Double array solution : calculates the solution
     * @param : double graph array : contains initial graph
     * @return None
     *
     */
    public static void getUnused(double[][][] solution,double[][] graph){
        int unused =0;
        for (int i=1;i<solution.length;i++){
            for(int j=1;j<solution.length;j++){
                // check is path is updated by a shorter path and if initial graph had a node or not.
               if(  solution[i][j][solution.length-1]<graph[i][j]) {
                   if (graph[i][j] != 0) {
                       unused = unused + 1;
                   }
               }
            }

        }
        System.out.println(unused);
    }


}
