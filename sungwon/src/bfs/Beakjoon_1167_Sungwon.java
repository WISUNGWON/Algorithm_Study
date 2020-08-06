package bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beakjoon_1167_Sungwon {

	private static List<Edge>[] list;
	private static int V;
	private static int[] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine()); // 정점의 개수
		list = new ArrayList[V + 1]; // 트리 (List<Edge>[])를 넣어주든 안넣어주든 상관 없음 
		dist = new int[V + 1]; // 간선 길이의 최대 값을 저장하는 배열
		for (int i = 0; i <= V; i++) { // List[]를 만들어주면 그냥 빈공간이기 때문에 간선들을 채워줘야 함
			list[i] = new ArrayList<Edge>(); // 트리에 빈 간선들 채우기
		}
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()); // 시작 노드
			while (true) {
				int e = Integer.parseInt(st.nextToken()); // 연결 노드
				if (e == -1) { // 연결 노드가 아닌 -1을 받으면 입력 종료
					break;
				}
				int val = Integer.parseInt(st.nextToken()); // 두 노드의 길이
				list[s].add(new Edge(e, val)); // 노드에 연결 정보 넣기 (연결 노드, 간선 길이)
			}
		} // ----------------- 입력 끝 -----------------------
		
		dist = bfs(1); // 가장 긴 길이의 정점 찾기 (어디서 출발하던 결국 최대 길이임)
		int start = 1; // bfs에 1노드로 시작했기 때문에 길이의 최대값을 판단하는 변수도 1부터
		for (int i = 2; i <= V; i++) {
			if (dist[start] < dist[i]) {
				start = i; // start = 현재 선택한 노드에서 가장 먼곳에 있는 시작노드 숫자(여기서부터 다시 끝으로 가기 때문에 변수명을 start로 설정함)
			}
		}
		
		dist = bfs(start); // 가장 긴 길이의 정점(start변수의 값)을 루트로 dist배열 초기화
		Arrays.sort(dist);
		System.out.println(dist[V]); // 정렬 후 최대값 출력

	} // end of main
	
	public static int[] bfs (int start) {
		boolean[] visited = new boolean[V + 1];
		dist = new int[V + 1]; // dist를 초기화 해야 두번째 bfs 시행시에 맨 끝값을 탐색 할 수 있음
		Queue<Integer> q = new LinkedList<>(); // 큐에 들어가는 숫자가 간선의 시작 노드를 가르킨다
		q.add(start); // start = 1번 시행시에는 아무 노드나, 2번 시행시에는 가장 마지막 부분에서 시작
		visited[start] = true;
		while (!q.isEmpty()) {
			int v = q.poll();
			for (Edge edge : list[v]) { // list[v]에는 v간선의 시작, 끝, 길이가 담겨 있다
				int end = edge.end;
				int val = edge.val;
				if (!visited[end]) {
					visited[end] = true;
					q.add(end);
					dist[end] = dist[v] + val;
				}
			}
		} // end of while
		return dist;
	} // end of bfs
} // end of class

class Edge {
	int end;
	int val;
	
	public Edge() {
	}

	public Edge(int end, int val) {
		this.end = end;
		this.val = val;
	}
	
} // end of Edge
