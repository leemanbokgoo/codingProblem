package javaAnswer.dynamicProgramming;

/**
 * 87 ) 계단 오르기
 */
public class ClimbingStairs {

	// 상향식
	public int solution(int n) {
		// n이 1인 경우 배열 크기 때문에 예외 처리
		if (n == 1) return 1;

		// 1. 테이블 생성 (n번째까지 저장하기 위해 n+1 크기)
		int[] dp = new int[n + 1];

		// 2. 초기값(기저 상태) 설정
		dp[1] = 1; // 계단 1개를 오르는 방법: (1)
		dp[2] = 2; // 계단 2개를 오르는 방법: (1,1), (2)

		// 3. 3번째 계단부터 n번째까지 차례대로 계산 (상향식)
		// 3번째 계단에 올라가기위해선 2단씩 올라온 다음에 마지막 1칸을 더하는 방법과 1단까지 올라온다음 마지막에 2칸을 올라가는 방법이 두가지다.
		// 즉, 계단 2개를 오르는 방법에서 (1,1), (2)에서 + 1하여 (1,1,1), (2,1)하면 된다. 이때 방법의 수는 그대로 2가지다.
		// 계단 1개를 오르는 방법 (1)에서 + 2를 하여 (1,2) 이때 방법의 수는 그대로 1가지다.
		// 고로 3단에 도착하는 모든 방법은 "2단에서 오는 법(2가지)" + "1단에서 오는 법(1가지)"을 합쳐서 3가지가 된다.
		// 이를 식으로 나타내면 dp[3] = dp[2] + dp [1]  => dp[i] = dp[i - 1] + dp[i - 2]이 된다.
		// 이는 3계단부터 시작이 가능하기때문에 (1과 2가 기본값으로 셋팅되어있어야함으로) int i = 3;이다.
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}

	// 공간최적화 : 상향식에서 배열 없이 변수 두개만 사용하여 메모리를 아끼는 방법.
	public int solution2(int n){
		if (n <= 2) return n;

		int first = 1;  // f(n-2) 역할
		int second = 2; // f(n-1) 역할

		for (int i = 3; i <= n; i++) {
			// dp[i] = dp[i - 1] + dp[i - 2]의 식을 변수로만 처리하면 아래와 같다.
			// 3계단까지 가는 법 = 1층 계단까지 가는 법 + 2층 계단까지 가는 법
			int current = first + second;
			first = second; // 한 칸 전진 , first 는 second -1 임으로 second 값을 first 에 넣어주면 1칸 전진이다.
			second = current; // 한 칸 전진, second 는 current - 1 임으로 current 값을 second 에 넣어주면 1칸 전진이다.
		}

		return second;
	}
}
