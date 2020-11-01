package simulation;

import java.util.Scanner;

public class Baekjoon_17825_Junhyeong {

	/*
	 * 5번 -> 22번 (그 칸에서만)
	 * 10번 -> 25번 (그 칸에서만)
	 * 15번 -> 27번 (그 칸에서만)
	 * 24번 -> 30번 (무조건)
	 * 26번 -> 30번 (무조건)
	 * 29번 -> 30번 (무조건)
	 * 32번 -> 20번 (무조건)
	 * 21번: 끝(이동불가)
	 */
	final static int END = 21;
	final static int[] scoreList = {0, 2, 4, 6, 8,		// 0
								10, 12, 14, 16, 18, // 5
								20, 22, 24, 26, 28, // 10
								30, 32, 34, 36, 38,	// 15
								40, 0, 13, 16, 19,	// 20
								22, 24, 28, 27, 26,	// 25
								25, 30, 35};		// 30
	
	static int[] dice = new int[10]; // 주사위 눈 10개
	static int[] pos = new int[4]; // 말 4개의 위치
	static int maxScore = 0;
	
	static boolean exists(int idx) {
		if(idx == END) {
			return false;
		}
		for (int i = 0; i < pos.length; i++) {
			if(pos[i] == idx) return true;
		}
		return false;
	}
	
	static int move(int fromIdx, int num) {
		int result = fromIdx;
		int left = num;
		switch(fromIdx) { // 시작할때 특수 케이스
		case END:
			return END;
		case 5:
			result = 22;
			left--;
			break;
		case 10:
			result = 25;
			left--;
			break;
		case 15:
			result = 27;
			left--;
			break;
		}
		while(left-- > 0) { // 한 칸씩
			switch(result) {
			case 24:
				result = 30;
				break;
			case 26:
				result = 30;
				break;
			case 32:
				result = 20;
				break;
			case END:
				return END;
			default:
				result ++;
			}
		}
		return result;
	}
	
	static void dfs(int depth, int score) {
		if(depth == 10) {
			maxScore = Math.max(maxScore, score);
			return;
		}
		for (int i = 0; i < pos.length; i++) {
			int prevPos = pos[i];
			int nextPos = move(pos[i], dice[depth]);
			if(!exists(nextPos)) {
				pos[i] = nextPos;
				dfs(depth + 1, score + scoreList[nextPos]);
				pos[i] = prevPos;
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			dice[i] = sc.nextInt();
		}
		sc.close();
		
		pos[0] = move(0, dice[0]);
		
		dfs(1, scoreList[pos[0]]);
		
		System.out.println(maxScore);
		
	}
	
}
