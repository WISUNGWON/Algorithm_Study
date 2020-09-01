package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * 
 * @author Junhyeong
 * 566ms
 */
public class SWEA_5653_Junhyeong {
	
	static int N, M, K, halfK;
	static boolean[][] visited = new boolean[400][400];
	
	static Queue<int[]> inactive = new ArrayDeque<>(); // 좌표, 원래 생명력, 지금 생명력
	static Queue<int[]> active = new ArrayDeque<>(); // 좌표, 남은 생명력
	
	static ArrayList<int[]> newCell = new ArrayList<>(); // 좌표, 원래 생명력, 남은 생명력
	
	final static int[] dr = {0, 0, 1, -1};
	final static int[] dc = {1, -1, 0, 0};
	
	static int solve() {
		int time = 0, qsize;
		int[] cell;
		while(++time <= K) {
			qsize = inactive.size();
			newCell.clear();
			for (int i = 0; i < qsize; i++) {
				cell = inactive.poll();
				if(cell[3] == 0) { // 비활성이 끝나고 활성의 첫번째 시간
					// 번식, 1짜리는 번식과 동시에 죽는다
					for (int d = 0; d < dr.length; d++) {
						int r = cell[0] + dr[d];
						int c = cell[1] + dc[d];
						if(visited[r][c]) {
							continue;
						}
						boolean isAlreadyInCell = false;
						for (int[] newCell : newCell) {
							if(newCell[0] == r && newCell[1] == c) {
								isAlreadyInCell = true;
								newCell[2] = Math.max(newCell[2], cell[2]);
							}
						}
						if(!isAlreadyInCell) {
							newCell.add(new int[] {r, c, cell[2], cell[2]});
						}
					}
					if(cell[2] > 1) {
						active.offer(cell);
					}
				}
				else {
					cell[3]--;
					inactive.offer(cell);
				}
			}
			
			for (int[] i : newCell) {
				visited[i[0]][i[1]] = true;
				inactive.offer(i);
			}
			
			qsize = active.size();
			for (int i = 0; i < qsize; i++) {
				cell = active.poll();
				if(--cell[2] > 0) {
					active.offer(cell);
				}
			}
//			System.out.println("Inactive");
//			for (int[] i : inactive) {
//				System.out.println(Arrays.toString(i));
//			}
//			System.out.println();
//			System.out.println("active");
//			for (int[] i : active) {
//				System.out.println(Arrays.toString(i));
//			}
//			System.out.println();
		}
		return inactive.size() + active.size();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			halfK = K / 2;
			int l1 = N + K + 10, l2 = M + K + 10;
			for (int i = 0; i < l1; i++) {
				for (int j = 0; j < l2; j++) {
					visited[i][j] = false;
				}
			}
			inactive.clear();
			active.clear();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int power = Integer.parseInt(st.nextToken());
					if(power > 0) {
						int r = i + halfK + 5, c = j + halfK + 5;
						visited[r][c] = true;
						inactive.add(new int[] {r, c, power, power});
					}
				}
			}
			sb.append('#').append(test_case).append(' ').append(solve()).append('\n');		
		}
		System.out.print(sb.toString());
	}
	
}
