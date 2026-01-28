package javaAnswer.bit;

/**
 *  74 ) 비트의 개수
 */
public class NumberOf1Bits {


	// 비트 시프트(비트 밀기) 방법 : 정석적인 방법.
	public int solution(int n) {
		int count = 0;

		//  i < 32 : 자바에서 int 타입은 무조건 32비트 임으로
		for (int i = 0; i < 32; i++) {

			// 맨 오른쪽 비트가 1인지 확인
			if ( (n & 1) != 0 ) {
				count++;
			}
			// 오른쪽으로 한 칸 밀기
			n >>= 1;
		}

		return count;
	}

	// Brian Kernighan의 알고리즘. 0인 비트는 건너뛰고 1인 비트의 개수만큼만 루프를 돈다.
	public int solution2(int n) {
		
		int count = 0;

		while (n != 0) { // n에 있는 비트 1을 하나씩 지우면 n은 0이 된다.
			// n과 n-1을 AND 연산하면 가장 오른쪽에 있는 1이 제거되는데 n & (n-1)을 한 번 실행할 때마다, 숫자 n에 들어있는 1이 무조건 딱 하나씩 사라진다.
			n &= (n - 1);
			count++;
		}
		return count;
	}


	public static void main(String[] args) {

	}
}
