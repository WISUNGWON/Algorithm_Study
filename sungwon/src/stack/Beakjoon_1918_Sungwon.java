package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Beakjoon_1918_Sungwon {

    static Stack<Character> stack = new Stack<>();
    
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       String str = br.readLine();
       int nowP = 0; // 현재 문자 우선순위
       int preP = 0; // 이전 문자 우선순위
       boolean pushCheck = false;

       
       for (int i = 0; i < str.length(); i++) { // 1. 입력받은 String 탐색
           char ch = str.charAt(i);
           if (ch >= 'A' && ch <= 'Z') { // 2. 알파벳이면 출력
               sb.append(ch);
           } else { // 3. 괄호 또는 연산자일 경우, 우선순위를 파악하여 스택에 저장.
               if (ch == '(') { // 현재 문자가 '('인 경우 바로 스택에 저장
                   stack.push(ch);
               } else if (ch == ')') { // 현재 문자가 ')'인 경우
                   while(true) { // '('를 만날 때 까지 스택안의 연산자 출력
                       char tempC = stack.pop();
                       if (tempC == '(') {
                           break;
                       } else {
                           sb.append(tempC);
                       }
                   }
               } else { // '(' ')' 이외의 경우 우선순위에 따라 연산자 처리
                   nowP = order(ch); // 현재 문자와 이전 문자의 우선순위 파악
                   if (stack.isEmpty()) {
                       preP = 0;
                   } else {
                       preP = order(stack.peek());
                   }
                   if (nowP > preP) { // 현재 문자의 우선순위가 큰 경우 스택에 바로 저장
                       stack.push(ch);
                   } else if (nowP <= preP) { 
                       // 우선순위가 작거나 같은경우 기존 스택 연산자 pop후 출력 뒤 현재 연산자 Push
                       sb.append(stack.pop());
                       while (!stack.isEmpty()) { // 기존 순위 연산자보다 우선순위가 클 때 까지
                           int tempP = order(stack.peek());
                           if (nowP > tempP) {
                               stack.push(ch);
                               pushCheck = true;
                               break; //break 하면 
                           } else {
                               sb.append(stack.pop());
                           }
                       }
                       if (!pushCheck) {
                           stack.push(ch); 
                       }

                   } 
               }             
           }
       }
       while(!stack.isEmpty()) {
           sb.append(stack.pop());
       }
       
       System.out.println(sb);
    } // end of method
    
    private static int order(char ch) {
        int priority = 0;
        if (ch == '+' || ch == '-') {
            priority = 1;
        } else if (ch == '*' || ch == '/') {
            priority = 2;
        } 
        return priority;
    }

} // end of class
