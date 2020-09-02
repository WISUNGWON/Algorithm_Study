package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_5653_Minkyung {

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
			HashMap<Point, Cell> cells = new HashMap<>();
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n != 0) {
						cells.put(new Point(i, j), new Cell(n, n+1, 2*n+1));
						cnt++;
					}
				}
			}
			// 상하좌우
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			for (int time = 0; time <= K; time++) {
				// 활성화 된 것들을 퍼지게 해주기
				HashMap<Point, Cell> clone = (HashMap<Point, Cell>) cells.clone();
				for (Map.Entry<Point, Cell> entry:clone.entrySet()) {
					Point curP = entry.getKey();
					Cell curC = entry.getValue();
					int nr, nc;
					if(curC.inactive == 0 && curC.active>0) {
						// 상하좌우
						for (int i = 0; i < 4; i++) {
							nr = curP.r+dr[i]; nc = curP.c+dc[i];
							Point newP = new Point(nr, nc);
							Cell newC = new Cell(curC.power, curC.power+1, curC.power*2+1);
							if(!cells.containsKey(newP)) {
								cells.put(newP, newC);
								cnt++;
							}
							else {
								Cell origin = cells.get(newP);
								if(origin.inactive == origin.power+1) {
									if(origin.power<newC.power)
										cells.replace(newP, newC);
								}
							}
						}
					}
				}
				for (Map.Entry<Point, Cell> entry:cells.entrySet()) {
					Cell c = entry.getValue();
					Point p = entry.getKey();
					if(c.inactive>0) {
						c.inactive--;
						c.active--;
						cells.replace(p, c);
					}
					else if(c.active>0) {
						c.active--;
						if(c.active == 0)
							cnt--;
						cells.replace(p, c);
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			if(this.r == p.r && this.c == p.c) return true;
			return false;
		}
		@Override
		public int hashCode() {
			return this.r+this.c;
		}
	}
	
	public static class Cell {
		int power;
		int inactive;
		int active;
		public Cell(int power, int inactive, int active) {
			super();
			this.power = power;
			this.inactive = inactive;
			this.active = active;
		}
	}
}
