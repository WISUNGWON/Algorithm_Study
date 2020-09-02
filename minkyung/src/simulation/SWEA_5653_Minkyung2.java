package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_5653_Minkyung2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][][] cells = new int[700][700][3]; // 생명력, 활성화까지의 시간, 죽음까지의 시간
			int cnt = 0;
			ArrayList<Cell> arr = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n != 0) {
						cells[350-N/2+i][350-M/2+j] = new int[] {n, n+1, 2*n+1};
						arr.add(new Cell (350-N/2+i, 350-M/2+j, n, n+1, 2*n+1));
						cnt++;
					}
				}
			}
			// 상하좌우
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			for (int time = 0; time <= K; time++) {
				// 활성화 된 것들을 퍼지게 해주기
				Collections.sort(arr);
				int size = arr.size();
				for (int s = 0; s < size; s++) {
					Cell cur = arr.get(s);
					int nr, nc;
					if(cur.inactive == 0 && cur.active>0) {
						// 상하좌우
						for (int i = 0; i < 4; i++) {
							nr = cur.r+dr[i]; nc = cur.c+dc[i];
							if(cells[nr][nc][0] == 0) {
								cells[nr][nc][0] = cur.power;
								cells[nr][nc][1] = cur.power+1;
								cells[nr][nc][2] = cur.power*2+1;
								cnt++;
								arr.add(new Cell (nr, nc, cur.power, cur.power+1, cur.power*2+1));
							}
						}
					}
					
				}
				for (int i = arr.size()-1; i >= 0; i--) {
					Cell c = arr.get(i);
					arr.remove(i);
					if(c.inactive>0) {
						c.inactive--;
						c.active--;
						arr.add(c);
					}
					else if(c.active>0) {
						c.active--;
						if(c.active == 0)
							cnt--;
						else arr.add(c);
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static class Cell implements Comparable<Cell> {
		int r;
		int c;
		int power;
		int inactive;
		int active;
		public Cell(int r, int c, int power, int inactive, int active) {
			super();
			this.r = r;
			this.c = c;
			this.power = power;
			this.inactive = inactive;
			this.active = active;
		}
		@Override
		public int compareTo(Cell o) {
			return o.power - this.power;
		}
	}
}
