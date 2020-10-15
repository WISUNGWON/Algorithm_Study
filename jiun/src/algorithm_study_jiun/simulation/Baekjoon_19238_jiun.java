import java.io.*;
import java.util.*;
public class Baekjoon_19238_jiun{
    static int n,m,fuel;
    static int map[][];
    static boolean visited[][];
    static int driver_x,driver_y;
    static int dx[] = {0,0,1,-1};
    static int dy[] = {1,-1,0,0};
    static ArrayList<Node>guest = new ArrayList<>();
    static ArrayList<Info>cust_list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] t = br.readLine().split(" ");
        n = Integer.parseInt(t[0]);
        m = Integer.parseInt(t[1]);
        fuel = Integer.parseInt(t[2]);
        
        map = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];
        
        for(int i=1; i<=n; i++) {
            String[] map_input = br.readLine().split(" ");
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(map_input[j-1]);
                if(map[i][j]==1) {
                    map[i][j]=-1;
                }
            }
        }
        String[] tt = br.readLine().split(" ");
        driver_x = Integer.parseInt(tt[0]);
        driver_y = Integer.parseInt(tt[1]);
        
        for(int i=0; i<m; i++) {
            String[] a = br.readLine().split(" ");
            int start_x = Integer.parseInt(a[0]);
            int start_y = Integer.parseInt(a[1]);
            int end_x = Integer.parseInt(a[2]);
            int end_y = Integer.parseInt(a[3]);
            cust_list.add(new Info(start_x,start_y,end_x,end_y));
            map[start_x][start_y] = i+1;
        }
        while(true) {
            
            if(cust_list.size()==0) {
                System.out.println(fuel);
                return;
            }
            guest.clear();
            visited = new boolean[n+1][n+1];
            get_cust(driver_x,driver_y);    //가장 가까운 손님 찾기
            if(guest.size()==0) {               //벽에 의해서 못가거나 이런경우 (승객을 못태울 경우)
                System.out.println(-1);
                return ;
            }
            Node a = guest.get(0);
            map[a.x][a.y] = 0;
            fuel-=a.dist;   // 기사 현재위치에서 승객을 데리러 가는 연료 소비
            if(fuel<0) {            // 연료가 없으면 끝
                System.out.println(-1);
                return ;
            }
            int dist = 0;
            //승객의 위치에서 승객의 목적지까지 가는 과정
            visited = new boolean[n+1][n+1];
            for(int i=0; i<cust_list.size(); i++) {
                Info info = cust_list.get(i);
                if(info.start_x == a.x && info.start_y == a.y) {
                    dist = get_dist(info.start_x,info.start_y,info.end_x,info.end_y);
                        if(dist == -1) {
                            System.out.println(-1);
                            return;
                        }
                        driver_x = info.end_x;
                        driver_y = info.end_y;
                        cust_list.remove(info);
                        break;
                    }
                }
            fuel-=dist;
            if(fuel<0) {
                System.out.println(-1);
                return ;
            }
            fuel+=dist*2;
            }
        }
    public static int get_dist(int x, int y,int end_x, int end_y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y,0));
        while(!q.isEmpty()) {
            Node a= q.poll();
            if(a.x == end_x && a.y== end_y) {
                return a.dist;
            }
            for(int i=0; i<4; i++) {
                int nx = a.x+dx[i];
                int ny = a.y+dy[i];
                if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny]!=-1) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx,ny,a.dist+1));
                }
            }
        }
        return -1;
    }
    public static void get_cust(int x, int y) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(x,y,0));
        
        while(!q.isEmpty()) {
            Node a= q.poll();
            if(map[a.x][a.y]>=1) {
                guest.add(new Node(a.x,a.y,a.dist));
                break;
            }
            for(int i=0; i<4; i++) {
                int nx = a.x+dx[i];
                int ny = a.y+dy[i];
                if(isRange(nx,ny) && map[nx][ny]!=-1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx,ny,a.dist+1));
                }
            }
        }
    }
    public static boolean isRange(int x, int y) {
        if(x>=1 && y>=1 && x<=n && y<=n) {
            return true;
        }
        return false;
    }
}
class Info{
    int start_x,start_y,end_x,end_y;
    Info(int start_x, int start_y, int end_x, int end_y){
        this.start_x = start_x;
        this.start_y= start_y;
        this.end_x = end_x;
        this.end_y = end_y;
    }
}
class Node implements Comparable<Node>{
    int x,y,dist;
    Node(int x, int y, int dist){
        this.x=x;
        this.y=y;
        this.dist=dist;
    }
    public int compareTo(Node o) {
        if(this.dist!=o.dist) {
            return this.dist-o.dist;
        }
        else {
            if(this.x != o.x) {
                return this.x-o.x;
            }
            else {
                return this.y-o.y;
            }
        }
    }
}
