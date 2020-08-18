package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Baekjoon_17472_Junhyeong {

	static class Edge implements Comparable<Edge> {
		int v1, v2;
		int len;

		public Edge(int v1, int v2, int len) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.len = len;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Edge) {
				Edge e = (Edge)obj;
				if(v1 == e.v1 && v2 == e.v2 && len == e.len) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int compareTo(Edge o) {
			if(len == o.len) {
				if(v1 == o.v1) {
					return v2 - o.v2;
				}
				return v1 - o.v1;
			}
			return len - o.len;
		}
	}

	static int N, M;
	static char[][] map;
	static int[] parent;
	final static int[] dr = {0, 0, 1, -1};
	final static int[] dc = {1, -1, 0, 0};
	static Queue<Integer> rq = new ArrayDeque<>();
	static Queue<Integer> cq = new ArrayDeque<>();
	static void bfs(int initr, int initc, int islandNum) {
		char num = (char)islandNum;
		rq.offer(initr);
		cq.offer(initc);
		map[initr][initc] = num;
		while(!rq.isEmpty()) {
			int r = rq.poll();
			int c = cq.poll();
			for (int d = 0; d < dc.length; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == '1') {
					rq.offer(nr);
					cq.offer(nc);
					map[nr][nc] = num;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// Input
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().replace(" ", "").toCharArray();
		}

		// Convert map so that islands have their own number
		int totalIsland = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == '1') {
					bfs(i, j, ++totalIsland);
				}
				else if(map[i][j] == '0') {
					map[i][j] = 0;
				}
			}
		}

		int[][] distance = new int[totalIsland+1][totalIsland+1];
		for (int i = 0; i < distance.length; i++) {
			for (int j = 0; j < distance.length; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}

		// Calculate paths between an island and the others
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] > 0) {
					int count = 0;
					while(j + 1 < M && map[i][j+1] == map[i][j]) {
						j ++;
						count ++;
					}
					for (int j2 = j+2; j2 < M; j2++) {
						if(map[i][j2] > 0) {
							if(map[i][j2] != map[i][j]) {
								if(j2 - j < 3) {
									break;
								}
								distance[map[i][j]][map[i][j2]] = Math.min(distance[map[i][j]][map[i][j2]], j2-j);
								distance[map[i][j2]][map[i][j]] = distance[map[i][j]][map[i][j2]];
								break;
							}
							break;
						}
					}
					j -= count;
					count = 0;
					while(i + 1 < N && map[i+1][j] == map[i][j]) {
						i++;
						count++;
					}
					for (int i2 = i+2; i2 < N; i2++) {
						if(map[i2][j] > 0) {
							if(map[i2][j] != map[i][j]) {
								if(i2 - i < 3) {
									break;
								}
								distance[map[i][j]][map[i2][j]] = Math.min(distance[map[i][j]][map[i2][j]], i2-i);
								distance[map[i2][j]][map[i][j]] = distance[map[i][j]][map[i2][j]];
								break;
							}
							break;
						}
					}
					i -= count;
				}
			}
		}

		TreeSet<Edge> set = new TreeSet<>();
		for (int i = 1; i < distance.length; i++) {
			for (int j = i+1; j < distance.length; j++) {
				if(distance[i][j] < Integer.MAX_VALUE) {
					if(set.add(new Edge(i, j, distance[i][j]-1))) {
//						System.out.println(i + " " + j + " " + (distance[i][j]-1));
					}

				}
			}
		}

		Edge e;
		parent = new int[totalIsland+1];
		for (int i = 1; i <= totalIsland; i++) {
			parent[i] = i;
		}
		int merged = 1;
		int answer = 0;
		while(!set.isEmpty() || merged < totalIsland) {
			e = set.pollFirst();
			if(e == null) {
				break;
			}
			if(union(e.v1, e.v2)) {
				answer += e.len;
				merged ++;
			}
		}
		System.out.println(merged < totalIsland?-1:answer);

//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map[0].length; j++) {
//				System.out.print((int)map[i][j] + " ");
//			}
//			System.out.println();
//		}

		//		for (int i = 0; i < distance.length; i++) {
		//			System.out.println(Arrays.toString(distance[i]));
		//		}


	}

	static int findSet(int a) {
		if(parent[a] != a) {
			parent[a] = findSet(parent[a]);
		}
		return parent[a];
	}

	static boolean union(int a, int b) {
		int parA = findSet(a);
		int parB = findSet(b);
		if(parA != parB) {
			parent[parB] = parA;
			return true;
		}
		return false;
	}

}
