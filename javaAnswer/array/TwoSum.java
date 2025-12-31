package javaAnswer.array;

import java.util.ArrayList;
import java.util.List;

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

	// 책의 풀이
	public static int[] solution2(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (!(left == right)) {

			if (nums[left] + nums[right] < target) {
				left += 1;
			} else if (nums[left] + nums[right] > target) {
				right -= 1;
			} else {
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