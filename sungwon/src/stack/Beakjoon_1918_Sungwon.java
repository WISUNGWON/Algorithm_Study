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
       int nowP = 0; // ���� ���� �켱����
       int preP = 0; // ���� ���� �켱����
       boolean pushCheck = false;

       
       for (int i = 0; i < str.length(); i++) { // 1. �Է¹��� String Ž��
           char ch = str.charAt(i);
           if (ch >= 'A' && ch <= 'Z') { // 2. ���ĺ��̸� ���
               sb.append(ch);
           } else { // 3. ��ȣ �Ǵ� �������� ���, �켱������ �ľ��Ͽ� ���ÿ� ����.
               if (ch == '(') { // ���� ���ڰ� '('�� ��� �ٷ� ���ÿ� ����
                   stack.push(ch);
               } else if (ch == ')') { // ���� ���ڰ� ')'�� ���
                   while(true) { // '('�� ���� �� ���� ���þ��� ������ ���
                       char tempC = stack.pop();
                       if (tempC == '(') {
                           break;
                       } else {
                           sb.append(tempC);
                       }
                   }
               } else { // '(' ')' �̿��� ��� �켱������ ���� ������ ó��
                   nowP = order(ch); // ���� ���ڿ� ���� ������ �켱���� �ľ�
                   if (stack.isEmpty()) {
                       preP = 0;
                   } else {
                       preP = order(stack.peek());
                   }
                   if (nowP > preP) { // ���� ������ �켱������ ū ��� ���ÿ� �ٷ� ����
                       stack.push(ch);
                   } else if (nowP <= preP) { 
                       // �켱������ �۰ų� ������� ���� ���� ������ pop�� ��� �� ���� ������ Push
                       sb.append(stack.pop());
                       while (!stack.isEmpty()) { // ���� ���� �����ں��� �켱������ Ŭ �� ����
                           int tempP = order(stack.peek());
                           if (nowP > tempP) {
                               stack.push(ch);
                               pushCheck = true;
                               break; //break �ϸ� 
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
