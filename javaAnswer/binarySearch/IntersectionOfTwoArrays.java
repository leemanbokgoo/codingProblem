package javaAnswer.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *  67 ) 두 배열의 교집합
 */
public class IntersectionOfTwoArrays {

	// 이진 탐색
	public int[] solution( int[] nums1, int[] nums2){

		// 1. 이진 탐색을 위해 하나의 배열을 정렬
		Arrays.sort(nums1);
		HashSet<Integer> intersect = new HashSet<>(); // 결과 배열은 중복을 제거해야함으로 hashSet 으로 선언

		// 2. nums2의 각 원소가 nums1에 있는지 이진 탐색으로 찾는다
		for (int target : nums2) {
			if (binarySearch(nums1, target)) {
				intersect.add(target);
			}
		}

		// 3. 결과 변환
		int[] result = new int[intersect.size()];
		int i = 0;
		for (int num : intersect) {
			result[i++] = num;
		}
		return result;
	}

	public boolean binarySearch(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + ( right - left) / 2;

			if( nums[mid] == target){
				return true;

			} else if( nums[mid] > target){
				right = mid - 1;
			} else {
				left = mid + 1;
			}

		}

		return false;
	}

	// HashSet 사용
	public int[] intersection(int[] nums1, int[] nums2) {
		HashSet<Integer> set1 = new HashSet<>();
		HashSet<Integer> intersect = new HashSet<>();

		// 1. 첫 번째 배열의 모든 숫자를 set1에 담는다 (중복 자동 제거)
		for (int num : nums1) {
			set1.add(num);
		}

		// 2. 두 번째 배열을 돌면서 set1에 있는 숫자가 있다면 결과 set에 담는다
		for (int num : nums2) {
			if (set1.contains(num)) {
				intersect.add(num);
			}
		}

		// 3. Set을 배열로 변환하여 반환
		int[] result = new int[intersect.size()];
		int i = 0;
		for (int num : intersect) {
			result[i++] = num;
		}
		return result;
	}

	public static void main(String[] args) {

	}
}
