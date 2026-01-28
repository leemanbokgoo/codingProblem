package javaAnswer.bit;

/**
 *  70 ) 싱글 넘버
 *  추가 메모리를 쓰지말고 O(1)의 공간 복잡도 시간 복잡도는 O(n)으로 풀어야하는 제약 사항이 존재함.
 */
public class SingleNumber {

	// 비트 연산
	public int solution(int[] nums) {
		int result = 0; // 초기값 0

		for (int num : nums) {
			result = result ^ num; // 모든 요소를 XOR 연산
		}

		return result; // 마지막에 남은 '외톨이' 숫자 반환
	}

	public static void main(String[] args) {

	}
}
