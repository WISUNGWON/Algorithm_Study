package algorithm_study_jiun.dfs;
import java.util.Scanner;

//https://www.acmicpc.net/problem/1167
public class Baekjoon_1167_jiuin {
	private static int[][] map;
	private static boolean[][] visit;
	private static int max = Integer.MIN_VALUE;
	private static int v;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// --input start--
		 v = sc.nextInt();
		map = new int[v+1][v+1];
//		visit = new boolean[v+1][v+1];
		int start, end, val;
		for (int i = 1; i <= v; i++) { // 간선 정보 입력
			start = sc.nextInt();
			while(true) {
				end = sc.nextInt();
				if(end == -1) break;
				val = sc.nextInt();
				map[start][end] = val;// 두 정점사이의 거리 입력
			}
		}// --input end--
		
		//각 정점을 출발점으로 dfs시작
		for (int i = 1; i < v+1; i++) {
//			visit = new boolean[v+1][v+1];
			dfs(i, i, 0);
		}
		
		System.out.println(max);
	}

	private static void dfs(int start, int end, int len) {
		boolean flag = true; // 현재 정점에서 더이상 갈 곳이 없으면 종료시킬 flag 변수
		for (int i = 1; i < map.length; i++) {
			if(map[start][i] != 0 && !visit[start][i]) { // 하나라도 0이 아니면 갈 곳이 있으므로
				flag = false; // 종료조건 false
			}
		}
		if(flag) {
			if(max < len)
				max = len;
//			System.out.println(len);
			return;
		}
		
		for (int i = 0; i < v+1; i++) {
			if(map[start][i] != 0 && !visit[start][i]) {
				visit[start][i]=true;
				visit[i][start]=true; // 반대방향도 true 처리
				dfs(i,i, len+map[start][i]);
			}
		}
	}
}
