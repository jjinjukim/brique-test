package brique.brique_test.assignment7;

/**
 * Description : Assignment7 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Assignment7 {
    /**
     * 주어진 문자열에서 올바르게 짝지어진 괄호의 최대 길이를 반환합니다.
     *
     * @param s 괄호로만 구성된 문자열
     * @return 올바르게 짝지어진 괄호의 최대 길이
     */
    public static int longestValidParentheses(String s) {
        int maxLength = 0;
        // Deque를 스택 용도로 사용
        Deque<Integer> stack = new LinkedList<>();
        // 기준점: 유효한 문자열의 시작 전 인덱스
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                // 여는 괄호의 인덱스를 스택에 추가
                stack.push(i);
            } else { // current == ')'
                // 닫는 괄호가 나오면 스택에서 하나 pop
                stack.pop();
                if (stack.isEmpty()) {
                    // 스택이 비었다면 현재 닫는 괄호 인덱스를 새로운 기준점으로 설정
                    stack.push(i);
                } else {
                    // 현재 유효한 길이는 현재 인덱스와 스택 최상단의 인덱스의 차이
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "(()()()()()))";
        String s2 = ")()())";
        System.out.println("Input: " + s1 + " -> result: " + longestValidParentheses(s1));
        System.out.println("Input: " + s2 + " -> result: " + longestValidParentheses(s2));
    }
}

