package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Baekjoon_14502_Junhyeong {

	final static int EMPTY = 0;
	final static int WALL = 1;
	final static int VIRUS = 2;

	final static int[] walldr = { 0, 0, 1, 1, 1, -1, -1, -1 };
	final static int[] walldc = { 1, -1, 0, 1, -1, 0, 1, -1 };

	final static int[] virusdr = { 0, -1, 0, 1 };
	final static int[] virusdc = { 1, 0, -1, 0 };

	static int N, M, MapSize;
	static int[][] map = new int[10][10];
	static int[] virusR = new int[10], virusC = new int[10];
	static int virusCount;
	static int originalSafeArea;

	static boolean[][] visited = new boolean[10][10];
	static Deque<Integer> rq = new ArrayDeque<>();
	static Deque<Integer> cq = new ArrayDeque<>();

	static int answer;

	static boolean canBuildWall(int r, int c) {
		if (map[r][c] != EMPTY)
			return false;
		for (int d = 0; d < walldr.length; d++) {
			if (map[r + walldr[d]][c + walldc[d]] == WALL)
				return true;
		}
		return false;
	}

	static void buildWalls(int count, int start) {
		if (count == 3) {
			countSafeArea();
			return;
		}
		int r, c;
		for (int i = start; i < MapSize; i++) {
			r = (i / M) + 1;
			c = (i % M) + 1;
			if (canBuildWall(r, c)) {
				map[r][c] = WALL;
				buildWalls(count + 1, i + 1);
				map[r][c] = EMPTY;
			}
		}
	}

	static void countSafeArea() {
		int i, j;

		int safeArea = originalSafeArea;
		for (i = 1; i <= N; i++) {
			for (j = 1; j <= M; j++) {
				visited[i][j] = false;
			}
		}
		for (i = 0; i < virusCount; i++) {
			rq.offer(virusR[i]);
			cq.offer(virusC[i]);
			visited[virusR[i]][virusC[i]] = true;
		}
		int r, c, d, nr, nc;
		while (!rq.isEmpty()) {
			r = rq.poll();
			c = cq.poll();
			for (d = 0; d < virusdr.length; d++) {
				nr = r + virusdr[d];
				nc = c + virusdc[d];
				if (!visited[nr][nc] && map[nr][nc] == EMPTY) {
					visited[nr][nc] = true;
					safeArea--;
					rq.offer(nr);
					cq.offer(nc);
				}
			}
		}
		answer = Math.max(answer, safeArea);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();
		N = line.charAt(0) - '0';
		M = line.charAt(2) - '0';
		MapSize = N * M;
		virusCount = 0;
		originalSafeArea = 0;

		// Fill border
		for (int i = 0; i < N + 2; i++) {
			map[i][0] = WALL;
			map[i][M + 1] = WALL;
		}
		for (int i = 0; i < M + 2; i++) {
			map[0][i] = WALL;
			map[N + 1][i] = WALL;
		}

		// Input map
		for (int i = 1; i <= N; i++) {
			line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j * 2 - 2) - '0';
				if (map[i][j] == VIRUS) {
					virusR[virusCount] = i;
					virusC[virusCount++] = j;
				} else if (map[i][j] == EMPTY) {
					originalSafeArea++;
				}
			}
		} // input end
		originalSafeArea -= 3; // substract three walls
		answer = 0;
		buildWalls(0, 0);
		System.out.println(answer);

	} // main end

} // class end
