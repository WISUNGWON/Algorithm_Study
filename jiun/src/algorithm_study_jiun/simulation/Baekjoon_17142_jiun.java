// https://www.acmicpc.net/problem/17142
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3 {
    private static int n, m, count = 0;
    static int min = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[] visit;//방문표시
    private static ArrayList<Virus> virus = new ArrayList<>();//바이러스 리스트
    private static final int[] dc = {0, 0, -1, 1};
    private static final int[] dr = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new Virus(i, j));//바이러스
                if (map[i][j] == 0) count++;// 빈공간
            }
        }

        visit = new boolean[virus.size()];

        if (count != 0) comb(0, 0);//바이러스 m개 고르기
        else min = 0;
        System.out.println((min == Integer.MAX_VALUE) ? -1 : min);
    }

    /** 바이러스 m개를 고르는 조합*/
    private static void comb(int depth, int start) {
        if (depth == m) {
            int[][] copyMap = copy(); // 맵 저장
            bfs(copyMap, count);// bfs탐색(바이러스 확산)
            return;
        }

        for (int i = start; i < virus.size(); i++) {//조합
            visit[i] = true;
            comb(depth + 1, i + 1);
            visit[i] = false;
        }
    }

    /** 바이러스 확산 bfs. 
     * 이 때 활성화 바이러스가 비활성화 바이러스를 만날 때 처리 주의하기. */
    private static void bfs(int[][] map, int count) {
        Queue<Virus> queue = new LinkedList<>();
        for (int i = 0; i < virus.size(); i++) {
            if (visit[i]) queue.add(virus.get(i));//m개의 바이러스 선택
        }

        int time = 0, nr, nc;
        while (!queue.isEmpty()) {
            if(min <= time) break; // 가지치기

            int len = queue.size();
            for (int t = 0; t < len; t++) { // 선택한 바이러스 꺼내기
                Virus cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    nr = cur.r + dr[i];
                    nc = cur.c + dc[i];
                    if (!isRange(nr, nc) || map[nr][nc] == 1 || map[nr][nc] == 2) continue; // 벽이나 비활성 바이러스는 무시

                    if (map[nr][nc] == 0) count--; // 퍼뜨릴 수 있으면 
                    map[nr][nc] = 2;//바이러스로 변경.
                    queue.add(new Virus(nr, nc));
                }
            }
            time++;

            if (count == 0) {
                min = time;
                return;
            }
        }
    }

    private static int[][] copy() {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, result[i], 0, n);
        }
        
        for (int i = 0; i < virus.size(); i++) {
            if (!visit[i]) {
                Virus index = virus.get(i);
                result[index.r][index.c] = 3; // 선택되지 않은 바이러스는 3으로
            }
        }
        return result;
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }
}

class Virus { // 바이러스 클래스
    int c,r; // 열,행

    Virus(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
