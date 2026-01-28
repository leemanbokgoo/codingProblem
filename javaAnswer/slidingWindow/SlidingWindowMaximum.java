package javaAnswer.slidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 75 ) 최대 슬라이딩 윈도우
 */
public class SlidingWindowMaximum {

	// 단조 큐
	public int[] solution(int[] nums, int k) {
		int n = nums.length;
		//  결과값을 담을 배열 (전체 길이 - 윈도우 크기 + 1)
		// + 1을 하는 이유는 첫번쨰 창문도 포함해야하기때문.
		int[] result = new int[n - k + 1];
		int resIdx = 0;

		// 2. 덱(Deque) 생성: 숫자의 '인덱스'를 저장한다. 내림차순이다.(큰 값일수록 앞)
		Deque<Integer> deque = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {

			// 3. 창문을 벗어난 인덱스 제거
			// 덱의 맨 앞(peekFirst)에 있는 인덱스가 현재 윈도우 범위를 벗어났다면 삭제
			// peekFirst() : 덱의 맨 앞에 있는 값을 조회(삭제 X)
			// i - k : 현재 윈도우 바로 직전에 끝난 '삭제되어야 할 인덱스
			if (!deque.isEmpty() && deque.peekFirst() == i - k) {
				// pollFirst() : 맨 앞에 있는 값을 꺼내서 삭제
				deque.pollFirst();
			}

			// 2. nums[i] 작은 값 삭제 (단조 큐 핵심 로직)
			// peekLast() : 맨뒤에 있는 값을 조회
			// 새로 들어올 숫자(nums[i])보다 작은 숫자가 덱 뒤쪽에 있다면 해당 숫자는 절대 최댓값이 될 수 없으므로 삭제.
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				// pollLast() : 맨 뒤에 있는 값을 꺼내서 삭제
				deque.pollLast();
			}

			// [단계 3] 현재 숫자의 인덱스를 덱에 넣기
			// offerLast() : 맨 뒤에 새로운 값을 추가
			// 다음 윈도우에서는 현재 nums[i]값이 제일 큰 값이 될 수 있음으로 일단 넣어서 다음 반복문에 삭제하던가 하는 것.
			deque.offerLast(i);

			// [단계 4] 윈도우가 완성된 시점(i >= k-1)부터 결과를 배열에 담기
			// 최소한 숫자 k개는 모였을 때부터 결과를 저장하기 시작해야함으로 i가 k-1만큼 커진다면 윈도우 크기가 k만큼 되었다는 뜻.
			if ( i >= k - 1 ) {
				// 덱의 맨 앞(peekFirst)은 항상 현재 윈도우에서 가장 큰 숫자의 인덱스.
				result[resIdx++] = nums[deque.peekFirst()];
			}
		}

		return result;

	}
	public static void main(String[] args) {

	}
}
