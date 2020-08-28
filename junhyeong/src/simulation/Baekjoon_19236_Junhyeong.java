package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_19236_Junhyeong {

	final static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	final static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	static void fishMove(int[][] map, int[] fR, int[] fC, int[] fD, boolean[] dead, int sR, int sC) {
		for (int i = 1; i <= 16; i++) {
			if(dead[i]) continue;
			int r1 = fR[i];
			int c1 = fC[i];
			while(true) {
				int nr = r1 + dr[fD[i]];
				int nc = c1 + dc[fD[i]];
				if(0 <= nr && nr < 4 && 0 <= nc && nc < 4 && !(nr == sR && nc == sC)) {
					if(map[nr][nc] > 0) {
						int j = map[nr][nc];
						int tmp = map[nr][nc];
						map[nr][nc] = map[r1][c1];
						map[r1][c1] = tmp;
						swap(fR, i, j);
						swap(fC, i, j);
					}
					else {
						map[nr][nc] = i;
						map[r1][c1] = 0;
						fR[i] = nr;
						fC[i] = nc;
					}
					break;
				}
				else {
					fD[i] = (fD[i] + 1) % 8;
				}
			}
		}
	}
	
	static int sharkEat(int[][] map, int[] fR, int[] fC, int[] fD, boolean[] dead, int sR, int sC, int sD) {
		int maxFish = 0;
		int nr = sR, nc = sC;
		while(true) {
			nr += dr[sD];
			nc += dc[sD];
			if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
				break;
			}
			if(map[nr][nc] > 0) {
				int victim = map[nr][nc];
				int[][] newMap = new int[4][];
				for (int i = 0; i < 4; i++) {
					newMap[i] = map[i].clone();
				}
				int[] newFR = fR.clone();
				int[] newFC = fC.clone();
				int[] newFD = fD.clone();
				newMap[nr][nc] = 0;
				dead[victim] = true;
				fishMove(newMap, newFR, newFC, newFD, dead, nr, nc);
				maxFish = Math.max(maxFish, victim + sharkEat(newMap, newFR, newFC, newFD, dead, nr, nc, fD[victim]));
				dead[victim] = false;
			}
		}
		return maxFish;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int a, b;
		int[] fR = new int[17], fC = new int[17], fD = new int[17];
		boolean[] dead = new boolean[17];
		int sR, sC, sD;
		int[][] map = new int[4][4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken()) - 1;
				fR[a] = i;
				fC[a] = j;
				fD[a] = b;
				map[i][j] = a;
			}
		}
		sR = 0;
		sC = 0;
		int firstBlood = map[0][0];
		dead[map[0][0]] = true;
		sD = fD[map[0][0]];
		map[0][0] = 0;
		fishMove(map, fR, fC, fD, dead, sR, sC);
		System.out.println(firstBlood + sharkEat(map, fR, fC, fD, dead, sR, sC, sD));
	}
	
}
