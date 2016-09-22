/**
 * Node class for the graph database
 *
 * @version   $Id$ 1.0 GraphNode.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */
import java.util.*;
//import java.lang.*;

public class SpyNode {

    int ID;
    boolean reliable = true;
    ArrayList<SpyNode> connectedTo = new ArrayList<SpyNode>();
    ArrayList<Integer> cost = new ArrayList<>();


    SpyNode(){

    }

    SpyNode(int ID){
        this.ID = ID;
    }

    public  void addConnection(SpyNode node ,int cost){
        connectedTo.add(node);
        this.cost.add(cost);

    }



    public void addReliable(boolean reliable){
        this.reliable = reliable;
    }

    public boolean isReliable(){
        return this.reliable;
    }

    public void printNodes(){
        System.out.print("{");
        for(int i=0;i<connectedTo.size();i++){
            System.out.print(connectedTo.get(i).ID);
        }
        System.out.println("}");
    }
}
