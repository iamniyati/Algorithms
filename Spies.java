import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Class to calculate the shortest path between the
 * given nodes
 *
 * @version   $Id$ 1.0 Spies.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */

public class Spies {
    static SpyNode [] graph;
    static SpyNode [] unrelaibleSpyList;
    static int spies;
    static int unreliableSpies;
    static int connections;
    static int minCost[];
    static int parent[];
    static int mincost =9999;
    static int mincostparent;
    static boolean[] seen;
    static int[] path;
    static int finalCost =0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get number of spies and connections
        String line = br.readLine();
        String [] lines =line.split(" ");
        spies =Integer.parseInt(lines[0]);
        connections = Integer.parseInt(lines[1]);
        // Get unreliable spies
        line = br.readLine();
        lines =line.split(" ");
        unreliableSpies =Integer.parseInt(lines[0]);
        line = br.readLine();
        lines =line.split(" ");
        graph = new SpyNode[spies];
        minCost = new int[spies];
        parent = new int[spies];
        seen = new boolean[spies];
        for(int i=0;i<spies;i++){
            graph[i] = new SpyNode(i);
            minCost[i] =99999;
            parent[i] =-1;
            seen[i]=false;

        }
        unrelaibleSpyList = new SpyNode[unreliableSpies];
        int j=0;
        for(int i=0;i<unreliableSpies;i++){
            graph[Integer.parseInt(lines[i])].addReliable(false);
            unrelaibleSpyList[j]=graph[Integer.parseInt(lines[i])];
            j++;
        }
        print();


        int nodea, nodeb, cost;
        for(int i=0;i<connections;i++){
            line = br.readLine();
            lines =line.split(" ");
            nodea = Integer.parseInt(lines[0]);
            nodeb = Integer.parseInt(lines[1]);
            cost = Integer.parseInt(lines[2]);
            makegraph(nodea,nodeb,cost);
        }

        print();
        createMinCostNetwork();
    }

    public static void makegraph(int nodea, int nodeb,int cost){
        System.out.println(nodea+" "+nodeb);
        System.out.println(graph[nodea].connectedTo.contains(graph[nodeb]));
        if(!graph[nodea].connectedTo.contains(graph[nodeb])) {
            graph[nodea].addConnection(graph[nodeb], cost);
            graph[nodeb].addConnection(graph[nodea], cost);
        }
    }


    public static void print(){
        for(int i=0;i<spies;i++){
            System.out.print(i+" =");
            graph[i].printNodes();
        }
    }

    public static int createMinCostNetwork(){
        int firstnode =getfirstNode();
        minCost[firstnode] =0;
        seen[firstnode]=true;
        int lastNode;
        SpyNode node =graph[firstnode], currNode = graph[firstnode], prevNode = graph[firstnode];
        int count =spies-unreliableSpies-1,cost;
        System.out.println("size "+minCost.length);
        System.out.println("node "+firstnode);
        boolean flag = false;
        boolean check;
        while(count!=0){
            check = false;
            System.out.println(currNode.ID);
            prevNode = currNode;
            for(int i=0;i<node.connectedTo.size();i++){
                currNode = node.connectedTo.get(i);
                if(seen[currNode.ID]==false) {
                    if (currNode.isReliable()) {
                        System.out.println(minCost[currNode.ID]);
                        System.out.print(node.cost.get(i));
                        if (minCost[currNode.ID] > node.cost.get(i)) {
                            minCost[currNode.ID] = node.cost.get(i);
                            check = true;
                            if (currNode.ID == 39) {
                                System.out.println("its 39");
                            }
                            System.out.println("relaible");
                            cost = node.cost.get(i);
                            flag = calculateMin(currNode, cost, node);
                            System.out.println("mincost , node " + mincost + " " + mincostparent);
                        }
                    }
                }else{
                    flag = false;
                }
            }
            if(check == false ){
                System.out.println("None");
                return -1;
            }
            node = graph[mincostparent];
            count--;
            seen[mincostparent] = true;
            if (flag) {
                finalCost += mincost;
            }

            System.out.println("mincost , node, finalcost "+mincost+" "+mincostparent+" "+finalCost);

        }System.out.println("*********");
        lastNode = mincostparent;
       for(int j=0;j<unreliableSpies;j++){
           node = unrelaibleSpyList[j];
           mincost =999;
           System.out.println(unrelaibleSpyList[j].ID);
           seen[unrelaibleSpyList[j].ID] = true;
            for(int i=0;i<node.connectedTo.size();i++){
                currNode = node.connectedTo.get(i);
                if(currNode.ID==firstnode || currNode.ID == lastNode) {
                    if (currNode.isReliable()) {
                        cost = node.cost.get(i);

                        if (cost < mincost) {
                            mincost = cost;
                        }
                        System.out.println("mincost , node " + mincost + " " + mincostparent);
                    }
                }
            }
               finalCost += mincost;
            System.out.println("mincost , node, finalcost "+mincost+" "+mincostparent+" "+finalCost);

        }

        System.out.println("finalcost "+finalCost);
        return 1;


    }
    public static boolean calculateMinUnreliable(SpyNode node, int cost, SpyNode parent){
        if (cost < mincost) {
            mincost = cost;
            mincostparent = node.ID;
            System.out.println(" min "+mincost);
            return true;
        }
        return false;
    }

    public static boolean calculateMin(SpyNode node, int cost, SpyNode parent) {
            if (cost < minCost[node.ID]) {
                minCost[node.ID] = cost;
                System.out.println("cost,min "+cost+" "+mincost);
                //parent[node.ID] =parent.ID;
                if (cost < mincost) {
                    mincost = cost;
                    mincostparent = node.ID;
                    System.out.println(" min "+mincost);
                }
                return true;
            }
            return false;
    }

    public static int getfirstNode(){
        for(int i=0;i<graph.length;i++){
            if(graph[i].isReliable()){
                return graph[i].ID;
            }
        }
        return -1;
    }
}
