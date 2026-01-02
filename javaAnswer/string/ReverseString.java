package javaAnswer.string;

import java.util.Arrays;
// 02 ) 문자열 뒤집기
public class ReverseString {

	// // 02 ) 문자열 뒤집기 - 첫 번째 풀이 (인덱스 계산)
	// 배열의 값을 바꿔치기 해서 바꾸려고 했음.
	public String[] solution(String[] arr) {
		int len = arr.length;
		// 파이썬: for i in range(len(arr)//2):
		for (int i = 0; i < len / 2; i++) {
			// 자바는 다중 할당이 안 되므로 temp 변수를 사용
			String temp = arr[i];
			// arr[-(i+1)]는 자바에서 arr[len - 1 - i]와 같습니다.
			arr[i] = arr[len - 1 - i];
			arr[len - 1 - i] = temp;
		}
		return arr;
	}

	// // 힌트를 보고 푼 풀이: 슬라이딩 윈도우 투 포인터 (정석적인 방식)
	// 풀이가 틀린 건 아니지만 for _ in range(half): -> while left < right 가 좀 더 정석적인 슬라이딩 윈도우 풀이 같음.
	public String[] solutionSlidingWindow(String[] arr) {
		int left = 0;
		int right = arr.length - 1;

		// "반만 돌아야 함"을 while (left < right) 조건으로 명확하게 표현
		while (left < right) {
			// Swap 로직
			String temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;

			// 포인터 이동
			left++;
			right--;
		}
		return arr;
	}

	public static void main(String[] args) {
		ReverseString app = new ReverseString();

		String[] test1 = {"h", "e", "l", "l", "o"};
		System.out.println("Test 1: " + Arrays.toString(app.solution(test1)));

		String[] test2 = {"H", "a", "n", "n", "a", "h"};
		System.out.println("Test 2: " + Arrays.toString(app.solutionSlidingWindow(test2)));
	}
}