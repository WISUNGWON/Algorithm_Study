package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_19237_Minkyung {

	static int N, M, K, time = 1, cnt;
	static int[][] shark;
	static int[][][] smell, order;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		order = new int[M+1][5][5];
		shark = new int[M+1][3];
		smell = new int[N][N][2];
		cnt = M;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num!=0) shark[num] = new int[] {i, j, 0};
			}
		}

		String s = br.readLine();
		for (int i = 0; i < M; i++) {
			shark[i+1][2] = s.charAt(2*i)-'0';
		}
		
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				s = br.readLine();
				for (int k = 1; k <= 4; k++) {
					order[i][j][k] = s.charAt((k-1)*2)-'0';
				}
			}
		}
		
		for (int i = 1; i <= M; i++) {
			smell[shark[i][0]][shark[i][1]] = new int[] {i, K};
		}
		
		while(time<=1000 && cnt>1) {
			// 상어 이동 & 냄새뿌리기 (냄새가 K+1이면 번호 큰 것이 죽는다)
			for (int i = 1; i <= M; i++) {
				if(shark[i][2]!=0) {
					int[] cur = shark[i];
					int nr, nc, flag = 0;
					for (int j = 1; j <= 4; j++) { // order[i][cur[2]] 우선순위 4방향
						nr = cur[0]+dr[order[i][cur[2]][j]]; nc = cur[1]+dc[order[i][cur[2]][j]];
						if(0<=nr&&nr<N&&0<=nc&&nc<N) {
							if(smell[nr][nc][1] == 0) { // 빈칸 일 때
								shark[i] = new int[] {nr, nc, order[i][cur[2]][j]};
								flag = 0;
								break;
								// 내가 왔던 곳일 때
							} else if(smell[nr][nc][0] == i && flag == 0) flag = j;
						}
					}
					if(flag != 0) { // 내 자리로 돌아가기
						nr = cur[0]+dr[order[i][cur[2]][flag]]; nc = cur[1]+dc[order[i][cur[2]][flag]];
						shark[i] = new int[] {nr, nc, order[i][cur[2]][flag]};
					}
				}
			}
			
			// smell 체크해주기
			for (int i = 1; i <= M; i++) {
				if(shark[i][2] == 0) continue;
				if(smell[shark[i][0]][shark[i][1]][1] != K+1) {
					smell[shark[i][0]][shark[i][1]][0] = i;
					smell[shark[i][0]][shark[i][1]][1] = K+1;
				} else {
					shark[i][2] = 0;
					cnt--;
				}
			}
			
			// 모든 냄새 -1 (냄새가 0이 되면 그 번호 비워주기)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(smell[i][j][1]>0) {
						if(--smell[i][j][1] == 0) smell[i][j][0] = 0;
					}
				}
			}
			
			time++;
		}
		
		if(cnt == 1) System.out.println(--time);
		else System.out.println(-1);
	}

}
