import java.util.*;

// 00 ) 배열 파티션 1
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
				//  min(array)
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