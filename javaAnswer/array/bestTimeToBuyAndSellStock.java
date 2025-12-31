// 주식은 한 반향으로만 움직인다. 그럼으로 계속 가면서 가장 낮은 값을 교체해나가며 현재 가장 낮은 값 - 현재 값 = 이익 이다.
// 이렇게 계산한 이익을 max()를 통해 갱신해나가면 된다.

public class StockSolution {
	public int solution(int[] stocks) {
		// 가장 낮은 가격을 저장할 변수 (최대값으로 초기화)
		int mostLowPrice = Integer.MAX_VALUE;
		// 최대 이익을 저장할 변수 (0 또는 최소값으로 초기화)
		int maxProfit = 0;

		for (int stock : stocks) {
			// 현재 가격과 비교하여 가장 낮은 가격을 갱신
			mostLowPrice = Math.min(mostLowPrice, stock);

			// 현재 가격에서 가장 낮은 가격을 뺀 값(이익)과 기존 최대 이익을 비교하여 갱신
			maxProfit = Math.max(maxProfit, stock - mostLowPrice);
		}

		return maxProfit;
	}

	public static void main(String[] args) {
		StockSolution sol = new StockSolution();
		int[] stocks = {7, 1, 5, 3, 6, 4};

		System.out.println(sol.solution(stocks)); // 결과: 5 (1에 사서 6에 파는 경우)
	}
}