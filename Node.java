import java.util.*;
/**
 * Node class for the graph database
 *
 * @version   $Id$ 1.0 Node.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class Node {
    int ID;
    LinkedList<Node> connectedTo = new LinkedList<>();
    LinkedList<Double> weight = new LinkedList<>();

    public void addConnection(Node node, double weight) {
        connectedTo.add(node);
        this.weight.add(weight);
    }

    public Node(){
    }

    public Node(int id){
        this.ID = id;
    }

}