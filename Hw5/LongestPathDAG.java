import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Class to calculate the longest path between the
 * the given nodes in the graph
 *
 * @version   $Id$ 1.0 LongestPathDAG.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *              
 *
 * Revisions:
 *	$Log$
 *
 *
 */

public class LongestPathDAG {
    static Node [] graph;
    static int nodes;
    static int edges;
    static int finishOrder[];
    static boolean[] seen;
    static int[] distance;
    static int count =0;
    static int mindist =999;
    static Stack<Node> ordered = new Stack();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get number of vertices and edges
        String line = br.readLine();
        String[] lines = line.split(" ");
        nodes = Integer.parseInt(lines[0]);
        edges = Integer.parseInt(lines[1]);
        graph = new Node[nodes];
        distance = new int[nodes];
        seen = new boolean[nodes];
        finishOrder = new int[nodes];
        //initialize array with graph nodes
        for (int i = 0; i < nodes; i++) {
            graph[i] = new Node(i);
            seen[i] = false;
            finishOrder[i]=999;
        }
        int nodea, nodeb;
        // Create the graph for the given nodes
        for (int i = 0; i < edges; i++) {
            line = br.readLine();
            lines = line.split(" ");
            nodea = Integer.parseInt(lines[0]);
            nodeb = Integer.parseInt(lines[1]);
            makegraph(nodea, nodeb);
        }
        //get topological sorted nodes
        startTopologocalSort();
       // get longest path
        getLongestPath(ordered.pop());

    }

    /**
     * @description : Base Function to start topological
     *                  sorting of nodes
     *
     * @return None
     *
     */
    public static void startTopologocalSort(){
        for(int i=0;i<graph.length;i++){
            if(seen[i]== false){
                getTopologicalOrder(graph[i], ordered, seen);
            }
        }
    }

    /**
     * @description : Function to create topologically sorted
     *                  nodes from given set of nodes
     *
     * @param : Node node : Node for which neighbours are being selected
     * @param : Stack ordered : stores nodes sorted in topological order
     * @param : Boolean[] seen : array to keep check of visited nodes
     * @return None
     *
     */
    public static void getTopologicalOrder(Node node,Stack<Node> ordered,boolean[]seen){
        seen[node.ID] = true;
        Node curr;
        Iterator<Node> iter = graph[node.ID].connectedTo.iterator();
        while (iter.hasNext()){
            curr=iter.next();
            if(!seen[curr.ID]){
                getTopologicalOrder(curr, ordered, seen);
            }
        }
        ordered.push(node);

    }

    /**
     * @description : Function to find the longest path
     *                  from a given node
     *
     * @param :Node node : a node to start the search of longest path
     *
     * @return None
     *
     */
    public static void getLongestPath(Node start){
        Arrays.fill(seen, false);
        Arrays.fill(distance, 0);
        distance[start.ID] =0;
        int incr =1;
        while (!ordered.empty()){
                for(int i=0;i<start.connectedTo.size();i++){
                    if(distance[start.connectedTo.get(i).ID] < distance[start.ID]+incr){
                        distance[start.connectedTo.get(i).ID] = distance[start.ID]+incr;
                    }
                }
            start=ordered.pop();
        }
       int max = distance[0];
        for(int i=1;i<distance.length;i++){
            if(max <distance[i]){
                max=distance[i];
            }
        }
        System.out.println(max);
    }


    /**
     * @description : Function to create the edge between
     *                  the given two nodes a and b
     *
     * @param :GraphNode nodea : Node a
     * @param : GraphNode nodeb : Node b
     * @return None
     *
     */
    public static void makegraph(int nodea, int nodeb){
        graph[nodea].addConnection(graph[nodeb]);
    }
}

