package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15644_Minkyung {

	static int N, M;
	static char[][] map;
	static int[] red, blue;
	static int min = Integer.MAX_VALUE;
	static String answer = "";
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		red = new int[2];
		blue = new int[2];
		
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'R') red = new int[] {i, j};
				else if(map[i][j] == 'B') blue = new int[] {i, j};
			}
		}
		
		dfs(0, "");
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else {
			System.out.println(min);
			System.out.println(answer);
		}
	}

	public static void dfs(int cnt, String dir) {
		if(cnt>9 || cnt >= min) return;
		
		int crr = red[0], crc = red[1], cbr = blue[0], cbc = blue[1];
		int nrr, nrc, nbr, nbc;
		for (int i = 0; i < 4; i++) {
			nrr = crr+dr[i];
			nrc = crc+dc[i];
			nbr = cbr+dr[i];
			nbc = cbc+dc[i];
			boolean rfin = false, bfin = false;
			if(map[nrr][nrc] != '#') {
				if(map[nrr][nrc] == 'O') rfin = true;
				else {
					while(true) {
						nrr+=dr[i];
						nrc+=dc[i];
						if(map[nrr][nrc] == 'O') {
							rfin = true;
							break;
						} else if (map[nrr][nrc] == '#') {
							nrr-=dr[i];
							nrc-=dc[i];
							break;
						}
					}
				}
			} else {
				nrr-=dr[i];
				nrc-=dc[i];
			}
			if(map[nbr][nbc] != '#') {
				if(map[nbr][nbc] == 'O') bfin = true;
				else {
					while(true) {
						nbr+=dr[i];
						nbc+=dc[i];
						if(map[nbr][nbc] == 'O') {
							bfin = true;
							break;
						} else if (map[nbr][nbc] == '#') {
							nbr-=dr[i];
							nbc-=dc[i];
							break;
						}
					}
				}
			} else {
				nbr-=dr[i];
				nbc-=dc[i];
			}
			if(bfin) continue;
			if(rfin) {
				min = Math.min(min, cnt+1);
				if(i == 0) answer = dir+"U";
				else if (i == 1) answer = dir+"D";
				else if (i == 2) answer = dir+"L";
				else if (i == 3) answer = dir+"R";
				return;
			}
			if(nrr == nbr && nrc == nbc) {
				if(i==0) {
					if(crr > cbr) nrr++;
					else nbr++;
				}
				else if(i==1) {
					if(crr > cbr) nbr--;
					else nrr--;
				}
				else if(i==2) {
					if(crc > cbc) nrc++;
					else nbc++;
				} else {
					if(crc > cbc) nbc--;
					else nrc--;
				}
			}
			red[0] = nrr; red[1] = nrc; blue[0] = nbr; blue[1] = nbc;
			if(i == 0) dfs(cnt+1, dir+"U");
			else if(i == 1) dfs(cnt+1, dir+"D");
			else if(i == 2) dfs(cnt+1, dir+"L");
			else dfs(cnt+1, dir+"R");
			red[0] = crr; red[1] = crc; blue[0] = cbr; blue[1] = cbc;
		}
	}
	
}
