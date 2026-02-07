package javaAnswer.dynamicProgramming;

/**
 *  85 ) 피보나치 수
 */
public class FibonacciNumber {
	private int[] memo;

	// 타뷸레이션(상향식)
	public int solution_bottom_up(int n) {
		// n이 0이나 1일 때 예외 처리
		if (n <= 1) return n;

		// 1. 테이블(배열) 생성
		int[] dp = new int[n + 1];

		// 2. 기저 상태(Base Case) 설정
		dp[0] = 0;
		dp[1] = 1;

		// 3. 작은 문제부터 차례대로 표를 채움 (Iteration)
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		// 4. 최종 목표값 반환
		return dp[n];
	}

	// 반복문 사용. 타뷸레이션 방식을 기반으로 하되 공간 최적화한 풀이
	public int solution_bottom_up2(int n){
		if (n <= 1) return n;

		int prev = 0;
		int curr = 1;

		for (int i = 2; i <= n; i++) {
			int next = prev + curr;
			prev = curr;
			curr = next;
		}

		return curr;
	}

	// 재귀 풀이 : 메모이제이션
	public int solution_memoization(int n) {
		// 저장할 배열
		memo = new int[31]; // 문제 조건 n <= 30 임으로
		return fib(n);
	}

	public int fib(int n ){
		if (n <= 1) return n;
		if (memo[n] != 0) return memo[n]; // 이미 계산했다면 바로 반환

		return memo[n] = fib(n - 1) + fib(n - 2);
	}

	public static void main(String[] args) {

	}

}
