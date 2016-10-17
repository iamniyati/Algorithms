import java.util.*;
/**
 * Node class for the graph database
 *
 * @version   $Id$ 1.0 Node.java
 *
 * @author   Niyati Shah(nxs6032) Section3
 *              & Rudresh Pandit(rmp7494) Section1
 *
 * Revisions:
 *	$Log$
 *
 *
 */
public class Node {
    int ID;
    LinkedList<Node> connectedTo = new LinkedList<>();

    public void addConnection(Node node) {
        connectedTo.add(node);
    }

    public Node(){
    }

    public Node(int id){
        this.ID = id;
    }

}
