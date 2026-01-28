package javaAnswer.bit;

/**
 *  73 ) UTF-8 검증
 */

public class UTF8Validation {

	// UTF-8은 문자의 길이에 따라 첫 번째 바이트의 앞부분 모양이 달라진다.
	// 1바이트 (0xxxxxxx): 첫 비트가 0.
	// 2바이트 (110xxxxx 10xxxxxx): 첫 바이트가 110으로 시작.
	// 3바이트 (1110xxxx 10xxxxxx 10xxxxxx): 첫 바이트가 1110으로 시작.
	// 4바이트 (11110xxx 10xxxxxx 10xxxxxx 10xxxxxx): 첫 바이트가 11110으로 시작.
	// 나머지 바이트 (Follow-up): 모든 후속 바이트는 반드시 10으로 시작해야함.
	public boolean solution(int[] data) {
		int numberOfBytesToProcess = 0;

		for (int d : data) {
			// 현재 바이트의 상위 비트들만 추출 (8비트만 사용)
			// Integer.toBinaryString : 십진수 정수를 이진수 형태의 문자열(String)로 바꿔주는 함수
			String binaryString = Integer.toBinaryString(d);

			// 1. 데이터 정리: 입력값은 int 지만, UTF-8은 8비트(1바이트) 단위이므로
			// 마지막 8비트만 남기고 나머지는 무시한다. (255는 이진수로 11111111 임으로 & 연산하면 해당 비트 자리는 1이 된다.)
			if (binaryString.length() > 8) {
				d = d & 255; // 하위 8비트만 남김 (11111111_2)
			}

			if (numberOfBytesToProcess == 0) {
				// 새로운 문자의 시작을 판별하는 구간

				// 0xxxxxxx(1바이트 문자) : 7칸 밀었을때 0이면 통과
				if ((d >> 7) == 0b0) {
					numberOfBytesToProcess = 0;

					// 110xxxxx 형태 (2바이트 문자): 5칸 밀었을 때 110이면, 뒤에 1개의 후속 바이트가 더 와야 한다.
				} else if ((d >> 5) == 0b110) {
					numberOfBytesToProcess = 1;

					// 1110xxxx 형태 (3바이트 문자): 4칸 밀었을 때 1110이면, 뒤에 2개의 후속 바이트가 더 와야 한다.
				} else if ((d >> 4) == 0b1110) {
					numberOfBytesToProcess = 2;

					// 11110xxx 형태 (4바이트 문자): 3칸 밀었을 때 11110이면, 뒤에 3개의 후속 바이트가 더 와야 한다.
				} else if ((d >> 3) == 0b11110) {
					numberOfBytesToProcess = 3;

				} else {

					// 위 형식 중 어디에도 해당하지 않으면 잘못된 UTF-8이다.
					return false;
				}

			} else {
				// 문자의 후속 바이트를 검사한다.
				// 모든 후속 바이트는 반드시 10xxxxxx 형태여야 한다. 즉, 6칸 밀었을 때 값이 이진수 '10'.
				if ((d >> 6) != 0b10) {
					return false;
				}
				// 하나 확인했으니 남은 개수를 줄인다.
				numberOfBytesToProcess--;
			}
		}

		// 모든 데이터를 다 읽었는데, 아직 더 읽어야 할 바이트가 남았다면(예: 3바이트짜린데 데이터는 2개뿐) false. 딱 맞게 끝났다면 true를 반환.
		return numberOfBytesToProcess == 0;
	}
}
