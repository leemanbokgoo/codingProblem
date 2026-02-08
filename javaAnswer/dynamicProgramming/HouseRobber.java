package javaAnswer.dynamicProgramming;

/**
 *  88 ) 집도둑
 */
public class HouseRobber {

	// 상향식 공간 최적화
	public int solution(int[] nums) {
		// 1. 예외 처리: 집이 없으면 0원, 하나면 그 집 돈이 최대.
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];

		// 2. 초기 세팅 (기초 공사)
		// prev2: 두 칸 전 집까지 털었을 때의 최대 금액 (f(n-2))
		// prev1: 바로 전 집까지 털었을 때의 최대 금액 (f(n-1))
		// 둘다 0으로 셋팅하는 이유는 밑의 로직에서 max() current = Math.max(0, 0 + nums[0])가 되어 첫 번째 집의 돈이 자연스럽게 prev1에 저장된다.
		int prev2 = 0;
		int prev1 = 0;

		// 3. 루프: 모든 집을 순서대로 방문하며 '상향식'으로 계산한다.
		for (int num : nums) {
			// 4. 핵심 결정 (점화식)
			// prev2 + num : 현재 집을 털기떄문에 두 칸 전까지의 최대합(prev2) + 현재 집의 돈 num 을 합친다.
			// prev1 : 현재 집을 안 털기 때문에 바로 전 집까지 털었던 최대합 prev1을 max()로 비교한다.
			int current = Math.max(prev1, prev2 + num);

			// 5. 다음 집으로 이동하기 위해 값을 한 칸씩 밀어준다.
			prev2 = prev1; // 이제 한칸 뒤의 집(prev1)이 두칸 뒤의 집의 최대값(prev2)이 됨
			prev1 = current; // 방금 구한 현재 최대치(current)가 prev2(전 집의 최댓값)이 됨
		}

		// 6. 마지막까지 계산된 최대 금액을 반환합니다.
		return prev1;
	}
}
