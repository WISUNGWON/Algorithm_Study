import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
// bfs는 비용은 고려하지 않음
public class Baekjoon_4485_jiun {
	private static int[][] map;
	private static int n,min;
	private static int[][] dist;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		final int INF = 987654321;
		while(true) {//tc진행
			n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			min = Integer.MAX_VALUE;
			map = new int[n][n];
			dist = new int[n][n];
			
			for(int i = 0; i< n; i++) {
				String input = br.readLine();
				for (int j = 0,idx=0; j < n; j++,idx+=2) {
					map[i][j]= input.charAt(idx)-'0';
				}
			}// input 
			for (int i = 0; i < n; i++) 
				for (int j = 0; j < n; j++) 
					dist[i][j] = INF;

			bfs(); // map탐색(0:r좌표, 1:c좌표, 2:빼앗긴돈)
			sb.append("Problem ").append(tc).append(": ").append(dist[n-1][n-1]).append("\n");
			tc++;
		}//while
		System.out.println(sb);
	}// main

	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		
		q.offer(new int[] {0,0});
		dist[0][0]=map[0][0];
		while(!q.isEmpty()){
			int r = q.peek()[0];
			int c = q.poll()[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= n ) continue;
				if(dist[nr][nc] > dist[r][c] + map[nr][nc]) { 
					dist[nr][nc] = dist[r][c] + map[nr][nc];// 더 적은 비용으로 갱신
					q.offer(new int[] {nr,nc});
				}
			}
		}//bfs
		
		return;
	}
}// class
