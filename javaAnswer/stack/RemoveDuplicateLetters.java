package javaAnswer.stack;

import java.util.*;

// 21 ) 중복 문자 제거
public class RemoveDuplicateLetters {

	public String solution(String s) {
		// counter: 각 문자의 잔여 개수를 저장 (알파벳 소문자 기준 26개)
		int[] counter = new int[26];
		for (char c : s.toCharArray()) {
			counter[c - 'a']++;
		}

		// stack: 결과를 담을 스택
		Stack<Character> stack = new Stack<>();
		// seen: 현재 스택에 포함된 문자인지 확인하는 집합
		boolean[] seen = new boolean[26];

		for (char charCurrent : s.toCharArray()) {
			// 현재 문자 처리 시작 - 잔여 개수 하나 줄임
			counter[charCurrent - 'a']--;

			// 이미 스택에 있는 문자라면 건너뜀 (중복 제거)
			if (seen[charCurrent - 'a']) {
				continue;
			}

			// 스택이 비어있지 않고,
			// 현재 문자가 스택의 마지막 문자보다 사전순으로 앞서며,
			// 스택의 마지막 문자가 뒤에 더 남아있다면 (counter > 0)
			while (!stack.isEmpty() && charCurrent < stack.peek() && counter[stack.peek() - 'a'] > 0) {
				// 스택에서 제거하고 seen 상태도 해제
				seen[stack.pop() - 'a'] = false;
			}

			// 현재 문자 추가
			stack.push(charCurrent);
			seen[charCurrent - 'a'] = true;
		}

		// 스택에 쌓인 문자를 문자열로 합치기
		StringBuilder sb = new StringBuilder();
		for (char c : stack) {
			sb.append(c);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		RemoveDuplicateLetters app = new RemoveDuplicateLetters();
		String s = "bcabc";
		System.out.println(app.solution(s)); // 출력: "abc"
	}
}