package algorithm_study_jiun.bfs;
// https://www.acmicpc.net/problem/17472
/*
 * 1. 구역나누기(bfs)
 * 2. 다리 후보 만들기 
 * 		2.1. 섬을 만나면 다리 만들기 시도 (재귀)
 * 		2.2. 인접값이 0(바다)면 그 방향(수평or수직 조건 만족)으로 재귀 지속
 * 		2.3. 길이가 2미만이거나 배열 범위를 벗어나면 다리를 만들지 못하므로 종료
 * 		2.4. 2.3.이 아닌 경우 -> 다리가 완성되므로 다리 후보 리스트에 추가
 * 3. 짧은 다리 먼저 놓으면서 모든 다리 연결하기 (union-find 연산 사용)
 * 		3.1. 다리후보리스트(list)를 다리길이가 짧은 순으로 정렬한다.
 * 		3.2. list에서 하나씩 꺼내 다리를 연결 (두 섬의 부모가 다르면 연결한다. union사용)
 * 		3.3. find 연산 사용해 모든 섬의 부모가 같아지면 모든 다리가 연결됐음으로 판단
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17472_jiun {
	private static boolean[][] visit;
	private static int[][] map;
	private static int m;
	private static int n;
	private static int start = 0;
	private static int min = 0;
	static Queue<int[]> q = new LinkedList<>();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static int[] linked;
	private static List<int[]> list = new ArrayList(); // 0:시작 섬, 1: 도착 섬, 2: 다리길이 를 저장하는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visit = new boolean[n][m];

		for (int i = 0; i < n; i++) { // input 
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // ---input end---
		
		int area = 1;
		for (int i = 0; i < n; i++) {// 1. 구역 나누기
			for (int j = 0; j < m; j++) {
				if (!visit[i][j] && map[i][j] == 1)
					bfs(i, j, area++);
			}
		}
		linked = new int[area];// 각 섬의 부모 초기화
		for (int i = 1; i < area; i++) {
			linked[i] = i; // 자기자신을 부모로 초기화
		}

		for (int i = 0; i < n; i++) { //2. 다리 후보 만들기 
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {//2.1. 섬을 만나면 다리 만들기 시도 (dfs)
					start = map[i][j];
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 0) {
							int cnt = 1;
							makeBridge(nr, nc, cnt, k); // 2.2. 인접값이 0(바다)면 그 방향(수평or수직 조건 만족)으로 재귀 지속
						}
					}
				}
			}
		}
		
//		3. 짧은 다리 먼저 놓으면서 모든 다리 연결하기 (union-find 연산 사용)
		Collections.sort(list, new Comparator<int[]>() { 	//3.1. 다리후보리스트(list)를 다리길이가 짧은 순으로 정렬한다.
			@Override 
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});

		// 3.2. list에서 하나씩 꺼내 다리를 연결 (두 섬의 부모가 다르면 연결한다. union사용)
		for (int[] tmp : list) {
			if (Find(tmp[0]) != Find(tmp[1])) {// 시작섬과 도착섬이 연결되지 않았다면 (= 부모가 같지 않다면)
				Union(tmp[0], tmp[1]);// 다리를 놓고 합친다.
				min += tmp[2]; // 다리길이의 최솟값을 구하기 위해 놓은 현재 다리의 길이를 더해준다.
			}
		}
		// 3.3. 모든 다리가 연결되었는지 확인 - 하나라도 부모가 다른 섬이 있다면 모든 섬이 연결되지 않은 것이다.
		int standard = Find(1);
		for (int i = 2; i < area; i++) {
			if (standard != Find(i)) {
				System.out.println("-1");
				return;
			}
		}

		System.out.println(min); // 최솟값 출력
	}

	/** 2. 다리 후보 만들기 - 재귀*/
	private static void makeBridge(int r, int c, int cnt, int k) {
		int nr = r + dr[k];
		int nc = c + dc[k];

		if (!(nr >= 0 && nr < n && nc >= 0 && nc < m)) { //2.3. 길이가 2미만이거나 배열 범위를 벗어나면 다리를 만들지 못하므로 종료
			return;
		}

		if (map[nr][nc] > 0) {
			if (cnt >= 2) {// 2.4. 2.3.이 아닌 경우 -> 다리가 완성되므로 다리 후보 리스트에 추가
				list.add(new int[] { start, map[nr][nc], cnt });// 0:시작 섬, 1: 도착 섬, 2: 다리길이 를 저장
			}
			return;
		}
		if (map[nr][nc] == 0) {
			makeBridge(nr, nc, cnt + 1, k);
		}
	}

	private static void bfs(int r, int c, int area) {
		q.offer(new int[] { r, c, area });// �ʱⰪ
		visit[r][c] = true;
		map[r][c] = area;
		while (!q.isEmpty()) {
			r = q.peek()[0];
			c = q.poll()[1];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visit[nr][nc] && map[nr][nc] == 1) {
					q.offer(new int[] { nr, nc, area });
					visit[nr][nc] = true;
					map[nr][nc] = area;
				}
			}
		}
	}

	static int Find(int x) { // 원소의 루트부모(각 집합을 구분하는 번호)를 찾아주는 함수
		if (linked[x] == x)
			return x;
		return Find(linked[x]);
	}

	static void Union(int x, int y) { // 각 원소 집합을 하나로 합쳐주는 함수
		x = Find(x);
		y = Find(y);
		if (x < y) 
			linked[y]=x;
		else linked[x]=y;
	}
}
