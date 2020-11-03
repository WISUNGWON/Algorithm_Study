package algo_1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beakjoon_주사위윷놀이_Sungwon2 {

	private static int[][] lines = { 
			{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 },
			{ 10, 13, 16, 19 }, 
			{ 30, 28, 27, 26 }, 
			{ 20, 22, 24, 25, 30, 35, 40 } };
	
	private static int[] dices;
	private static int[] moves; 
	private static Horse[] horses;
	private static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dices = new int[10];
		for (int i = 0; i < 10; i++) {
			dices[i] = Integer.parseInt(st.nextToken());
		} // ------------------ 입력부 -------------------

		horses = new Horse[4];
		for (int i = 0; i < 4; i++) {
			horses[i] = new Horse(i);
		}

		moves = new int[10];
		max = Integer.MIN_VALUE;
		solution(0);
		
		System.out.println(max);
	} // end of main

	private static void solution(int step) {

		if (step == 10) { // move[]에 이동할 말의 index를 주사위 시행(10개)만큼 다담았으면 이동 시행

			// 말 초기화!!
			for (int i = 0; i < 4; i++) {
				horses[i].area = 0;
				horses[i].pos = 0;
			}
			
			int score = 0;
			int h_area = 0, h_pos;
			for (int i = 0; i < 10; i++) {
				boolean isPossible = move(dices[i], horses[moves[i]]); // i번째에 해당 house로 해당 dices수만큼 move해보기
				if (isPossible) {
					h_area = horses[moves[i]].area;
					h_pos = horses[moves[i]].pos;
					
					// line 3, index(pos) 7을 도착 지점
					if (h_area == 3 && h_pos == 7) {
						continue;
					}
					else {
						score += lines[h_area][h_pos];
					}
				} else {
					return;
				}
			}
			if (max < score) {
				max = score;
			}
			return;
		}
		
		// moves[step]: step번째 주사위가 나왔을 때, 이동할 말의 index
		// 4마리의 모든 말들을 주사위의 시행마다 다 이동시키기 (4(num) ^ 10(step))
		for (int h = 0; h < 4; h++) {
			moves[step] = h;
			solution(step + 1);
		}
	}
	
	private static boolean move(int diceNum, Horse horse) {
		int pos = horse.pos;
		int area = horse.area;
		
		int next = pos + diceNum;
		int nArea = area;
		if (area == 0) {
			if (next == 5) { // 파란색을 밟을 경우 nArea로 구역 교체
				nArea = 1;
				next = 0;
			} else if (next == 10) {
				nArea = 3;
				next = 0;
			} else if (next == 15) {
				nArea = 2;
				next = 0;
			} else if (next == 20) {
				nArea = 3;
				next = 6;
			} else if (next > 20) {
				nArea = 3;
				next = 7;
			}
		} else if (area == 1) {
			if (next > 3) {
				nArea = 3;
				next--;
				if (next > 6) {
					next = 7;
				}
			}
		} else if (area == 2) {
			if (next > 3) {
				nArea = 3;
				next--; // area 2구역이 3구역보다 1칸 많으므로 next--해주기
				if (next > 6) { // area 3구역에서 next가 6보다 크면 도착점에 도착함
					next = 7;
				}
			}
		} else if (area == 3) {
			if (next > 6) {
				next = 7;
			}
		}
		
		// 도착점에 도착
		if (nArea == 3 && next == 7) {
			horse.area = nArea;
			horse.pos = next;
			return true;
		}

		// 중복 처리
		if (dupCheck(horse.num, nArea, next)) {
			// 시행이 끝났으면 말 위치 바꿔주기
			horse.area = nArea;
			horse.pos = next;
			return true;
		} else {
			return false;
		}
	}

	private static boolean dupCheck(int num, int nArea, int next) {
		for (int i = 0; i < 4; i++) {
			if (num == i)
				continue; // 말번호(num)와 같은 시행은 넘기기
			if (horses[i].area == 3 && horses[i].pos == 7)
				continue; // 도착점에 있는 말들은 더이상 이동하지 않음
			if (horses[i].area == 0 && horses[i].pos == 0) {
				continue; // 시작점에 있는 말들은 고려할 필요 없음
			}
			if (horses[i].area == nArea && horses[i].pos == next) {
				return false; // 내가 갈 곳에 이미 다른 말이 놓여져 있는 경우 (시행 X, 문제조건에서 갈 수 없다고 나왔으므로) 
			}
		}
		return true;
	}

	private static class Horse {
		int area; // 윷놀이판 배열로 나눈 4가지 구역에서 현재 말의 구역 위치 (0 ~ 3)
		int pos; // 구역에서 몇 번째 위치하는지 
		int num; // 말의 번호 (0 ~ 3)

		public Horse(int num) {
			this.num = num;
			this.area = 0;
			this.pos = 0;
		}

	}
}