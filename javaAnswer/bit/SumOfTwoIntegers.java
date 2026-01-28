package javaAnswer.bit;

/**
 *  72 ) 두 정수의 합
 *
 */
public class SumOfTwoIntegers {

	// 비트 끼리 더할때 0101 + 0111라면 1100이 됨. 2가 되면 올림값 1이 되는 식으로 계산.
	// 올림(Carry)이 없는 합: XOR(^) 연산으로 처리
	// 올림(Carry) 값: AND(&) 연산 후 왼쪽으로 1비트 시프트(<<) 하여 구한다.
	public int solution(int a, int b) {

		// 받아올림(b)이 0이 될 때까지 반복.
		while (b != 0) {
			// & : 둘 다 1일 때만 1을 내뱉는 연산.
			// a & b 연산 후 왼쪽으로 한칸 민다.
			// & 연산 결과 0110 일때 << 1 하면 1100 이 된다.
			int carry = (a & b) << 1;

			// ^ : 비트 두개가 서로 다르면 1, 같으면 0 으로 만듬.
			a = a ^ b;

			// b에 올림 값을 할당하여 다음 루프에서 더한다.
			b = carry;
		}

		return a;
	}
	public static void main(String[] args) {

	}
}
