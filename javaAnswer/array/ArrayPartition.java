package javaAnswer.array;

import java.util.*;

/***
 * 10 ) 배열 파티션 1
 * min(1,2) + min(3,4) = 4 를 보면 min()이 되도록 커야한다는 뜻이고 뒤에서부터 내림차순으로 집어넣으면 항상 최대 min() 페어를 유지할 수 있음.
 * 최소값들의 합을 최대화하려면, 큰 숫자가 작은 숫자 때문에 함께 min()에 걸려서 사라지는 것을 막아야하기떄문임.
 * min(1,4)가 되면 4가 사라짐 하지만 min(1,2) + min(4,5)하면 4가 살아남아서 더 큰 sum을 만들어낼 수 있기때문에 정렬하는 것.
 * 정렬해서 2개로 나누면 (작은값,큰값)이 되기떄문에 항상 페어의 2번째 숫자가 크다는 걸 알수있다.
 *
 */
public class ArrayPartition {

	// 첫 번째 풀이: 리스트를 활용한 방식
	public int solution1(int[] nums) {
		Arrays.sort(nums);
		List<Integer> array = new ArrayList<>();
		int sum = 0;

		for (int num : nums) {
			array.add(num);
			if (array.size() == 2) {
				// 정렬되어 있으므로 사실상 array.get(0)이 항상 최소값이다.
				// min(array)
				sum += Math.min(array.get(0), array.get(1));
				array.clear(); // array = []
			}
		}
		return sum;
	}

	// 두 번째 풀이: 인덱스(짝수 번째)를 활용한 최적화 방식
	// 책의 힌트보고 푼 풀이 2
	public int solution2(int[] nums) {
		Arrays.sort(nums);
		int sum = 0;

		// i를 2씩 증가시키면 0, 2, 4... 인덱스만 바로 접근 가능합니다.
		for (int i = 0; i < nums.length; i += 2) {
			sum += nums[i];
		}

		return sum;
	}

	public static void main(String[] args) {
		ArrayPartition sol = new ArrayPartition();
		int[] nums = {1, 4, 3, 2};

		System.out.println("Solution 1: " + sol.solution1(nums)); // 결과: 4
		System.out.println("Solution 2: " + sol.solution2(nums)); // 결과: 4
	}
}