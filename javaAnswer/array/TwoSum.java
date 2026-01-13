package javaAnswer.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 07 ) 두 수의 합
 */

public class TwoSum {

	// 내 풀이
	// 이 문제는 투 포인터로 해결 하면 된다고 생각했음.
	public static List<Integer> solution(int[] nums, int target) {

		int left = 0;
		int right = nums.length - 1;
		List<Integer> results = new ArrayList<>();

		while (left < right) {

			if (nums[left] + nums[right] < target) {
				left += 1;
			} else if (nums[left] + nums[right] > target) {
				right -= 1;
			} else {
				results.add(left);
				results.add(right);
				break;
			}
		}

		return results;
	}

	// 책 풀이
	public static int[] solution2(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		// 전체 식을 부정하고 싶을 때는 반드시 전체를 괄호로 감싸고 그 앞에 !를 붙여야한다.
		// !left == right 하면 !left를 먼저 계산하려고 시도해서 문법오류 발생(left가 숫자임으로)
		while (!(left == right)) {

			if (nums[left] + nums[right] < target) {
				left += 1;
			} else if (nums[left] + nums[right] > target) {
				right -= 1;
			} else {
				// 정수형 배열을 만들어서 바로 반환
				return new int[]{left, right};
			}
		}
		return new int[]{}; // 일치하는 값이 없을 경우 빈 배열 반환
	}

	// 실행 테스트
	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 9;

		// solution1 테스트 (List 출력)
		System.out.println(solution(nums, target));

		// solution2 테스트 (배열 출력)
		int[] result2 = solution2(nums, target);
		System.out.print("[");
		for (int i = 0; i < result2.length; i++) {
			System.out.print(result2[i] + (i == result2.length - 1 ? "" : ", "));
		}
		System.out.println("]");
	}
}