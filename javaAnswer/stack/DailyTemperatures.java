package javaAnswer.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 *  22 ) 일일 온도
 */

public class DailyTemperatures {
	public int[] solution(int[] T) {
		int[] results = new int[T.length];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < T.length; i++) {
			int cur = T[i];

			// 반복문을 돌면서 인덱스(i)를 넣어주다가 현재 온도가 T[stack[-1]]보다 크다면, while문 루프 발동
			while (!stack.isEmpty() && cur > T[stack.peek()]) {
				// 해당 날짜의 인덱스를 꺼내서 현재 날짜의 인덱스를 빼면 last가 몇일을 기다려야 더 높은 온도가 되는지 알수 있다.
				int last = stack.pop();
				results[last] = i - last;
			}
			stack.push(i);
		}

		return results;
	}

	public static void main(String[] args) {
		DailyTemperatures app = new DailyTemperatures();

		int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
		int[] result = app.solution(T);

		// 결과 출력: [1, 1, 4, 2, 1, 1, 0, 0]
		System.out.println(Arrays.toString(result));
	}
}