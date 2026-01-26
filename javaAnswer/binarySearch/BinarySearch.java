package javaAnswer.binarySearch;

/**
 * 65 ) 이진 검색
 */
public class BinarySearch {

	public int solution(int[] nums, int target ){

		int left = 0 ;
		int right = nums.length;


		while ( left <= right){

			int mid = left + ( right - left) / 2;

			if(nums[mid] == target ){
				return mid;

			} else if ( nums[mid] < target ){
				left = mid + 1;

			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums = {-1, 0, 3, 5, 9, 12};
		int target = 9;

		BinarySearch app = new BinarySearch();
		System.out.println(app.solution(nums, target));
	}
}
