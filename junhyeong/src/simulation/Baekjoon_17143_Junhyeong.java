package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_17143_Junhyeong {

	static class Shark{
		int s, d, z;

		public Shark(int s, int d, int z) {
			super();
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}
	
	static int R, C, M;
	
	static Shark[][] lake;
	static Shark[][] newLake;
	
	static int aliveShark;
	
	static int fish(int fisherC) {
		int catched = 0;
		for (int i = 1; i <= R; i++) {
			if(lake[i][fisherC] != null) {
				catched = lake[i][fisherC].z;
				lake[i][fisherC] = null;
				aliveShark --;
				break;
			}
		}
		return catched;
	}
	
	static void moveShark() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(lake[i][j] == null) {
					continue;
				}
//				System.out.println(i + " " + j + " " + lake[i][j].d + " " + lake[i][j].s);
				int nr = 0, nc = 0;
				int left = lake[i][j].s;
				switch(lake[i][j].d) {
				case 1: // 위
					nc = j;
					if(i - 1 >= left) {
						nr = i - left;
						break;
					}
					left -= i - 1;
					if(left < R - 1) {
						nr = 1 + left;
						lake[i][j].d ^= 0b11;
						break;
					}
					left -= R - 1;
					nr = R - left;
					break;
				case 2: // 아래
					nc = j;
					if(R - i >= left) {
						nr = i + left;
						break;
					}
					left -= R - i;
					if(left < R - 1) {
						nr = R - left;
						lake[i][j].d ^= 0b11;
						break;
					}
					left -= R - 1;
					nr = 1 + left;
					break;
				case 3: // 오른쪽
					nr = i;
					if(C - j >= left) {
						nc = j + left;
						break;
					}
					left -= C - j;
					if(left < C - 1) {
						nc = C - left;
						lake[i][j].d ^= 0b111;
						break;
					}
					left -= C - 1;
					nc = 1 + left;
					break;
				case 4: // 왼쪽
					nr = i;
					if(j - 1 >= left) {
						nc = j - left;
						break;
					}
					left -= j - 1;
					if(left < C - 1) {
						nc = 1 + left;
						lake[i][j].d ^= 0b111;
						break;
					}
					left -= C - 1;
					nc = C - left;
					break;
				}
//				System.out.println(nr + " " + nc);
				if(newLake[nr][nc] != null) {
					if(lake[i][j].z > newLake[nr][nc].z) {
						newLake[nr][nc] = lake[i][j];
					}
					aliveShark --;
				}
				else {
					newLake[nr][nc] = lake[i][j];
				}
				lake[i][j] = null;
			}
		}
		Shark[][] tmp = lake;
		lake = newLake;
		newLake = tmp;
	}
	
	static int solve() {
		int answer = 0;
		for (int fisherC = 1; fisherC < C; fisherC++) {
			answer += fish(fisherC);
			moveShark();
			if(aliveShark == 0) {
				return answer;
			}
		}
		answer += fish(C);
		return answer;
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lake = new Shark[R+1][C+1];
		newLake = new Shark[R+1][C+1];
		aliveShark = M;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if(d <= 2) {
				s = s % (R*2-2);
			}
			else {
				s = s % (C*2-2);
			}
			lake[r][c] = new Shark(s, d, z);
		}
		System.out.println(solve());
		
	}
	
}
