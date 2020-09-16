package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_Junhyeong {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N, M, R, C, L, qsize, time, answer, r, c;
		String line;
		char[][] map = new char[50][];
		int[][] visited = new int[50][50];
		Queue<int[]> q = new ArrayDeque<>();
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				line = br.readLine();
				map[i] = line.replace(" ", "").toCharArray();
			}
			q.clear();
			q.offer(new int[] {R, C});
			visited[R][C] = testCase;
			time = 1;
			answer = 1;
			while(q.size() > 0 && time < L) {
				qsize = q.size();
				time ++;
				for (int qs = 0; qs < qsize; qs++) {
					int[] pos = q.poll();
					r = pos[0];
					c = pos[1];
					switch(map[r][c]) {
					case '1':
						if(r - 1 >= 0 && (map[r-1][c] == '1' || map[r-1][c] == '2' || map[r-1][c] == '5' || map[r-1][c] == '6') && visited[r-1][c] < testCase) {
							visited[r-1][c] = testCase;
							q.offer(new int[] {r-1, c});
							answer ++;
						}
						if(r + 1 < N && (map[r+1][c] == '1' || map[r+1][c] == '2' || map[r+1][c] == '4' || map[r+1][c] == '7') && visited[r+1][c] < testCase) {
							visited[r+1][c] = testCase;
							q.offer(new int[] {r+1, c});
							answer ++;
						}
						if(c - 1 >= 0 && (map[r][c-1] == '1' || map[r][c-1] == '3' || map[r][c-1] == '4' || map[r][c-1] == '5') && visited[r][c-1] < testCase) {
							visited[r][c-1] = testCase;
							q.offer(new int[] {r, c-1});
							answer ++;
						}
						if(c + 1 < M && (map[r][c+1] == '1' || map[r][c+1] == '3' || map[r][c+1] == '6' || map[r][c+1] == '7') && visited[r][c+1] < testCase) {
							visited[r][c+1] = testCase;
							q.offer(new int[] {r, c+1});	
							answer ++;
						}
						break;
					case '2':
						if(r - 1 >= 0 && (map[r-1][c] == '1' || map[r-1][c] == '2' || map[r-1][c] == '5' || map[r-1][c] == '6') && visited[r-1][c] < testCase) {
							visited[r-1][c] = testCase;
							q.offer(new int[] {r-1, c});
							answer ++;
						}
						if(r + 1 < N && (map[r+1][c] == '1' || map[r+1][c] == '2' || map[r+1][c] == '4' || map[r+1][c] == '7') && visited[r+1][c] < testCase) {
							visited[r+1][c] = testCase;
							q.offer(new int[] {r+1, c});
							answer ++;
						}
						break;
					case '3':
						if(c - 1 >= 0 && (map[r][c-1] == '1' || map[r][c-1] == '3' || map[r][c-1] == '4' || map[r][c-1] == '5') && visited[r][c-1] < testCase) {
							visited[r][c-1] = testCase;
							q.offer(new int[] {r, c-1});
							answer ++;
						}
						if(c + 1 < M && (map[r][c+1] == '1' || map[r][c+1] == '3' || map[r][c+1] == '6' || map[r][c+1] == '7') && visited[r][c+1] < testCase) {
							visited[r][c+1] = testCase;
							q.offer(new int[] {r, c+1});	
							answer ++;
						}
						break;
					case '4':
						if(r - 1 >= 0 && (map[r-1][c] == '1' || map[r-1][c] == '2' || map[r-1][c] == '5' || map[r-1][c] == '6') && visited[r-1][c] < testCase) {
							visited[r-1][c] = testCase;
							q.offer(new int[] {r-1, c});
							answer ++;
						}
						if(c + 1 < M && (map[r][c+1] == '1' || map[r][c+1] == '3' || map[r][c+1] == '6' || map[r][c+1] == '7') && visited[r][c+1] < testCase) {
							visited[r][c+1] = testCase;
							q.offer(new int[] {r, c+1});	
							answer ++;
						}
						break;
					case '5':
						if(r + 1 < N && (map[r+1][c] == '1' || map[r+1][c] == '2' || map[r+1][c] == '4' || map[r+1][c] == '7') && visited[r+1][c] < testCase) {
							visited[r+1][c] = testCase;
							q.offer(new int[] {r+1, c});
							answer ++;
						}
						if(c + 1 < M && (map[r][c+1] == '1' || map[r][c+1] == '3' || map[r][c+1] == '6' || map[r][c+1] == '7') && visited[r][c+1] < testCase) {
							visited[r][c+1] = testCase;
							q.offer(new int[] {r, c+1});	
							answer ++;
						}
						break;
					case '6':
						if(r + 1 < N && (map[r+1][c] == '1' || map[r+1][c] == '2' || map[r+1][c] == '4' || map[r+1][c] == '7') && visited[r+1][c] < testCase) {
							visited[r+1][c] = testCase;
							q.offer(new int[] {r+1, c});
							answer ++;
						}
						if(c - 1 >= 0 && (map[r][c-1] == '1' || map[r][c-1] == '3' || map[r][c-1] == '4' || map[r][c-1] == '5') && visited[r][c-1] < testCase) {
							visited[r][c-1] = testCase;
							q.offer(new int[] {r, c-1});
							answer ++;
						}
						break;
					case '7':
						if(r - 1 >= 0 && (map[r-1][c] == '1' || map[r-1][c] == '2' || map[r-1][c] == '5' || map[r-1][c] == '6') && visited[r-1][c] < testCase) {
							visited[r-1][c] = testCase;
							q.offer(new int[] {r-1, c});
							answer ++;
						}
						if(c - 1 >= 0 && (map[r][c-1] == '1' || map[r][c-1] == '3' || map[r][c-1] == '4' || map[r][c-1] == '5') && visited[r][c-1] < testCase) {
							visited[r][c-1] = testCase;
							q.offer(new int[] {r, c-1});
							answer ++;
						}
						break;
					}
				}
			} // bfs while end
			sb.append('#').append(testCase).append(' ').append(answer).append('\n');
		}// testcase for end
		System.out.print(sb);
	}
	
}
