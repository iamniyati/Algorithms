
/*
 * Program to find the maximum sum of registered students over all courses
 * 
 * @version		$Id$ 1.0 Register.java
 * 
 * @author   Niyati Shah(nxs6032) Section3
 *              & Rudresh Pandit(rmp7494) Section1
 * 
 * Revision : $Log$
 */
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Register {
	public static int visited[]; // array to keep a track of visited nodes for
									// bfs
	public static int parent[]; // array to keep a track of parent of particular
								// node

	public static void setParams(int x) {
		/*
		 * Function to set the visited and parent
		 */

		visited = new int[x];
		parent = new int[x];
		for (int i = 0; i < x; i++) {
			visited[i] = 0;
			parent[i] = -1;
		}
	}

	public static boolean bfs(int graph[][], int source, int sink) {
		/*
		 * Function to find a path using breadth first search, uses a linked
		 * list as a queue
		 * 
		 * @param graph[][] : graph as input
		 * 
		 * @param source : source node
		 * 
		 * @param sink : sink node
		 */

		setParams(graph[0].length); // setting the visited and graph arrays for
									// each new bfs call
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(source);

		visited[source] = 1; // marking the source as visited
		while (!queue.isEmpty()) {
			int node = queue.remove();
			for (int j = 1; j < graph[0].length; j++) {
				// if edge exists and is not visited
				if ((graph[node][j] > 0) && (visited[j] == 0)) {
					queue.add(j); // add edge to iterate
					parent[j] = node; // store the parent
					visited[j] = 1; // mark as visited
					if (j == sink) {
						return true; // if we find the sink we break
					}
				}
			}
		}
		return false;

	}

	public static int fordf(int graph[][], int source, int sink) {
		/*
		 * Function to find the maximum flow in a graph using Ford Fulkerson
		 * Algorithm
		 * 
		 * @param graph : input graph
		 * 
		 * @param source : source node
		 * 
		 * @param sink : source sink
		 */
		int max = 0;
		int y, min = 0;
		ArrayList<Integer> path = new ArrayList<Integer>();
		while (bfs(graph, source, sink)) {
			// find the minimum path cost
			y = sink;
			// finding the minimum edge in the path found by bfs
			while (y != source) {
				path.add(y);
				y = parent[y];
			}

			min = findMin(path);

			// updating the edge flow and backward edge by traversing
			// through the edge ( sink to source )
			y = sink;
			while (y != source) {
				int x = parent[y];
				graph[x][y] = graph[x][y] - min; // removing from the flow
				graph[y][x] = graph[y][x] + min; // adding to the backward edge
				y = parent[y];

			}
			max = max + min; // found one path of flow cost - min
		}

		return max;
	}

	public static int findMin(ArrayList<Integer> path) {
		/*
		 * Function to find the minimum in the returned path
		 */

		int min = path.get(0);
		for (int i = 1; i < path.size(); i++) {
			if (path.get(i) < min) {
				min = path.get(i);
			}
		}
		return min;

	}

	public static void main(String args[]) throws IOException {

		int graph[][], students = 0, courses = 0;
		int totalnodes = 0;
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine();
		String x[] = line.split(" ");
		students = Integer.parseInt(x[0]);
		courses = Integer.parseInt(x[1]);
		totalnodes = students + courses + 2;
		graph = new int[totalnodes][totalnodes];

		// System.out.println("students+ " + students + "courses: " + courses);
		for (int i = 1; i <= students; i++) {
			graph[0][i] = 3; // setting the common sink as 3( max courses
								// student
								// can take)
		}
		for (int i = 1; i <= students; i++) {
			line = br.readLine();
			x = line.split(" ");
			for (int j = 0; j < x.length; j++) {
				int m = Integer.parseInt(x[j]);
				graph[i][students + m] = 1; // setting the link between students
											// and courses as 1
			}
		}
		for (int i = 1; i <= courses; i++) {
			line = br.readLine();
			int n = Integer.parseInt(line);
			graph[i + students][totalnodes - 1] = n; // max students in a course
		}

		/*
		 * for (int i = 0; i < totalnodes; i++) { for (int j = 0; j <
		 * totalnodes; j++) { System.out.print(" " + graph[i][j]); }
		 * System.out.println(); }
		 */
		int max = fordf(graph, 0, totalnodes - 1);
		System.out.println(max);

	}
}
