import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class NegativeCycle {
    public static int edges;
    public static int nodes;
	public static ArrayList dist = new ArrayList<>();
	public static ArrayList parent = new ArrayList<>();
    public static int[][]a;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String [] lines =line.split(" ");
        nodes =Integer.parseInt(lines[0]);
        edges = Integer.parseInt(lines[1]);
        a = new int[nodes][nodes];
        for(int i=0;i<nodes;i++){
            for(int j=0;j<nodes;j++){
                a[i][j] =99999;
            }
        }
        int nodea, nodeb, cost;
        for(int i=0;i<edges;i++){
            line = br.readLine();
            lines =line.split(" ");
            nodea = Integer.parseInt(lines[0]);
            nodeb = Integer.parseInt(lines[1]);
            cost = Integer.parseInt(lines[2]);
            a[nodea][nodeb] = cost;

        }
	/*	int a[][] = { { 99999, 3, 99999, 99999 }, { 99999, 99999, 5, 99999 }, { 99999, 99999, 99999, -10 },
				{ 1, 99999, 20, 99999 } };
*/
		System.out.println("Graph is: ");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		Dijkstra(a, 3);

		for (int i = 0; i < dist.size(); i++) {
			System.out.println(i+" " + "," + parent.get(i) +" "+ dist.get(i));
		}
	}

	public static void Dijkstra(int a[][], int source) {
		ArrayList vertexSet = new ArrayList<>();
		for (int i = 0; i < nodes; i++) {
			vertexSet.add(i);
		}
		vertexSet.remove(source);
		for (int i = 0; i < nodes; i++) {
			dist.add(99999);
			parent.add(99999);
		}
		dist.set(source, 0);
		update(source, vertexSet, a, dist, parent);
		for (int i = 0; i < nodes-1; i++) {
			int n = findMin(vertexSet, dist);
            System.out.println("vertex to be removed "+n+"at position"+vertexSet.indexOf(n));
			vertexSet.remove(vertexSet.indexOf(n));
			update(n, vertexSet, a, dist, parent);
		}

	}

	public static void update(int x, ArrayList vertexSet, int a[][], ArrayList dist, ArrayList parent) {
    System.out.println("***UPDATE"+x+"*****");
		ArrayList temp = new ArrayList<>(); // neighbors of x node
		for (int i = 0; i < vertexSet.size(); i++) {
			if (a[x][i] != 99999) {
				temp.add(i);            // adding neighbors of x node to temp(adjacency list)
			}
		}
        //System.out.println("x"+x);
		for (int j = 0; j < temp.size(); j++) {
			int v = (int) temp.get(j);
            System.out.println("V"+v);
			if (vertexSet.contains(v)) {
				int element = (int) dist.get(v);
				int weight = a[x][v];
                System.out.println("weight + ele"+weight + "," + element);
				if (element > ((int) (dist.get(x)) + weight)) {
					int newwt = weight + (int) dist.get(x);
                    System.out.println(newwt+" , "+x );
					dist.set(v, newwt);
					parent.set(v, x);

				}
			}
		}
        System.out.println("^^^^^^");
	}

	public static int findMin(ArrayList nodes, ArrayList dist) {

		ArrayList temp = new ArrayList<>(); // store corresponding wts(distance)
											// of vertex list
		for (int i = 0; i < nodes.size(); i++) {
			int ele = (int) nodes.get(i);
            System.out.println("nodes "+ele+" temp "+dist.get(ele));
			temp.add(dist.get(ele));
		}
        if(temp.size()==1){
            System.out.println("Length one returning the damn");
            return dist.indexOf(temp.get(0));
        }
        else{
        System.out.println("findmin");
		int min = (int) temp.get(0); // distance list
		int index = 0;

		for (int i = 1; i < temp.size(); i++) {
            //System.out.println("min "+min+" "+"tempmin "+temp.get(i) );
			if (min > (int) temp.get(i)) {
				min = (int) temp.get(i);
				index = i;
				//break;
			}
		}

		int node = dist.indexOf(min);
        System.out.println("min is"+ min + "last"+ node);

		return node;
	}
    }
}