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

public class GraphNode {

     int ID;
    ArrayList<GraphNode> connectedTo = new ArrayList<GraphNode>();

    GraphNode(){

    }

    GraphNode(int ID){
        this.ID = ID;
    }

    public void addConnection(GraphNode node ){
        connectedTo.add(node);
    }

    public void printNodes(){
        System.out.print("{");
        for(int i=0;i<connectedTo.size();i++){
            System.out.print(connectedTo.get(i).ID);
        }
        System.out.println("}");
    }
}
