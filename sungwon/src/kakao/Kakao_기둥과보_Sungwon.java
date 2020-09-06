package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// 아웃 오브 바운스 잡고
// x, y 값 바꿔야 한다
class Solution {
	
	static boolean[][][] map; // 기둥과 보 저장할 맵 (0 기둥, 1 보)
	static int N;
	
	public int[][] solution(int n, int[][] build_frame) {
		N = n + 3; // 맵 사이즈 
		map = new boolean[N][N][2]; // 3차원 0 기둥, 1 보
		for (int i = 0; i < build_frame.length; i++) {
			int[] cur = build_frame[i];
			int col = cur[0]; // x (col)
			int row = cur[1]; // y (row)
			int material = cur[2]; // 0기둥, 1보
			int mode = cur[3]; // 0삭제, 1설치
			if (mode == 0) { // 삭제일 경우
				boolean flag = true;   
				map[row][col][material] = false; // 삭제 가정
	ex:				for (int r = 0; r < N; r++) {	// 하나하나 따져가면서 각각 설치 가능한지 따져보고
					for (int c = 0; c < N; c++) {
						if (map[r][c][0]) { // 기둥 설치가능한지 따져보기
							if (!isPossibleToSetup(r, c, 0)) {
								flag = false; // 중간에 하나라도 설치 가능하지 않으면 바로 삭제 불가로 판단
								break ex; 
							}
						} 
						if (map[r][c][1]) { // 보가 설치가능한지 따져보기
							if (!isPossibleToSetup(r, c, 1)) {
								flag = false;
								break ex;
							}
						}
					}
				}
				
				if (!flag) { // 삭제 불가능하면 true로 바꿈 (다시 원위치)
					map[row][col][material] = true;
				}
				
				
			} else if (mode == 1) { // 설치일 경우
				System.out.print(Arrays.toString(cur) + " ");
				System.out.println(col + " " + row + " " + material + " " + mode);
				if (isPossibleToSetup(row, col, material)) {  // 설치 가능한지 확인
					map[row][col][material] = true; // 설치 가능하면 설치
				}
			}
		}
		
		//정렬 후 좌표 담기
		/*
		 * return 하는 배열은 x좌표 기준으로 오름차순 정렬하며, x좌표가 같을 경우 y좌표 기준으로 오름차순 정렬해주세요.
			x, y좌표가 모두 같은 경우 기둥이 보보다 앞에 오면 됩니다.
		 */
		ArrayList<int[]> arr = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j][0]) { // 기둥 있으면 기둥 담기
					arr.add(new int[] {j, i, 0});
				}
				if (map[i][j][1]) { // 보 있으면 보 담기 
					arr.add(new int[] {j, i, 1});
				}
			}
		}
		arr.sort(new Comparator<int[]>() {
			
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					if (o2[1] == o2[1]) {
						return o1[2] - o2[2]; // x, y좌표가 모두 같은 경우 기둥이 보보다 앞
					}
					return o1[1] - o2[1]; // x같을 경우 y좌표 기준 오름차순
				}
				return o1[0] - o2[0]; // x좌표 기준으로 오름차순
			}
		});
		
		int[][] answer = new int[arr.size()][3];
		for (int i = 0; i < arr.size(); i++) {
			answer[i] = arr.get(i);
		}
	    return answer;
	}
	private boolean isPossibleToSetup(int row, int col, int material) {
		// row == y( 높이 ), col == x( 밑변 );
		if (material == 0) { // 기둥 설치
			if (row == 0) { // 바닥인경우 기둥 설치 가능
				return true;
			} else if (row - 1 >= 0 && map[row - 1][col][0]) { // 바닥이 아닌데 설치하려는 자리 밑에 기둥이 있으면 설치 가능 
				return true;
			} else if (col - 1 >= 0 && (map[row][col - 1][1] || map[row][col][1])) { // 보의 한쪽 끝 위 면 설치 가능
				return true;
			}
		} else if (material == 1) { // 보 설치
			if (row - 1 >= 0 && (map[row - 1][col][0] || map[row - 1][col + 1][0])) { // 한쪽 끝이 기둥 위치면 보 설치 가능
				return true;
			} else if (col - 1 >= 0 && map[row][col - 1][1] && map[row][col + 1][1]) { // 다른 보와 동시 연결 
				return true;
			}
		}
		return false;
	} 
}
