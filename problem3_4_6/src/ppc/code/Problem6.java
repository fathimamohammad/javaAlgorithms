package ppc.code;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Problem6 {
	
	/**
	 * @param args
	 */
	private static Node start,end = null;
	private static int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1, 0}};//right,down,left,up
	private static Map<Node,Node> parentMap = new HashMap<Node,Node>();
	
	public static List<Node> shortestPath(char[][] grid){
		int rows = grid.length;
		int cols = grid[0].length;
		List<Node> path = new ArrayList<>();
		for (int i=0;i<rows;i++) {
			for (int j=0;j<cols;j++) {
				//System.out.println("i="+i+"j="+j+"rows="+rows+" coles="+cols);
				if (grid[i][j]=='S') {
					start = new Node(i,j);
					doPathSearch(grid,start);
					break;
				}
			}
		}
		if (start!=null && end !=null) {
			path.add(end);
			Node parent = parentMap.get(end);
			while (parent!=start) {
				path.add(parent);
				parent= parentMap.get(parent);
			}
			path.add(parent);
			Collections.reverse(path);
		}else {
            System.out.println("No path");
        }

        return path;
		
		
	}
	
	public static void doPathSearch(char[][] grid, Node start) {
		Queue<Node> queue = new LinkedList<>();// breadth first search , so using queue
		int rows = grid.length;
		int cols = grid[0].length;
		boolean[][] visited = new boolean[rows][cols];
		queue.offer(start);
		visited[start.getX()][start.getY()]=true;
		Node currentNode;
		Node child;
		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			visited[currentNode.x][currentNode.y] = true;
		for (int[] dir : directions) {
			int m = dir[0];
			int n = dir[1];
			int newX = currentNode.x + m;
			int newY = currentNode.y + n;
			//Node currentNode = new Node(start.x + m, start.y + n);
			if (newX < 1 || newX >= grid[0].length || newY < 1
					|| newY >= grid.length || visited[newX][newY]|| grid[newX][newY] == 'X') {
				continue;
			} 
			child = new Node(newX,newY);
			parentMap.put( child,currentNode);
			
				if (grid[child.x][child.y] != '0') {

						visited[child.x][child.y] = true;
						queue.offer(child);

						end = child;
				} else {
					end = child;
					return;
				}
			
		}
		}
		
	}
	
	public static void main(String args[]) {

		

		
		  Problem6 obj = new Problem6();

		  char[][] grid = {{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
				  		  {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ','0',' ',' ','X'},
				  		 {'X','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		 {'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ','X'},
				  		{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'}};
				  		 
			

	        List<Node> path = shortestPath(grid);
	        System.out.println("Path length:" + (path.size() - 1));
	        path.stream().forEach(i -> {
	            System.out.println("{" + i.x + "," + i.y + "}");
	        });
	}
	
}

 class Node{
	Integer x;
	Integer y;
	public Node() {
		super();
	}
	
	public Node(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		 if (obj == this) { 
	            return true; 
	        } 

	        if (!(obj instanceof Node)) { 
	            return false; 
	        } 
	          
	        Node n = (Node) obj; 
	        return x==n.getX() && y== n.getY(); 
	}

	
	
}
 