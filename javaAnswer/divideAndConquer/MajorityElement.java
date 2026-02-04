package javaAnswer.divideAndConquer;

/**
 *  83 ) 과반수 엘리먼트
 *  리트코드 169
 *
 */
public class MajorityElement {

	// 분할 정복 풀이
	public int majorityElement(int[] nums) {
		// 재귀 호출
		// 전체 배열(0 부터 nums.length )을 대상으로 시작
		return getMajority(nums, 0, nums.length - 1);
	}

	private int getMajority(int[] nums, int low, int high) {
		// 1. 더 이상 쪼갤 수 없을 때 (Base Case)
		// 더 이상 쪼갤 수 없는 크기(원소 1개)가 되면 그 숫자 자체가 과반수 후보
		if (low == high) return nums[low];

		// 2. 분할 (Divide)
		// 중간 지점(mid)를 계산하여 배열을 왼쪽과 오른쪽 두 덩이로 나눔.
		int mid = low + (high - low) / 2;
		// 왼쪽 배열의 과반수 후보 추출
		int left = getMajority(nums, low, mid);
		// 오른쪽 배열의 과반수 후보 추출
		int right = getMajority(nums, mid + 1, high);

		// 3. 정복/합치기 (Conquer/Combine)
		// 만약 왼쪽과 오른쪽의 과반수 후보가 같다면 확인할 필요 없이 해당 후보가 정답임.
		if (left == right) return left;

		// 양쪽 결과가 다르면 전체 구간에서 더 많이 나온 숫자를 선택
		int leftCount = countInRange(nums, left, low, high);
		int rightCount = countInRange(nums, right, low, high);

		return leftCount > rightCount ? left : right;
	}

	// 특정 범위(low, height) 안에서 숫자가 몇 번 나오는지 세는 도우미 함수
	private int countInRange(int[] nums, int num, int low, int height) {
		int count = 0;
		for (int i = low; i <= height; i++) {
			if (nums[i] == num) count++;
		}
		return count;
	}
}
