package ppc.code;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem4 {

	public static void dijkstra(int[][] graph, int startNode, int endNode) {
		int nodeCount = graph.length;
		boolean[] visited = new boolean[nodeCount];
		int[] distance = new int[nodeCount];
		Map<Integer, Integer> parentMap = new HashMap<>();
		// initialize
		for (int i = 0; i < nodeCount; i++) {
			visited[i] = false;
			distance[i] = Integer.MAX_VALUE;
		}
		distance[startNode] = 0; // distance of source node to itself is zero
		for (int i = 0; i < nodeCount; i++) {
			// find the adjacent unvisited node having minimum distance from source node

			// for the first time u will be the source vertex and the distance array will be
			// updated with the neighbouring vertex distance of source vertex
			int u = getMinDistanceNode(visited, distance);
			// u -row and v - column
			visited[u] = true;
			// for each row, loop through cols and update the distance, if we can find a min
			// distance through the node we are processing(row val)
			for (int v = 0; v < nodeCount; v++) {
				// graph[u][v] != 0 -> there should be a direct edge
				if (!visited[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
					distance[v] = distance[u] + graph[u][v];
					parentMap.put(v, u);// child,parent
					// System.out.println("v="+v+" destNode = "+endNode);

				}
			}

		}
		for (int i = 0; i < distance.length; i++) {
			System.out
					.println(String.format("Distance from source node %s to node %s is %s", startNode, i, distance[i]));
		}
		for (Integer key : parentMap.keySet()) {
			System.out.println(String.format("Parent for %s is %s", key, parentMap.get(key)));
		}
		List<Integer> path = new ArrayList<>();
		int child = endNode;
		int node;
		while (child != startNode) {
			node = parentMap.get(child);
			path.add(node);
			child = node;
		}
		Collections.reverse(path);
		System.out.println("Path from " + startNode + " to " + endNode + " is:");
		for (int i : path) {
			System.out.print(i + ",");
		}
		System.out.println(endNode);
	}

	private static int getMinDistanceNode(boolean[] visited, int[] distance) {
		int minDist = Integer.MAX_VALUE;
		int minDistanceNode = -1;
		for (int i = 0; i < distance.length; i++) {
			// find non-visited node with min distance
			// this is similar to finding the min element of an array
			if (!visited[i] && distance[i] < minDist) {
				minDist = distance[i];
				minDistanceNode = i;
			}
		}
		return minDistanceNode;
	}

	public int[][] buidGraphMatrix(String filename) {
		// List<List<String>> records = new ArrayList<>();
		List<int[]> rows = new ArrayList<>();
		int[][] grid = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				int[] vals = new int[values.length];
				for (int i = 0; i < values.length; i++) {
					vals[i] = Integer.parseInt(values[i]);
				}
				rows.add(vals);
			}
			if(rows.size()>0) {
				grid = new int[rows.size()][rows.size()];
				for (int j = 0; j < rows.size(); j++) {
					grid[j] = rows.get(j);
				}
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						System.out.print(grid[i][j] + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			System.out.println("Error in file read" + e);
		}
		return grid;
	}

	public static void main(String[] args) {
		/*
		 * int graph[][] = new int[][] { 	{ 0, 3, 7, 0, 0 },
		 * 								 	{ 3, 0, 2, 5, 0 }, 
		 * 									{ 7, 2,0, 5, 10}, 
		 									{ 0, 5, 5, 0, 3 },
		  									{ 0, 0, 10, 3, 0 } };
		 */
		Problem4 t = new Problem4();
		int graph[][] = t.buidGraphMatrix("resources/grid.csv");
		t.dijkstra(graph, 2, 9);
	}
}