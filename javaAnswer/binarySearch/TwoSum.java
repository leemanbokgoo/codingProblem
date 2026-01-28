package javaAnswer.binarySearch;

/**
 *  68 ) 두 수의 합
 */
public class TwoSum {

	// 이진 탐색
	public int[] solution(int[] numbers, int target) {

		for (int i = 0; i < numbers.length; i++) {
			// target 에서 현재 값( numbers[i] ) 을 빼고 남은 값을 이진 탐색으로 찾는다.
			int complement = target - numbers[i];
			int left = i + 1;
			int right = numbers.length - 1;

			while (left <= right) {
				int mid = left + (right - left) / 2;

				// 만약 complement 을 찾았다면
				if (numbers[mid] == complement) {
					// 배열의 인덱스가 1부터 시작한다고 문제에서 제시 했음으로 +1 해야함.
					return new int[]{i + 1, mid + 1};
				}

				if (numbers[mid] < complement) left = mid + 1;
				else right = mid - 1;
			}
		}
		// 없다면 -1, -1 배열 출력
		return new int[]{-1, -1};
	}

	// 투 포인터
	public int[] twoPointer(int[] numbers, int target) {
		int left = 0;
		int right = numbers.length - 1;

		while (left < right) {
			int sum = numbers[left] + numbers[right];

			if (sum == target) {
				return new int[]{left + 1, right + 1};

			} else if (sum < target) {
				// 합이 작으면 더 큰 숫자가 필요하므로 왼쪽 포인터를 오른쪽으로
				left++;
			} else {
				// 합이 크면 더 작은 숫자가 필요하므로 오른쪽 포인터를 왼쪽으로
				right--;
			}
		}
		return new int[]{-1, -1};
	}
	public static void main(String[] args) {
		int[] numbers = {2,7,11,15};
		int target = 9;

		TwoSum app = new TwoSum();
		System.out.println(app.solution(numbers, target));

	}
}
