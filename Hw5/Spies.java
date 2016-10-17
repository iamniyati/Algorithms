

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.jar.Pack200;

/**
 * Class to calculate the shortest path between the
 * given spies making sure that the path does not pass
 * through an unreliable spy
 *
 * @version   $Id$ 1.0 Spies.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *              & Rudresh Pandit(rmp7494) Section1
 *
 * Revisions:
 *	$Log$
 *
 *
 */

public class Spies {
    static SpyNode[] graph;
    static ArrayList<SpyNode> unrelaibleSpyList;
    static ArrayList<SpyNode> list;
    static int spies;
    static int unreliableSpies;
    static int connections;
    static int minCost[];
    static int parent[];
    static boolean[] seen;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get number of spies and connections
        String line = br.readLine();
        String[] lines = line.split(" ");
        spies = Integer.parseInt(lines[0]);
        connections = Integer.parseInt(lines[1]);
        // Get unreliable spies
        line = br.readLine();
        lines = line.split(" ");
        unreliableSpies = Integer.parseInt(lines[0]);
        line = br.readLine();
        lines = line.split(" ");
        graph = new SpyNode[spies];
        minCost = new int[spies];
        parent = new int[spies];
        seen = new boolean[spies];

        for (int i = 0; i < spies; i++) {
            minCost[i] = Integer.MAX_VALUE;
            parent[i] = -1111;
            seen[i] = false;
            graph[i] = new SpyNode(i);
        }
        //get unreliable nodes
        unrelaibleSpyList = new ArrayList<>();

        for (int i = 0; i < unreliableSpies; i++) {
            graph[Integer.parseInt(lines[i])].addReliable(false);
            unrelaibleSpyList.add(graph[Integer.parseInt(lines[i])]);
        }

        list = new ArrayList<>();
        // get other nodes
        int nodea, nodeb, cost;
        for (int i = 0; i < connections; i++) {
            line = br.readLine();
            lines = line.split(" ");
            nodea = Integer.parseInt(lines[0]);
            nodeb = Integer.parseInt(lines[1]);
            cost = Integer.parseInt(lines[2]);
                graph[nodea].addConnection(graph[nodeb], cost);
                graph[nodeb].addConnection(graph[nodea], cost);

        }

        startPrims();


    }

    /**
     * @description : Base Function to start prims
     *                  Algorithm on nodes
     *
     * @return None
     *
     */
    public static void startPrims() {
        Arrays.fill(seen, false);
        // get first node & update node
        boolean flag = false;
        int finalCost =0;
        int first = getFirstRelaible(spies, unrelaibleSpyList);
        minCost[first] = 0;
        parent[first] = first;
        int u = first;

        for (int i = 0; i < spies-unreliableSpies; i++) {

            seen[u]=true;
            update(u,graph,parent, minCost,seen);
            u =getMinNode(minCost,graph,seen, u);
        }

        for( int i=0;i<spies;i++){
            if(minCost[i]!=Integer.MAX_VALUE){
                finalCost +=minCost[i];

            }else{
                flag = true;
            }
        }

        if(flag){
            System.out.println("NONE");
        }else{
            System.out.println(finalCost);
        }

    }

    /**
     * @description : Function to update the minimum path
     *                  of neighbouring nodes
     *
     *  @param : int u : Parent node
     *  @param :SpyNode[] graph : Graph of spynodes
     *  @param : int [] parent : maintains parent of each node
     *  @param : int [] minCost : maintains minimum cost of each node
     *  @param : boolean[] seen : maintains nodes that are visited
     *
     * @return None
     *
     */
    public static void update(int u, SpyNode[] graph, int[] parent, int[] minCost, boolean[] seen){

        SpyNode node = graph[u], curr;
        for(int i=0;i<node.connectedTo.size();i++){
            curr = node.connectedTo.get(i);

            if(minCost[curr.ID]> node.cost.get(i) && !seen[curr.ID]){
                minCost[curr.ID]= node.cost.get(i);
                parent[curr.ID] = u;

            }
        }

    }

    /**
     * @description : Function to get the minimum path node
     *                  from the mincost node array
     *
     *  @param : int [] minCost : maintains minimum cost of each node
     *  @param : boolean[] seen : maintains nodes that are visited
     *  @param :SpyNode[] graph : Graph of spynodes
     *  @param : int [] parent : maintains parent of each node
     *  @param : int lastnode : last node used by function
     *
     * @return minimum valued node if any remaining or return the last node
     *
     */
    public static int getMinNode(int[] minCost,SpyNode[] graph, boolean[] seen, int lastnode) {
        int min = Integer.MAX_VALUE;
        int minNode =lastnode;

        for (int j = 0; j < spies; j++) {
            if (min > minCost[j] && graph[j].isReliable() && !seen[j]) {
                min = minCost[j];
                minNode = j;
            }
        }

        return minNode;
    }



    /**
     * @description : Function to get the first reliable node
     *                  from aray of nodes
     *
     *  @param : int spies : total number of spies
     *  @param : ArrayList<SpyNode> unrelaibleSpyList : list of unreliable nodes
     *
     *
     * @return first reliable node
     *
     */
    public static int getFirstRelaible(int spies, ArrayList<SpyNode> unrelaibleSpyList) {
        for (int i = 0; i < spies; i++) {
            if (!unrelaibleSpyList.contains(graph[i])) {
                return i;
            }
        }
        return -1;
    }

}




