package javaAnswer.divideAndConquer;

import java.util.HashMap;
import java.util.*;

/**
 *  84 ) 괄호를 삽입하는 여러가지 방법
 *  리트코드 241
 */
public class DifferentWaysToAddParentheses {

	public List<Integer> solution(String expression) {
		// [여기가 포인트] 함수가 호출될 때마다(새 테스트 케이스마다) 새 창고를 만든다!
		Map<String, List<Integer>> memo = new HashMap<>();
		return diffWaysToCompute(expression, memo);
	}
	public List<Integer> diffWaysToCompute(String expression, Map<String, List<Integer>> memo) {

		// 예외처리 이전에 계산한 적이 있는 식이라면 바로 반환
		// 예를 들어 2-1-1을 풀다가 1-1이 또 나오면 새로 계산하지않고 저장해둔 값을 씀/
		if (memo.containsKey(expression)) return memo.get(expression);

		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < expression.length(); i++) {
			// 현재 지점의 char 을 뽑음.
			char c = expression.charAt(i);

			// 분할 지점 찾기. 연산자를 만났을 때가 분할 타임이다.
			if (c == '-' || c == '+' || c == '*') {
				// Divide (왼쪽): 연산자 기준 왼쪽 문자열을 통째로 재귀 호출
				// 예: "2-1-1"에서 첫 번째 '-'를 만났다면 "2"를 보냄
				List<Integer> left = diffWaysToCompute(expression.substring(0, i), memo);

				// Divide (오른쪽): 연산자 기준 오른쪽 문자열을 통째로 재귀 호출
				// 예: "2-1-1"에서 첫 번째 '-'를 만났다면 "1-1"을 보냄
				List<Integer> right = diffWaysToCompute(expression.substring(i + 1), memo);

				// 3. 결합: 왼쪽 결과들과 오른쪽 결과들을 조합해서 계산
				// 왼쪽 결과에서 나온 답이 [2], 오른쪽(1-1)에서 나온 답이 [0]이라면 2 - 0 = 2라는 결과를 리스트에 담는다.
				for (int l : left) {
					for (int r : right) {
						if (c == '+') res.add(l + r);
						else if (c == '-') res.add(l - r);
						else if (c == '*') res.add(l * r);
					}
				}
			}
		}

		// 연산자가 하나도 없다면 루프를 다 돌아도 res가 비어있게된다. (Base Case)
		if (res.size() == 0) {
			res.add(Integer.valueOf(expression));
		}

		// 다음에 똑같은 식을 만나면 재사용 할 수 도록 저장.
		memo.put(expression, res);

		// 결과값 반환.
		return res;
	}
}