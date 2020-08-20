package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15644_Junhyeong {
	
	final static int[] dr = {0, 1, 0, -1};
	final static int[] dc = {1, 0, -1, 0};
	final static char[] dirs = {'R', 'D', 'L', 'U'};
	final static int RIGHT = 0;
	final static int DOWN = 1;
	final static int LEFT = 2;
	final static int UP = 3;
	
	static int N, M;
	static char[][] map;
	static int redR, redC, blueR, blueC;	// Position of Balls
	
	static char[] answer = new char[10];
	static int answerCount = 11;
	static String realAnswer = "";
	
	static int oneMove(int d) { // Move Balls in a direction
		int nr, nc;
		while(true) { // Move Blue Ball
			nr = blueR + dr[d];
			nc = blueC + dc[d];
			if(map[nr][nc] == '.') {
				if(nr == redR && nc == redC) { // Blue Ball met Red Ball
					while(map[redR + dr[d]][redC + dc[d]] != '#') {
						redR += dr[d];
						redC += dc[d];
						if(map[redR][redC] == 'O') { // If Red Ball got into hole first, Blue will follow
							return -1;
						}
					}
					blueR = redR - dr[d];
					blueC = redC - dc[d];
					return 0;
				}
				else { // Blue Ball moves to empty space
					blueR = nr;
					blueC = nc;
				}
			}
			else if(map[nr][nc] == 'O'){ // Blue Ball got into hole
				return -1;
			}
			else { // Blue Ball got into wall
				break;
			}
		} // Blue move end
		
		while(true) { // Move Red Ball and if it meets blue ball, it is not moving
			nr = redR + dr[d];
			nc = redC + dc[d];
			if(map[nr][nc] == '.') {
				if(nr == blueR && nc == blueC) { // Red Ball met Blue Ball
					break;
				}
				else { // Red Ball moves to empty space
					redR = nr;
					redC = nc;
				}
			}
			else if(map[nr][nc] == 'O'){ // Red Ball got into hole
				return 1;
			}
			else { // Red Ball got into wall
				break;
			}
		} // Red move end
		
		return 0; // Nothing happened, just balls moving
	}
	
	static void dfs(int count) {
		int cbr = blueR, cbc = blueC, crr = redR, crc = redC;
		int state;
		for (int i = 0; i < 4; i++) {
			blueR = cbr;
			blueC = cbc;
			redR = crr;
			redC = crc;
			answer[count] = dirs[i];
			state = oneMove(i);
			if(state < 0) {
				continue;
			}
			else if(state > 0) {
				if(count + 1 < answerCount) {
					realAnswer = String.valueOf(answer, 0, count + 1);
					answerCount = count + 1;
				}
				continue;
			}
			else {
				if(count + 1 == 10) {
					continue;
				}
				else {
					dfs(count+1);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		int i, j;
		
		for (i = 0; i < N; i++) {
			map[i] = br.readLine().trim().toCharArray();
			for (j = 0; j < M; j++) {
				if(map[i][j] == 'B') {
					blueR = i;
					blueC = j;
					map[i][j] = '.';
				}
				else if(map[i][j] == 'R') {
					redR = i;
					redC = j;
					map[i][j] = '.';
				}
			}
		}
		
		dfs(0);
		
		if(answerCount > 10) {
			System.out.println(-1);
		}
		else {
			System.out.println(answerCount);
			System.out.println(realAnswer);
		}
	}
	
	
}
