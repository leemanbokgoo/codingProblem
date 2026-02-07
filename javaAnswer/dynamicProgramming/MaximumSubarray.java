package javaAnswer.dynamicProgramming;

/**
 *  86 ) 최대 서브 배열
 *
 */
public class MaximumSubarray {

	// 상향식 풀이
	// 작은 부분 문제부터 해결해나가기때문.
	// nums[0]까지의 최대 부분합은 nums[0]이다. nums[1] 까지의 최대 부분합은 nums[0]의 결과를 이용해 결정한다.
	// 이런식으로 i번째 최적해를 구할 때 i - 1번째 최적해를 활용하여 위로 올라가며 최종 답을 도출한다.
	public int solution(int[] nums){
		// n이 1일 수 있으므로 첫 번째 원소로 초기화
		int currentSum = nums[0]; // 현재 진행 중인 합
		int maxSum = nums[0]; // 가장 큰 합

		// 이미 nums[0]은 위에서 초기화로 넣어놨음으로 nums[1]부터 시작
		for (int i = 1; i < nums.length; i++) {

			// 현재 값(nums[i])과 진행 중인 합(currentSum) + 현재값( nums[i])이 더 큰지 보고 더 큰값을 현재 진행 중인 값으로
			currentSum = Math.max(nums[i], currentSum + nums[i]);

			// 지금까지 발견한 최대합 중 가장 큰 값 갱신
			maxSum = Math.max(maxSum, currentSum);
		}

		return maxSum;
	}

	public static void main(String[] args){
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

	}
}
