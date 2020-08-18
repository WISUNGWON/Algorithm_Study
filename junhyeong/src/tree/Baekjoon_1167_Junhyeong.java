package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baekjoon_1167_Junhyeong {

	static class Node {
		int dest;
		int weight;
		Node next;
		public Node(int dest, int weight) {
			super();
			this.dest = dest;
			this.weight = weight;
		}
	}
	
	
	static int V, answer;
	static Node[] adj;
	static boolean[] visited;
	
	static int dfs(int i) {
		Node n = adj[i];
		ArrayList<Integer> al = new ArrayList<>();
		while(n != null) {
			if(!visited[n.dest]) {
				visited[n.dest] = true;
				al.add(n.weight + dfs(n.dest));
			}
			n = n.next;
		}
		switch(al.size()) {
		case 0:
			return 0;
		case 1:
			return al.get(0);
		}
		Collections.sort(al, Comparator.reverseOrder());
		answer = Math.max(answer, al.get(0)+al.get(1));
		return al.get(0);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine());

		adj = new Node[V+1];
		
		int i, n1, n2, w;
		Node tmp;
		for (i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			while(n2 != -1) {
				w = Integer.parseInt(st.nextToken());
				tmp = adj[n1];
				adj[n1] = new Node(n2, w);
				adj[n1].next = tmp;
				n2 = Integer.parseInt(st.nextToken());
			}
		}
		
		if(V == 2) { // If there are only two vertices
			System.out.println(adj[1].weight);
			return;
		}
		
		visited = new boolean[V+1];
		answer = 0;
		
		// Do dfs
		visited[1] = true;
		int result = dfs(1);
		answer = Math.max(answer, result);
		System.out.println(answer);
	}
	
}
