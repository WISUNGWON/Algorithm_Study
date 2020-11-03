// https://www.acmicpc.net/problem/17825

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
public class Main {
	private static int max = Integer.MIN_VALUE;
	private static int N = 10;
	private static int[] cmd;
	private static int[] score = {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0,13,16,19,25,30,35,22,24,28,31,32};// 각 지점의 점수, 골 지점: 21번
	private static int[][] tagger; //말 4개의 위치 {path, 위치}
	private static int[][] path = {
			{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21},
			{0,22,23,24,25,26,27,20,21},
			{0,28,29,25,26,27,20,21},
			{0,30,31,32,25,26,27,20,21}
	};// 4가지 경로(발판번호) ==> score[발판번호] = 얻은 점수
	// ex: score[22] = 13(점)
	private static boolean[] selected; // 해당 위치에 말이 있는지 검사.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cmd = new int[N];
		//ex: 1 2 3 4 1 2 3 4 1 2
		for (int i = 0; i < 10; i++) {
			cmd[i]=Integer.parseInt(st.nextToken());
		}// input end
		
		selected = new boolean[33]; 
		tagger = new int[4][2];
		for (int i = 0; i < 4; i++) {
			tagger[i][0]=tagger[i][1]=0; // 4개의 말 모두 출발점에 있음.
		}
		// 말 4개를 10개의 열에 배치 (중복순열, 4의 10승)
		reComb(0,0,0);// {고른 말의 갯수, 현재 위치,현재까지 점수}
		System.out.println(max);
	}// end of main
	
	private static void reComb(int depth, int index, int sum) {// {고른 말의 갯수, 현재 위치, 현재까지 점수}
		if(depth == N) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {// 1. i번째 말 선택
			// 2. 말 위치 확인
			int cur_path = tagger[i][0];
			int pos = tagger[i][1];
			if(pos == 21)// 골 위치면 이동못함.
				continue;
			
			// 3. 말 이동
			int next = pos + cmd[depth]; // 다음위치
			if(selected[path[i][next]]) continue; // 3.1. 다른 말이 있으면 이 말을 선택하지 않는다.
			if(cur_path == 1) { // 3.2.경로를 바꿔야 하는 위치면 바꾼다.
				pos = 0;
				if(next == 5)
					cur_path = 1;
				else if(next == 10)
					cur_path = 2;
				else if(next == 15)
					cur_path = 3;
			}
			
			tagger[i][0] = cur_path;
			tagger[i][1] = pos;
			selected[path[i][next]] = true;
			selected[path[i][pos]] = false;// 말 위치 바꿔주기.
			reComb(depth+1,next,sum+score[next]); // 3.3. 점수 합산
		}
	}

}// end of class
