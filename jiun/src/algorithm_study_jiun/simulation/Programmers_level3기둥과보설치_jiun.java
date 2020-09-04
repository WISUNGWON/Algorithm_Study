// https://programmers.co.kr/learn/courses/30/lessons/60061
// 구조물 종류 - 0: 기둥(위로 설치), 1: 보(오른쪽으로 설치)
// 동작  - 0: 삭제, 1: 설치
public class Solution Programmers_level3기둥과보설치_jiun{
	private boolean[][] vertical;
	private boolean[][] horizontal;

	public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        int cnt = 0;
        vertical = new boolean[n+2][n+2];// 기둥
        horizontal = new boolean[n+2][n+2];// 보
        
        //input 동작하기
        for (int[] cur : build_frame){
			int x = cur[0]+1;
			int y = cur[1]+1;
			int type = cur[2];
			int cmd = cur[3];
			
			switch (cmd) {
			
			case 0:// 0: 삭제 - 1. 현재 위치를 임시로 삭제  2. 기둥과 보 위치를 만족하는지 확인. 3.  만족하지 않는다면 기존 설치 위치 복구.
				if(type == 0) {//0: 기둥(위로 설치)
					vertical[x][y] = false;
					cnt--;
					if(!canDestruct(n)) {// 삭제할 수 없으면 원상복구
						cnt++;
						vertical[x][y]=true;
					}
				}
				else if(type == 1) {// 1: 보(오른쪽으로 설치)
					horizontal[x][y]=false;
					cnt--;
					if(!canDestruct(n)) {
						cnt++;
						horizontal[x][y]=true;
					}
				}
				break;

			case 1://1: 설치 - 기둥,보를 설치할 수 있는 경우에만 증가.
				if(type == 0 && canBuildVertical(x,y,n)) {//0: 기둥(위로 설치)
					vertical[x][y]=true;
					cnt++;
				}
				else if(type == 1 && canBuildHorizontal(x,y,n)) {// 1: 보(오른쪽으로 설치)
					horizontal[x][y]=true;
					cnt++;
				}
				break;
			} 
			
		}// 명령어 실행 종료
        
		answer = new int[cnt][3];
		int k=0;
		for (int i = 1; i <= n+1; i++) {//answer 구하기
			for (int j = 1; j <= n+1; j++) {
				if(vertical[i][j]) {
					answer[k][0] = i-1;
					answer[k][1] = j-1;
					answer[k++][2]= 0; // 기둥
				}
				if(horizontal[i][j]) {
					answer[k][0] = i-1;
					answer[k][1] = j-1;
					answer[k++][2]= 1; // 보
				}
			}
		}

        return answer;
    }// end of class
	
	private boolean canDestruct(int n) {
		for (int i = 1; i <= n+1; i++) {
			for (int j = 1; j <= n+1; j++) {
				if(vertical[i][j] && !canBuildVertical(i, j,n)) { // 남아있는 기둥에서 기둥 설치조건을 만족하지 않으면 기둥을 못세움
					return  false;
				}
				if(horizontal[i][j] && !canBuildHorizontal(i, j,n))//남아있는 보에서 설치조건을 만족하지 않으면 보를 못세움
					return false;
				
			}
		}
		return true;
	}

	/** 현재 위치에 기둥을 세울 수 있으면 true, 아니면 false
	 * 경우 1. 바닥 위에 세우는 경우 (y=0)
	 * 경우 2. 기둥 위에 또 기둥을 세우는 경우 (vertical[x][y-1]이 true)
	 * 경우 3. 보의 한쪽 끝의 있는 경우 
	 * 			3.1. 보의 왼쪽 끝에 세우는 경우 (horizontal[x-1][y]가 true)
	 * 			3.2. 보의 오른쪽 끝에 세우는 경우 (horizontal[x][y]가 true)
	 * 
	 * 위 경우 중 만족하는 것이 있으면 true를 리턴한다.*/
	private boolean canBuildVertical(int x, int y, int n) {
		return y == 1 || vertical[x][y-1] || horizontal[x-1][y] ||horizontal[x][y];
	}

	/** 현재 위치에 보를 세울 수 있으면 true, 아니면 false
	 * 경우 1. 한쪽 끝 부분이 기둥 위에 있는경우
	 * 		  1.1. 기둥 위에 보의 시작점이 있는 경우 (vertical[x][y-1])
	 * 		  1.2. 기둥 위에 보의 끝점이 있는 경우 (vertical[x+1][y-1])
	 * 경우 2. 양쪽 끝 부분이 다른 보와 동시에 연결된 경우(양 옆에 다른 보가 있는 경우)
	 * 		 (horizontal[x-1][y] && horizontal[x+1][y])
	 * 위 경우 중 만족하는 것이 있으면 true를 리턴한다.*/
	private boolean canBuildHorizontal(int x, int y, int n) {
		return vertical[x][y-1] ||vertical[x+1][y-1] ||(horizontal[x-1][y] && horizontal[x+1][y]);
	}
	
//	public static void main(String[] args) {
//		int arr[][] = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
//		System.out.println(Arrays.deepToString(new Solution().solution(5, arr)));
//	}

}// end of class
