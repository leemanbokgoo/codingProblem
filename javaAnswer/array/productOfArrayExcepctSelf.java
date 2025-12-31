import java.util.Arrays;
// 11 ) 자신을 제외한 배열의 곱
// 나눗셈을 사용하지 않고 $O(n)$ 시간에 해결해야함.

// 풀이 (책 참고)
// 왼쪽에서 오른쪽으로 진행하며 곱셈 결과를 쌓고, 다시 오른쪽에서 왼쪽으로 오면서 남은 곱셈을 처리하는 방식으로 풀이해야함.
public class ProductExceptSelf {
	public int[] solution(int[] nums) {
		int n = nums.length;
		int[] output = new int[n];

		// 1. 왼쪽 곱셈 결과 채우기
		// sum은 현재 인덱스 이전까지의 모든 요소의 곱
		int sum = 1;
		for (int i = 0; i < n; i++) {
			output[i] = sum;
			sum *= nums[i];
		}

		// 2. 오른쪽 곱셈 결과를 기존 output에 곱하기
		// 뒤에서부터 순회하며 오른쪽의 곱셈 결과(sum)를 누적하여 곱함
		sum = 1;
		for (int i = n - 1; i >= 0; i--) {
			output[i] = output[i] * sum;
			sum *= nums[i];
		}

		return output;
	}

	public static void main(String[] args) {
		ProductExceptSelf sol = new ProductExceptSelf();
		int[] nums = {1, 2, 3, 4};

		int[] result = sol.solution(nums);

		// 배열 출력을 위해 Arrays.toString() 사용
		System.out.println(Arrays.toString(result)); // 결과: [24, 12, 8, 6]
	}
}