import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Scanner;

/**
 * Class to calculate the shortest path between the
 * given nodes
 *
 * @version   $Id$ 1.0 CountShortestPaths.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *           
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class CountShortestPaths {

    static GraphNode [] graph;
    static int nodes;
    static int edges;
    static int startNode;
    static int endNode;
    static boolean[] seen;
    static int[] distance;
    static int count =0;
    static int mindist =999;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get number of vertices and edges
        String line = br.readLine();
        String [] lines =line.split(" ");
        nodes =Integer.parseInt(lines[0]);
        edges = Integer.parseInt(lines[1]);
        // Get start and end points
        line = br.readLine();
        lines =line.split(" ");
        startNode =Integer.parseInt(lines[0]);
        endNode = Integer.parseInt(lines[1]);
        graph = new GraphNode[nodes];
        distance = new int[nodes];
        //initialize array with graph nodes
        for(int i=0;i<nodes;i++){
            graph[i] = new GraphNode(i);
        }
        int nodea, nodeb;
        // Create the graph for the given nodes
        for(int i=0;i<edges;i++){
            line = br.readLine();
            lines =line.split(" ");
            nodea = Integer.parseInt(lines[0]);
            nodeb = Integer.parseInt(lines[1]);
            makegraph(nodea,nodeb);
        }
        findPath(startNode,endNode);

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
        graph[nodeb].addConnection(graph[nodea]);
    }


    /**
     * @description : Function to find the shortest path
     *                  between the given start and the end node
     *
     * @param :int startNode : Id of the start node
     * @param : int endNode : Id of the end node
     * @return None
     *
     */
    public static void findPath(int startNode, int endNode){
        if(startNode == endNode){ // if start and end node are same then no path is found
            System.out.print("No path found");
        }else if(startNode<0 || startNode > nodes || endNode <0|| endNode>nodes){
            System.out.print("No path foundl");
        }
        else{

            GraphNode start = graph[startNode];
            GraphNode current;
            seen = new boolean[graph.length];
            Arrays.fill(seen, false);
            ArrayList<GraphNode> queue=start.connectedTo;
            seen[start.ID]=true;
            int size = queue.size();
            int incr =1;
            // perform BFS on the nodes
            while( queue.size()!=0){
                if(size ==0){
                    size = queue.size();
                    incr+=1;
                }
                current = queue.get(0);
                if(current.ID == endNode){ //  if endNode is found
                    checkPath(incr);
                }else{
                    // check if node is not unseen OR
                    // check if node is seen but the distance to it greater than current distance
                    if(seen[current.ID]==false || distance[current.ID]<= incr){
                        // if node is connected to any other nodes
                        // add them to search list
                        if(current.connectedTo.size()>0){
                            queue.addAll(current.connectedTo);

                          }
                        // update node details
                            distance[current.ID] +=incr;
                            seen[current.ID]=true;
                        }
                }
                // remove node that has been searched
                queue.remove(0);
                size--;
            }

        }

        System.out.println(count);
    }

    /**
     * @description : Function to check is current path
     *                  is shortest and to keep track of the
     *                  count of shortest paths
     *
     * @param :int startNode : Id of the start node
     * @param : int endNode : Id of the end node
     * @return None
     *
     */
    public static void checkPath( int tempDist){
        if(mindist == tempDist){ // path with same minimum distance found
            count +=1;
        }else if(mindist > tempDist){ // path with shorter distance found
            mindist = tempDist;
            count = 1;
        }
    }

}
