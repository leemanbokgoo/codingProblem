package javaAnswer.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 20 ) 유효한 괄호
public class ValidParentheses {

	// 내 풀이: 여는 괄호를 Key로 관리하는 방식
	public boolean solution(String s) {
		Stack<Character> stack = new Stack<>();
		Map<Character, Character> table = new HashMap<>();
		table.put('(', ')');
		table.put('{', '}');
		table.put('[', ']');

		for (char item : s.toCharArray()) {
			// key(여는 괄호)가 들어오면 stack에 넣어줌
			if (table.containsKey(item)) {
				stack.push(item);
				continue;
			}

			// stack에 값이 없는 상황에서 닫는 괄호가 들어오면 False
			// stack에 값이 있다면 팝한 key의 짝(value)과 현재 item이 맞는지 확인
			if (stack.isEmpty() || table.get(stack.pop()) != item) {
				return false;
			}
		}

		// 모든 체크 후에도 stack이 남아있다면 짝이 맞지 않는 것
		return stack.isEmpty();
	}

	// 책 풀이: 닫는 괄호를 Key로 두어 비교하는 방식 (자바 스타일로 최적화)
	public boolean solution2(String s) {
		Stack<Character> stack = new Stack<>();
		// 책 로직의 의도에 맞게 닫는 괄호를 key, 여는 괄호를 value로 설정
		Map<Character, Character> table = new HashMap<>();
		table.put(')', '(');
		table.put('}', '{');
		table.put(']', '[');

		for (char charItem : s.toCharArray()) {
			// 닫는 괄호가 아니라면(즉, 여는 괄호라면) 스택에 푸시
			if (!table.containsKey(charItem)) {
				stack.push(charItem);
			}
			// 닫는 괄호인데 스택이 비어있거나, 팝한 결과가 짝꿍 여는 괄호가 아니라면
			else if (stack.isEmpty() || table.get(charItem) != stack.pop()) {
				return false;
			}
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {
		ValidParentheses app = new ValidParentheses();
		String s = "()[]{}";

		System.out.println("풀이 1 결과: " + app.solution(s)); // true
		System.out.println("풀이 2 결과: " + app.solution2(s)); // true
	}
}