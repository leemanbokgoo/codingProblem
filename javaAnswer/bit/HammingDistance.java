package javaAnswer.bit;

public class HammingDistance {

	public int solution(int x, int y){
		// XOR 연산은 두 비트가 "다를 때"만 1을 남김.
		// 예: 0001 ^ 0100 -> xor은 0101 (십진수 5)이 됩니다.
		int xor = x ^ y;
		int distance = 0;

		// 	xor 이 0이 될떄까지 반복.
		while (xor != 0) {

			// 비트 안의 1의 개수를 세기 위해서는 한자리씩 확인해야한다. 이를 위해 &(AND)연산을 사용한다.
			// 가장 오른쪽 비트가 1인지 확인하기 위해 1과 AND 연산한다.
			// and 연산은 둘다 1일때만 1인데 xor 은 이미 ^ 연산으로 다른 것만 골라내서 다른 부분은 1로 표기된 비트이다.
			// xor의 결과 값에 들어있는 1의 개수는 곧 두 숫자가 서로 달랐던 자릿수와 일치하게 된다.
			// 예를 들어 xor 이 0101라면 0101 & 0001 하게된다. 결과는 0001임으로 끝자리가 1이된다.
			if ((xor & 1) == 1) {
				// 맨 끝이 1이면 서로 달랐다는 뜻
				distance++;
			}

			// 확인한 비트는 버리고 다음 비트를 맨 끝으로 가져와야함으로 오른쪽으로 한 칸 밀기 (0101 -> 0010)
			xor >>= 1;
		}
		return distance;
	}

	// 자바에서 제공하는 비트 카운팅 함수 사용
	public int hammingDistance(int x, int y) {
		// x와 y를 XOR 해서 다른 비트만 1로 만든 뒤, 그 1의 개수를 센다.
		return Integer.bitCount(x ^ y);
	}
	public static void main(String[] args) {

	}
}
