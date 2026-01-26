package javaAnswer.binarySearch;

/**
 * 66 ) 회전 정렬된 배열 검색
 */
public class SearchInRotatedSortedArray {

	// 배열이 뒤틀려있어도 어느 한쪽은 반드시 정상적으로 정렬되어있다는 조건으로 이진 탐색함.
	public int solution(int[] nums, int target) {
		int left = 0;
		int right = nums.length;

		while( left <= right){

			int mid = left + ( right - left) / 2;

			if (nums[mid] == target) {
				return mid;
			}

			// mid값이 left보다 크거나 같다면 left부터 mid까지는 정상적인 오름차순이라는 것.
			// 만약 4 5 6 0 1 2일때 left = 4, mid = 6 라면 left - mid 구간은 정상적으로 정렬되어있다는 뜻.
			if (nums[left] <= nums[mid]) {

				// target이 left - mid 범위 안에 있다면
				if (nums[left] <= target && target <= nums[mid]) {
					right = mid - 1; // 정렬된 왼쪽만 보면 됨으로 right 범위를 축소

				} else{
					// 아니라면 오른쪽 구간에 있음으로 left를 뒤로
					left = mid + 1;
				}

				// mid값이 left보다 작다면, mid를 기준으로 왼쪽 구간은 정렬되어있지않고 오른쪽 구간은 정렬되어있다는 뜻이다.
			} else {

				//타겟이 정렬된 오른쪽 구간에 존재하는 지 확인
				if (nums[mid] < target && target <= nums[right]) {
					left = mid + 1; // 있다면 왼쪽 범위를 줄인다.
				} else {
					// 아니라면 왼쪽 구간에 있음으로 right를 범위를 줄인다.
					right = mid - 1;
				}
			}

		}
		return -1;
	}

	public static void main(String[] args){
		int[] nums = {4,5,6,7,0,1,2};
		int target = 1;

		SearchInRotatedSortedArray app = new SearchInRotatedSortedArray();
		System.out.println(app.solution(nums, target));

	}
}
