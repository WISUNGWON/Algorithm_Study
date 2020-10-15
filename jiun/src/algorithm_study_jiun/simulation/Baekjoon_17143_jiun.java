import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_17143_jiun {
	static int R, C, sum = 0;
	static Shark[][] map;
	static List<Shark> list = new ArrayList<Shark>();
	static int[] dr = { 0, -1, 1, 0, 0 };// 1:북, 2: 남, 3: 동, 4: 서
	static int[] dc = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// input start
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new Shark[R + 1][C + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			map[r][c] = shark;
			list.add(shark);

		} // input end

		for (int i = 1; i < C + 1; i++) {
			catchShark(i);// 1. 상어잡기
			moveShark();// 2. 이동
			arrangeMap(); // 3. map 갱신 (같은 위치일 땐 더 큰 상어를 남기기)

		} // 열 끝에 도달하면 종료
		System.out.println(sum);
		return;
	}// main

	/** 현재 열의 상어 잡기 */
	private static void catchShark(int col) {
		Shark catched = null;
		for (int i = 1; i < R + 1; i++) {
			if (map[i][col] != null) {
				catched = map[i][col];
				break;
			}
		} // select shark

		if (catched != null) {// 잡을 상어가 있으면
			map[catched.r][catched.c] = null;
			sum += catched.z;
			list.remove(catched);
		} // catch shark
	}// end of catchShark

	/** 상어 움직이기 */
	private static void moveShark() {
		for (Shark shark : list) {
			int end = C;
			if (shark.d < 3) {
				end = R;
			}
			int goback = 2 * (end - 1); 
			int speed = shark.s % goback; // 만큼만 움직이면 됨.

			for (int i = 0; i < speed; i++) {
				if (shark.d < 3) {
					// 위, 아래
					if ((shark.r == end && shark.d == 2) || (shark.r == 1 && shark.d == 1)) {
						shark.d = changeDir(shark.d);
					}
					shark.r += dr[shark.d];
				} else {
					if ((shark.c == end && shark.d == 3) || (shark.c == 1 && shark.d == 4)) {
						shark.d = changeDir(shark.d);
					}
					shark.c += dc[shark.d];
				}
			} // 속력만큼 이동
		} // for
	}// end of moveShark

	private static int changeDir(int d) {
		if (d == 1)
			return 2;
		else if (d == 2)
			return 1;
		else if (d == 3)
			return 4;
		else if (d == 4)
			return 3;
		return -1;
	}

	static int arrangeMap() {
		map = new Shark[R + 1][C + 1];
		int size = list.size();
		for (int i = size - 1; i >= 0; i--) {
			Shark shark = list.get(i);
			if (map[shark.r][shark.c] == null) {
				map[shark.r][shark.c] = shark;
			} else {
				if (map[shark.r][shark.c].z > shark.z) {
					list.remove(shark);
				} else {
					list.remove(map[shark.r][shark.c]);
					map[shark.r][shark.c] = shark;
				}
			}
		}
		return 0;
	} // end of survive()

	static class Shark { // implements Comparable<Shark> {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}// class
