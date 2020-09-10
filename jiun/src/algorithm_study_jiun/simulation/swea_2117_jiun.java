import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_2117_jiun {
	private static int[][] map;
	private static boolean[][] visit;
	static int n,m;// 맵의 크기, 방범 비용

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer  st;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			tmpMap = new int[n][n];
			for (int i = 0; i < n; i++) {
				String s= br.readLine();
				for (int j = 0,idx=0; j < n; j++,idx+=2) {
					map[i][j] = s.charAt(idx)-'0';
				}
			}// input end
			
			int max = -1,maxCnt = -1;
			//k만큼의 방범 구역 검사: 최대 (n/2) + 1까지
			for (int i = 1; i <= n+2 ; i++) {
				for(int j = 0; j < n; j++) {
					for (int j2 = 0; j2 < n; j2++) {
						
						visit = new boolean[n][n];
						cnt = 0;
						int cnt = makeSafeArea(j,j2,i);
						
						int benefit = m*cnt - ((i-1*i-1)+(i)*(i));
						if(max<benefit) {
							max = benefit;
							maxCnt = cnt;
						}
					}
					
				}
			}
			
			sb.append("#").append(t).append(" ").append(maxCnt).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	/** k크기의  안전구역의 집의 갯수를 구해 방범회사의 이득을 구한다. )*/
	static Queue<int[]> q = new LinkedList<>();
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	static int cnt = 0;
	private static int makeSafeArea(int r, int c, int k) {
		int benefit = 0;
//		System.out.println("k: "+k);
		int tmpk = 1;
		q.offer(new int[] {r,c,1});
		cnt++;
		visit[r][c]=true; // 가운데 방문표시
		
		while(!q.isEmpty()) {
			
			int[] cur = q.poll();
			tmpk = cur[2];
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i]; // 상하좌우로 이동.
				
				if(nr < 0||nr>=n||nc<0||nc>=n||visit[nr][nc] || tmpk == k)
					continue;
				
				visit[nr][nc]=true;
				q.offer(new int[] {nr,nc,tmpk+1});
				if(map[nr][nc] == 1) {
					cnt++;
				}
				
			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(visit[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("cnt: "+cnt+" k:"+k);
//		System.out.println(m*cnt - ((k*k)+(k-1)*(k-1)));
//		
		return cnt;
		
	}
}// end of class
