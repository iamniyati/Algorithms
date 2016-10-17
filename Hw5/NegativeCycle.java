
/*
 * Program to find a negative weight cycle 
 * 
 * @version		$Id$ 1.0 NegativeCycle.java
 * 
 * @author   Niyati Shah(nxs6032) Section3
 *             
 * 
 * Revisions:
 *	$Log$
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class NegativeCycle {
	public static int edges;
	public static int nodes;
	public static ArrayList dist = new ArrayList<>();
	public static ArrayList parent = new ArrayList<>();
	public static int[][] a;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] lines = line.split(" ");
		nodes = Integer.parseInt(lines[0]);
		edges = Integer.parseInt(lines[1]);
		a = new int[nodes][nodes];
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				a[i][j] = 99999;
			}
		}

		int m = 0, n = 0, wt = 0;
		int nodea, nodeb, cost;
		for (int i = 0; i < edges; i++) {
			line = br.readLine();
			lines = line.split(" ");
			nodea = Integer.parseInt(lines[0]);
			nodeb = Integer.parseInt(lines[1]);
			cost = Integer.parseInt(lines[2]);
			a[nodea][nodeb] = cost;
			if (cost < 0) { // if the edge is found to be negative store its
							// coordinates
				m = nodea;
				n = nodeb;
				wt = cost;
			}

		}
		Dijkstra(a, n); // calling the dijkstra's function

		if (checkNegWt(n, m, nodes, wt)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	public static void Dijkstra(int a[][], int source) {
		/*
		 * Function to implement Dijkstra's shortest shortest source path
		 * algorithm
		 * 
		 * @param: int a[][]: 2D adjacency matrix
		 * 
		 * @param: int source: source node
		 * 
		 */
		ArrayList vertexSet = new ArrayList<>();
		for (int i = 0; i < nodes; i++) {
			vertexSet.add(i); // adding all the vertices
		}
		vertexSet.remove(source); // removing the source node
		for (int i = 0; i < nodes; i++) {
			dist.add(99999); // updating the distance and parent node
			parent.add(99999);
		}
		dist.set(source, 0); // set the distance to sourcec as zero

		update(source, vertexSet, a, dist, parent); // calling update

		for (int i = 0; i < nodes - 1; i++) {
			int n = findMin(vertexSet, dist); // finding minimum from vertexSet
			if (vertexSet.indexOf(n) >= 0) {
				vertexSet.remove(vertexSet.indexOf(n)); // removing it
				update(n, vertexSet, a, dist, parent); // calling update
			}
		}

	}

	public static void update(int x, ArrayList vertexSet, int a[][], ArrayList dist, ArrayList parent) {
		/*
		 * Function to update all the nodes from current node, while marking it
		 * as visited
		 * 
		 * @param : x : The current node
		 * 
		 * @param : vertexSet : List of non visited nodes
		 * 
		 * @param : a[][] : 2D adjacency matrix
		 * 
		 * @param : dist : All the totalDistance list
		 * 
		 * @param : parent : All the parent list
		 */
		ArrayList temp = new ArrayList<>(); // neighbors of x node
		for (int i = 0; i < a.length; i++) {
			if (a[x][i] != 99999) {
				temp.add(i);// adding neighbors of x node to temp(adjacency
							// list)
			}
		}
		for (int j = 0; j < temp.size(); j++) { // going through all of x's
												// neighbors
			int v = (int) temp.get(j);
			if (vertexSet.contains(v)) {
				int element = (int) dist.get(v); // current distance of neighbor
				int weight = a[x][v];
				if (element > ((int) (dist.get(x)) + weight)) { // if current
																// distance is
																// larger
					int newwt = weight + (int) dist.get(x);
					dist.set(v, newwt);
					parent.set(v, x);

				}
			}
		}

	}

	public static int findMin(ArrayList nodes, ArrayList dist) {
		/*
		 * Fucntion to find the minimum distance from distance list of not
		 * visited nodes
		 * 
		 * @param : nodes : List of not visited nodes
		 * 
		 * @param : dist : List of distance
		 */

		ArrayList temp = new ArrayList<>(); // store corresponding wts(distance)
											// of vertex list
		for (int i = 0; i < nodes.size(); i++) {
			int ele = (int) nodes.get(i);
			temp.add(dist.get(ele));
		}
		if (temp.size() == 1) {
			return dist.indexOf(temp.get(0)); // only one element
		} else {
			int min = (int) temp.get(0); // distance list
			int index = 0;

			for (int i = 1; i < temp.size(); i++) {
				if (min > (int) temp.get(i)) {
					min = (int) temp.get(i);
					index = i;
				}
			}

			int node = dist.indexOf(min);
			return node;
		}
	}

	public static boolean checkNegWt(int start, int end, int nodes, int weight) {
		/*
		 * Function to find whether the graph contains a negative weight cycle
		 * or not
		 * 
		 * @param : start : start node of negative cycle
		 * 
		 * @param : end : end node to negative cycle
		 * 
		 * @param : nodes : The number of nodes
		 * 
		 * @param : weight : Weight of the negative edge
		 * 
		 */
		int theWt = (int) dist.get(end);
		for (int i = 0; i < nodes - 1; i++) {
			int x = (int) parent.get(end);
			if (x == 99999) {
				return false; // if no cycle found
			} else {
				if (x == start) {
					if ((weight + theWt) < 0) {
						return true;
					}
				}
				end = x;

			}
		}
		return false;
	}

}
