package javaAnswer.greedy;

/**
 * 78 ) 주식을 사고 팔기 가장 좋은 시점
 */
public class BestTimeToBuyAndSellStock {

	// 탐욕적 선택 속성 : 오늘 가격이 어제 보다 높아서 이익을 챙기는 선택이 전체 문제의 해를 구하는데 문제가 되지않음.(주식을 무제한으로 사고 팔수 있음으로)
	// 최적 부분 구조 : 전체 기간의 최대 수익은 결국 매일 발생하는 수익의 합으로 쪼개서 구할 수 있다.
	public int solution(int[] prices) {
		int maxProfit = 0;

		// 인덱스 1부터 시작하여 이전 날짜(right-1)와 현재 날짜(right)를 비교
		// 마치 크기가 2인 고정 윈도우를 한 칸씩 미는 것과 같습니다.
		for (int i = 1; i < prices.length; i++) {
			// 오늘 가격이 어제 가격보다 높다면?
			if (prices[i] > prices[i - 1]) {
				// 그 차익(상승분)을 수익에 누적합니다.
				maxProfit += prices[i] - prices[i - 1];
			}
		}

		return maxProfit;
	}

	public static void main(String[] args){
		int[] prices = {7, 1, 5, 3, 6, 4};
	}
}
