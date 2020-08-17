package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baekjoon_17472_Minkyung {
	
	static int N, M, front = -1, rear = -1, cnt = 0;
	static int[][] map, q = new int[100][2];
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<EdgeNode> edgeList;
	static int[] root;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j*2) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					visited[i][j] = true;
					map[i][j] = ++cnt;
					q[++rear] = new int[] {i, j};
					makeGroup();
				}
			}
		}
		
		// 만약 구역의 개수가 1개 이하라면 연결 불가능
		if(cnt < 2) {
			System.out.println(-1);
			return;
		}
		
		edgeList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j]) {
					makeBridge(i, j);
				}
			}
		}
		
		// 만약 다리 개수가 구역의 개수-1개보다 적으면 연결 불가능
		if(edgeList.size()/2 < cnt-1) {
			System.out.println(-1);
			return;
		}
		
		// 최소신장트리를 만들어주기 위해 다리 길이 기준으로 오름차순 정렬을 해준다
		Collections.sort(edgeList);
		
		// MakeSet
		root = new int[cnt+1];
		for (int i = 1; i <= cnt; i++) {
			root[i] = i;
		}
		
		// 다리 개수가 구역의 개수-1이 될때까지 만들어준다
		int bridge = 0;
		int min = 0;
		for (EdgeNode e : edgeList) {
			if(union(e.from, e.to)) {
				bridge++;
				min += e.length;
			}
			if(bridge == cnt-1) break;
		}
		
		// 만약 연결된 다리의 개수가 구역의 개수-1보다 적으면 연결 불가능
		if(bridge < cnt-1) System.out.println(-1);
		else System.out.println(min);
	}

	private static boolean union(int from, int to) {
		int aRoot = find(from);
		int bRoot = find(to);
		if(aRoot == bRoot) return false;
		root[bRoot] = aRoot;
		return true;
	}

	private static int find(int x) {
		if(root[x] != x) root[x] = find(root[x]);
		return root[x];
	}

	// 한 구역의 테두리에 있는 지역에서 상하좌우로 다른 구역을 만날 때까지 다리 길이 재서 ArrayList에 넣어주기
	// 다리길이가 2 이상인 곳만 넣어준다
	// 만약 같은 다리가 있다면 길이가 짧은 걸로 넣어준다
	private static void makeBridge(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int index = 1;
			int nr = row+dr[i]*index, nc = col+dc[i]*index;
			boolean flag = false;
			while(0<=nr && nr<N	&& 0<=nc && nc<M && map[nr][nc] == 0) {
				if(index > 1) flag = true;
				nr = row+dr[i]*(++index);
				nc = col+dc[i]*(index);
			}
			if(flag && 0<=nr && nr<N && 0<=nc && nc<M) {
				--index;
				boolean exist = false;
				for (EdgeNode e : edgeList) {
					if(e.from == map[row][col] && e.to == map[nr][nc]) {
						if(e.length>index) e.length = index;
						exist = true;
						break;
					}
				}
				if(!exist) edgeList.add(new EdgeNode(map[row][col], map[nr][nc], index));
			}
		}	
	}

	// bfs를 통해 같은 구역끼리 같은 번호 넘버링 해주기
	private static void makeGroup() {
		while(front!=rear) {
			int[] x = q[++front];
			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = x[0]+dr[i];
				nc = x[1]+dc[i];
				if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					map[nr][nc] = cnt;
					q[++rear] = new int[] {nr, nc};
				}
			}
		}
		
	}

}

class EdgeNode implements Comparable<EdgeNode>{
	int from;
	int to;
	int length;
	public EdgeNode(int from, int to, int length) {
		super();
		this.from = from;
		this.to = to;
		this.length = length;
	}
	@Override
	public int compareTo(EdgeNode e) {
		return this.length - e.length;
	}
}