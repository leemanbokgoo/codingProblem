package javaAnswer.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20 ) 유효한 괄호
 *
 */
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

	// 책 풀이: 닫는 괄호를 Key로 두어 비교하는 방식
	// 닫는 괄호로 쓰는 이유는 )를 검증의 주체로 봤기때문이다. ()가 주어질때 검증을 하는 순간은 ) 괄호가 나오는 순간임.
	// 위의 풀이는 스택에 있는 값을 꺼내서 table에 조회해야하지만 아래의 풀이는 현재 값을 table에 바로 넣어서 짝을 찾을 수 있다. 해당 값의 짝을 찾고 그 짝이 stack에 존재하는지 확인한다.
	// 즉, 스택의 값이 검증의 주체가 되느냐 반복문의 현재 값이 검증의 주체가 되느냐의 차이가 있는데 )를 중심으로 보는게 논리적 흐름이 자연스러움으로 아래의 풀이처럼하는 게 일반적이긴하다.
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

				// 해당 방법은 table.get(charItem)을 통해 바로 charItem의 정답을 알수 있으나
				// 기존의 table.get(stack.pop()) != item)은 stack.pop()을 거쳐야 item의 정답을 알 수 있음.
				// 즉, Map.get(현재 문자) 가 가능하게 설계하여 코드의 가독성을 높이기 위해서 닫는 괄호를 key로 쓴다.
				// 그리고 현재의 문제와는 상관없긴하지만 만약 여는 괄호는 한개인데 거기에 맞는 닫는 괄호가 여러개라면 여러개인 쪽을 key로 넣어서 써야 한번에 괄호에 대한 짝을 찾을 수 있다.
			} else if (stack.isEmpty() || table.get(charItem) != stack.pop()) {
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