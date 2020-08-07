package algorithm_study_jiun.dfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class bfs {
	private static List<Node>[] map;
	private static boolean[] visit;
	private static int max = Integer.MIN_VALUE;
	private static int v;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// --input start--
		v = Integer.parseInt(br.readLine());
		map = new ArrayList[v + 1];
		visit = new boolean[v + 1];
		int start, end, val;
		for (int i = 1; i <= v; i++) { // 간선 정보 입력
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			map[start] = new ArrayList<>();
			while(true) {
				end = Integer.parseInt(st.nextToken());
				if(end == -1) break;
				val = Integer.parseInt(st.nextToken());
				map[start].add(new Node(end, val));// 두 정점사이의 거리 입력
			}
		} // --input end--

		// 각 정점을 출발점으로 dfs시작
		for (int i = 1; i < v + 1; i++) {
			visit = new boolean[v + 1];
			dfs(map[i].get(0), 0);
		}

		System.out.println(max);
	}

	private static void dfs(Node v, int dist) {
		visit[v.end] = true;

		for (Node tmp : map[v.end]) {
			if (!visit[tmp.end])
				dfs(tmp, dist + tmp.val);
		}

		if (max < dist) {
			max = dist;
		}
		return;
	}

	static class Node {
		int end, val;

		public Node(int end, int val) {
			this.end = end;
			this.val = val;
		}
	}
}