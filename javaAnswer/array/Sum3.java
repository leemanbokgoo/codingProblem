package javaAnswer.array;

import java.util.*;

/**
 * 09 ) 세수의 합
 * 내 풀이. 투포인터로 3개의 요소를 어떻게 더하는지 모르겠어서 책에서 힌트 참고 함.
 */

public class Sum3 {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();

		// 투포인터 활용을 위한 정렬
		Arrays.sort(nums);

		// -2 하는 이유는 세개의 값을 더해야함으로 현재 i를 제외한 2개의 숫자가 필요하기때문.
		for (int i = 0; i < nums.length - 2; i++) {

			// 중복된 i 값 건너뛰기
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			int left = i + 1;
			int right = nums.length - 1;

			// i 값을 두고 나머지 left, right 포인터를 움직여 가며 정답을 찾는다.
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];

				if (sum < 0) {
					left++;

				} else if (sum > 0) {
					right--;

				} else {
					// 합이 0인 경우 결과 리스트에 추가
					results.add(Arrays.asList(nums[i], nums[left], nums[right]));

					// 중복된 left, right 값 건너뛰기
					// left < right : 포인터가 범위 안에 있고 nums[left]와 nums[left + 1]의 값이 같다면
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					while (left < right && nums[right] == nums[right - 1]) {
						right--;
					}

					// 포인터 한 칸씩 더 이동
					left++;
					right--;
				}
			}
		}
		return results;
	}

	public static void main(String[] args) {
		Sum3 sol = new Sum3();
		int[] nums = {-1, 0, 1, 2, -1, -4};

		List<List<Integer>> result = sol.threeSum(nums);
		System.out.println(result);
	}
}