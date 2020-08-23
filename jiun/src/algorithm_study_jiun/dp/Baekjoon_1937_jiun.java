import java.util.Scanner;

public class Baekjoon_1937_jiun. {
   public static int[][] map;
   public static int[][] day;
   public static int n;
   public static int[] dx = { 1, 0, -1, 0 };
   public static int[] dy = { 0, 1, 0, -1 };

   static int dp(int r, int c) {
      if (day[r][c] > 0) // 이미 방문했던 곳이면
         return day[r][c]; // 저장된 값 리턴

      day[r][c] = 1; // 처음 방문한 위치면 1일 저장
      for (int i = 0; i < 4; i++) { // 상하좌우 탐색
         int nr = r + dx[i];
         int nc = c + dy[i];

         if (nr < 0 || nc < 0 || nr >= n || nc >= n) // 범위 외
            continue;

         if (map[r][c] < map[nr][nc]) { // 다음 위치로 이동   할 수 있으면
            day[r][c] = Math.max(day[r][c], dp(nr, nc) + 1); // 배열값 갱신
            System.out.println(Arrays.deepToString(day));
         }
      }

      return day[r][c];
   }
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      n = sc.nextInt();
      map = new int[n][n];
      day = new int[n][n];

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            map[i][j] = sc.nextInt();
         }
      } // ---input end-- 

      int max = -1;
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            max = Math.max(max, dp(i, j));
         }
      }

      System.out.println(max);
   }// end of main
}// end of class
