import java.util.ArrayList;

/**
 * Created by niyatishah on 4/25/16.
 */

import java.util.*;

public class NegNode {

    int ID;
    ArrayList<NegNode> connectedTo = new ArrayList<NegNode>();
    ArrayList<Integer>cost = new ArrayList<>();

    NegNode(){

    }

    NegNode(int ID){
        this.ID = ID;
    }


    public  void addConnection(NegNode node ,int cost){
        connectedTo.add(node);
        this.cost.add(cost);

    }

    public void printNodes(){
        System.out.print("{");
        for(int i=0;i<connectedTo.size();i++){
            System.out.print(connectedTo.get(i).ID);
        }
        System.out.println("}");
    }
}
