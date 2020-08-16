package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
public class Beakjoon_17472_Sungwon {
    static int N, M;
    private static int[][] map;
    private static boolean[][] isVisited;
    private static int landNum; // 섬의 고유 번호
    private static int bLen, bAns, bNum; // 다리 길이 및 총 다리 길이, 만들어진 다리 개수
    private static int[] parents, rank;
    private static PriorityQueue<Bridge1> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // row
        M = Integer.parseInt(st.nextToken()); // col
        map = new int[N][M];
        isVisited = new boolean[N][M];
        landNum = 2;
        pq = new PriorityQueue<Bridge1>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 각각 구별하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !isVisited[i][j]) {
                    markLandBfs(i, j);
                }
            }
        }
        isVisited = new boolean[N][M]; // makeBridge에서 쓸 방문 초기화
         //다리 길이 카운트 
        // 다리 설치하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !isVisited[i][j]) { // 섬이고 방문하지 않았으면 다리 설치 진행
                    for (int dir = 0; dir < dc.length; dir++) {
                        int nr = i + dr[dir];
                        int nc = j + dc[dir];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                            bLen = 0;
                            makeBridgeDfs(i, j, dir, map[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println(countMinBridge()); // 답 출력
    } // end of main
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1}; // 상하좌우
    /** 지정된 방향이 있다면 그 방향으로만 Dfs */
    private static void makeBridgeDfs(int row, int col, int dir, int start) {
        int nr = row + dr[dir];
        int nc = col + dc[dir];
        if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
            bLen++;
            makeBridgeDfs(nr, nc, dir, start);
        } else if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && bLen >= 2) { // 다른 섬 도착
            bNum++;
            pq.add(new Bridge1(start, map[nr][nc], bLen)); // 만들어진 다리 정보 저장
            return;
        } 
    }
     /** 섬을 각각 구별해주는 메서드 (2, 3, 4 ...) */
    private static void markLandBfs(int row, int col) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[] {row, col});
        isVisited[row][col] = true;
        boolean isOnlyOne = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < dr.length; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1) {
                    map[nr][nc] = landNum;
                    q.offer(new int[] {nr, nc});
                    isOnlyOne = false;
                    isVisited[nr][nc] = true;
                }
            }
        }
        if (isOnlyOne) {
            map[row][col] = landNum;
        }
        landNum++;
    }
    /** 크루스칼 알고리즘 사용해서 최소 간선 찾은 후 답 출력 */
    private static int countMinBridge () { 
        parents = new int[landNum];
        rank = new int[landNum];
        for (int i = 2; i < landNum; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < bNum; i++) {
            Bridge1 b = pq.poll(); // 다리가 짧은 개수를 우선적으로 뺌
            int start = b.s;
            int end = b.e;
            if (findSet(start) == findSet(end)) { // 사이클이 생기면 넘어감
                continue;
            }
            unionSet (start, end);
            bAns += b.v;
        }
        int cnt = 0;
        for (int i = 2; i < landNum; i++) {
            if (parents[i] == i) {
                cnt++;
            }
        }
        if (cnt == 1) {
            return bAns;
        } else {
            return -1;
        }
    }
    /** */
    private static int findSet (int i) {
        if (parents[i] == i) {
            return i;
        } else {
            return parents[i] = findSet(parents[i]);
        }
    }
    /** */
    private static void unionSet (int i, int j) {
        i = findSet(i);
        j = findSet(j);
        if (i == j) {
            return;
        }
        if (rank[i] < rank[j]) {
            parents[i] = j;
        } else {
            parents[j] = i;
            if (rank[i] == rank[j]) {
                rank[i]++;
            }
        }
    }
} // end of class
/** */
class Bridge1 implements Comparable<Bridge1> {
    int s, e, v;
    public Bridge1(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }
    public int compareTo(Bridge1 b) { // 다리의 크기에 따라 오름차순 정렬
        return b.v >= this.v ? -1 : 1;
    }
}
