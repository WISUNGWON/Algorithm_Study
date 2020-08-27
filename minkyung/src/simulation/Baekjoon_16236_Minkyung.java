package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon_16236_Minkyung {

	static int N, size = 2, amount = 0, answer = 0, len;
	static int[][] map;
	static int[] shark = new int[2];
	static int[] target = new int[2];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(2*j)-'0';
				if(map[i][j] == 9) {
					shark = new int[] {i, j};
					map[i][j] = 0;
				}
			}
		}
		
		while(find()) {
			amount++;
			if(size==amount) {
				amount = 0;
				size++;
			}
			map[target[0]][target[1]] = 0;
			answer+=len;
			shark[0] = target[0];
			shark[1] = target[1];
		}
			
		System.out.println(answer);
	}

	// 상 좌 우 하
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	
	private static boolean find() {
		int[][] q = new int[N*N*2][3];
		int front = -1, rear = -1;
		boolean[][] visited = new boolean[N][N];
		int[] shortest = new int[2];
		ArrayList<int[]> arr = new ArrayList<>();
		q[++rear] = new int[] {shark[0], shark[1], 0};
		int nr, nc;
bfs:	while(front!=rear) {
			int[] cur = q[++front];
			if(map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) {
				arr.add(new int[] {cur[0], cur[1]});
				len = cur[2];
				while(front!=rear) {
					int[] cur2 = q[++front];
					if(map[cur2[0]][cur2[1]] != 0 && map[cur2[0]][cur2[1]] < size && cur2[2] == cur[2]) {
						arr.add(new int[] {cur2[0], cur2[1]});
					}
				}
				if(arr.size()==1) {
					target[0] = cur[0]; target[1] = cur[1];
					return true;
				}
				break bfs;
			}
			for (int i = 0; i < 4; i++) {
				nr = cur[0]+dr[i]; nc = cur[1]+dc[i];
				if(0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc]<=size && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q[++rear] = new int[] {nr, nc, cur[2]+1};
				}
			}
		}
		
		if(arr.size()==0) return false;
		
		shortest = new int[] {arr.get(0)[0], arr.get(0)[1]};
		for (int i = 1; i < arr.size(); i++) {
			if(arr.get(i)[0] < shortest[0]) shortest = new int[] {arr.get(i)[0], arr.get(i)[1]};
			else if(arr.get(i)[0] == shortest[0]&&arr.get(i)[1] < shortest[1])shortest = new int[] {arr.get(i)[0], arr.get(i)[1]};
		}
		target[0] = shortest[0]; target[1] = shortest[1];
		return true;
	}

}
