package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_1167_Minkyung {

	static ArrayList<int[]>[] tree;
	static int max[] = {0, 0}, V;
	static boolean[] flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		StringTokenizer st;
		tree = new ArrayList[V+1];
		flag = new boolean[V+1];
		
		for (int i = 0; i < V+1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int index = Integer.parseInt(st.nextToken());
			for (int j = 0; j < V; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == -1) break;
				int b = Integer.parseInt(st.nextToken());
				tree[index].add(new int[] {a, b});
			}	
		}
		
		flag[1] = true;
		dfs(1, 0);
		int[] tempMax = max;
		max = new int[] {0, 0};
		flag[1] = false;
		flag[tempMax[0]] = true;
		dfs(tempMax[0], 0);
		
		System.out.println(max[1]);
	}

	public static void dfs(int index, int sum) {
		boolean isLast = true;
		
		for (int[] v : tree[index]) {
			if(!flag[v[0]]) {
				isLast = false;
				flag[v[0]] = true;
				dfs(v[0], sum + v[1]);
				flag[v[0]] = false;
			}
		}
		
		if(isLast) {
			if(max[1] < sum) {
				max[1] = sum;
				max[0] = index;
			}
		}
	}

}
