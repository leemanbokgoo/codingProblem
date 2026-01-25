package javaAnswer.sort;

/**
 *  63 ) 색 정렬
 *  한번의 순회로 풀어야한다는 조건이 있음.
 *
 */
public class SortColors {

	public int[] solution(int[] colors) {
		int low = 0;          // 0이 들어갈 왼쪽 빈자리의 경계선, 이 앞으로는 무조건 0
		int mid = 0;          // 현재 탐색 위치
		int high = colors.length - 1; // 2가 들어갈 오른쪽 빈자리의 경계선, 이 뒤로는 무조건 2

		while ( mid <= high){ // 탐색 포인터가 2구역 경계선 high를 넘지않을때까지 반복.

			if( colors[mid] == 0 ){ // 0을 만나면 왼쪽 low 로 보낸다.
				swap(colors, low , mid);
				low++; // 0 구역이 한칸 넓어졌으니 경계선 확장

				// low와 바꾼 뒤의 mid 자리는 이미 검증된 값(1)이 온 것이므로 안심하고 다음 칸으로 이동.
				// 여기서 왜 low의 자리에 1이 있냐면 밑의 if문에서 mid가 1을 만나면 계속 전진함으로 mid는 쭉 1을 지나온 상태다.
				// 예를 들어 [ 0, (low)1, 1, (mid)0 ] 인 상태에서 mid와 low의 값을 swap하는 것. (이미 위에서 swap함)
				mid++;

			} else if ( colors[mid] == 1){ // 1을 만나면 다음으로 전진
				mid++;

			} else { // 2를 만나면 오른쪽과 교체
				swap(colors, mid, high);

				// high 위치에 있던 숫자는 mid 의 앞에 있던 숫자임으로 검증된 숫자가 아니다
				// 만약 high에서 mid 자리로 가져온 숫자가 또 2이거나 0일 수도 있기 때문에, mid 자리에서 다시 한번 검사를 받아야하기때문에 mid는 ++ 하지않음.
				high --;
			}
		}
		return colors;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// Counting Sort (Two-pass)
	// 원패스가 필수가 아니라면 0, 1, 2의 개수를 각각 센 다음 배열에 차례대로 덮어씌우는 방식도 가능
	public void sortColors(int[] nums) {
		int count0 = 0, count1 = 0;

		for (int num : nums) {
			if (num == 0) count0++;
			else if (num == 1) count1++;
		}

		for (int i = 0; i < nums.length; i++) {
			if (i < count0) nums[i] = 0;
			else if (i < count0 + count1) nums[i] = 1;
			else nums[i] = 2;
		}
	}
}
