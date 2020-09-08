package simulation;
import java.util.ArrayList;
import java.util.Arrays;

public class Kakao2020_5_Junhyeong {
	
	static boolean validate(int n, int[][] map, int i, int j, int type) {
		// 존재하지 않으면 그냥 true
		if((map[i][j] & type) == 0) {
			return true;
		}
		// 존재하면 제대로 세워져있는지 확인
		if(type == 1) { // 밑에 기둥이 있거나 왼쪽이나 같은 자리에 보가 있어야한다 또는 바닥이거나
			if(i == 0) {
				return true;
			}
			if((map[i][j] & 2) > 0 || // 밑 오른쪽 보
					(i - 1 >= 0 && (map[i-1][j] & 1) > 0 ) || // 밑 기둥
					(j - 1 >= 0 && (map[i][j-1] & 2) > 0) // 밑 왼쪽 보
					) {
				return true;
			}
		}
		else { // 양쪽 끝에 보가 있거나 양쪽 밑 중 하나에 기둥이 있어야한다
			if(i == 0) {
				return false;
			}
			if( (j - 1 >= 0 && j + 1 <= n && (map[i][j-1] & 2) > 0 && (map[i][j+1] & 2) > 0) || // 양쪽 끝에 보
					(i - 1 >= 0 && (
							(map[i-1][j] & 1) > 0 || // 왼쪽 밑 기둥 
							(j + 1 <= n && (map[i-1][j+1] & 1) > 0) // 오른쪽 밑 기둥
							))
					) {
				return true;
			}
		}
		return false;
	}
	
	public static int[][] solution(int n, int[][] build_frame) {
		/** 1이면 (i, j) 위치에서 (i+1, j)로 한칸 기둥, 2면 (i, j)위치에서 (i, j+1)위치로 한칸 보, 3이면 둘다*/
		int[][] map = new int[n+1][n+1];
		for (int[] frame : build_frame) {
			boolean isDelete = (frame[3]==0);
			int type = frame[2] + 1;
			int i = frame[1];
			int j = frame[0];
			if(isDelete) { // 삭제하는 경우
				if(type == 1) { // 기둥 삭제
					// 기둥을 삭제하면 위에 있는 기둥, 기둥 위에 놓여져있는 양쪽의 보가 제대로 놓여져 있는지 확인해야 한다
					// 일단 지우고 주위의 것들이 제대로 되는지 확인.
					map[i][j] ^= type;
					if(i + 1 <= n && validate(n, map, i+1, j, 1) && // 위의 기둥
							validate(n, map, i+1, j, 2) && // 위 오른쪽으로 뻗은 보
							(j - 1 < 0 || validate(n, map, i+1, j-1, 2))) {
						// 다 제대로 되어있으니 할 일 없다
					}
					else { // 하나라도 잘못되어있으면
						map[i][j] |= type; // 다시 세운다
					}
				}
				else { // 보 삭제
					// 보를 삭제하면 보의 양 끝에 있는 기둥, 보의 양쪽 끝에 연결된 보가 제대로 존재할 수 있는지 확인해야 한다. 
					// 일단 지우고 주위의 것들이 제대로 되는지 확인.
					map[i][j] ^= type; // 일단 지워보기
					if(validate(n, map, i, j, 1) && // 왼쪽 위 기둥
							(j + 1 > n || (validate(n, map, i, j+1, 1) && validate(n, map, i, j+1, 2))) && //오른쪽 위 기둥과 오른쪽 보
							(j - 1 < 0 || validate(n, map, i, j-1, 2))) { // 왼쪽 보
						//다 제대로 되어있으니 할 일 없다
					}
					else { // 하나라도 잘못되어있으면
						map[i][j] |= type; // 다시 세운다
					}
				}
			}
			else { // 생성하는 경우
				map[i][j] |= type; // 일단 지어보는데
				if(!validate(n, map, i, j, type)) { // 만약 제대로된 상태가 아니면
					map[i][j] ^= type; // 다시 지운다
				}
			}
		}
		
		
		ArrayList<int[]> result = new ArrayList<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if((map[j][i] & 1) > 0) {
					result.add(new int[] {i, j, 0});
				}
				if((map[j][i] & 2) > 0) {
					result.add(new int[] {i, j, 1});
				}
			}
		}
		int[][] resultArr = new int[result.size()][];
		result.toArray(resultArr);
		return resultArr;
	}
	
	public static void main(String[] args) {
		int n = 5;
//		int[][] build_frame = new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
		int[][] build_frame = new int[][] {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
//		int[][] build_frame = new int[][] {{0, 0, 0, 1}, {0, 1, 0, 1}, {0, 1, 0, 0}};
		int[][] result = solution(n, build_frame);
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
	}
}
