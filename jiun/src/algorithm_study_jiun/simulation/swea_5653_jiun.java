
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_5653_jiun {
	static final int REAL_INDEX = 400; 
	static final int INF = 1000; // n*m == 2500
	
	static int[][] map;
	static boolean[][] visited;
	
	static PriorityQueue<Cell> pq; // 활성세포 목록
	static Queue<Cell> q; // 죽지 않은 세포들
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int T, N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) { // 주어진 시간만큼
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			q = new LinkedList<>();
			pq = new PriorityQueue<>();
			map = new int[INF][INF];
			visited = new boolean[INF][INF];
			
			for(int i = 0 ; i < N ; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < M ; ++j) {
					int x = REAL_INDEX + i;
					int y = REAL_INDEX + j; // 임의로 중간에 배치.
					int lifecycle = Integer.parseInt((st.nextToken()));
					
					if(lifecycle > 0) {
						map[x][y] = lifecycle;
						visited[x][y] = true; // false면 비활성화 상태이거나 활성화가 완료됐음을 의미한다.
						q.add(new Cell(x, y, lifecycle, lifecycle, lifecycle * 2)); // dead: 생명주기가 2배를 지나면 죽음.
					}
				}
			}
			
			for(int time = 1 ; time <= K ; ++time) {
				changeStatus(time); // 활성화, 비활성화, 죽음 상태 표시 ==> 활성화: 우선순위 큐,큐 둘 다, 비활성화: 큐
				bfs(time);
			}
			sb.append("#").append(t).append(" ").append(q.size()).append("\n"); 
		}
		System.out.println(sb);
	}
	
	
	
	private static void bfs(int time) {
		while(!pq.isEmpty()) {
			Cell cell = pq.poll();
			
			if (time < cell.dead) { 
				q.add(cell);
			}
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nx = cell.x + dx[d];
				int ny = cell.y + dy[d];
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					map[nx][ny] = cell.lifecycle;
					q.add(new Cell(nx, ny, cell.lifecycle,
							time + cell.lifecycle,
							time + cell.lifecycle * 2));
				}
			}
		}
	}

	private static void changeStatus(int time) {
		int qSize = q.size();
		
		for(int i = 0 ; i < qSize ; ++i) { // 큐 갯수만큼 돌리기!. 큐가 빌때까지 아님!
			Cell cell = q.poll();
			
			if(time <= cell.start) { // 비활성
				q.add(cell);
			} else if(time == cell.start + 1) { // 활성화
				pq.add(cell); // pq에 넣어 낮은 시간부터 처리 --> 최종적으로 lifecycle이 큰 것으로 업데이트 된다.
			} else if(time > cell.start && time < cell.dead) { // 비활성
				q.add(cell);
			}
			
		}
		
	}	
	static class Cell implements Comparable<Cell>{
		int x, y, lifecycle;
		int start, dead;
		
		public Cell(int x, int y, int lifecycle, int start, int dead) {
			this.x = x;
			this.y = y;
			this.lifecycle = lifecycle;
			this.start = start;
			this.dead = dead;
		}

		@Override
		public int compareTo(Cell o) {
			return  o.lifecycle-this.lifecycle; //생명주기 오름차순 정렬 --> 최종적으로 lifecycle이 큰 것으로 업데이트 된다.
		}
	}
}
