package javaAnswer.slidingWindow;

/**
 *  77 ) 가장 긴 반복 문자 대체
 */
public class LongestRepeatingCharacterReplacement {

	// 바꿔야 할 글자가 k개 이하 일때는 가변 크기, 조건을 만족하면 고정 크기 윈도우가 되여 옆으로 이동.
	public int solution(String s, int k) {
		int[] count = new int[26]; // 알파벳 26개의 등장 횟수 저장
		int left = 0;
		int maxCount = 0; // 현재 윈도우 내에서 가장 많이 등장한 특정 문자의 개수
		int maxLength = 0;


		for (int right = 0; right < s.length(); right++) {

			// 1. 새로 들어온 문자의 카운트를 올리고, maxCount를 갱신
			// s.charAt(right) - 'A' : 알파벳 대문자('A'-'Z')를 숫자로 변환하는 공식으로 인덱스를 구하는 것.
			// 만약 현재 문자(s.charAt(right))가 'A' 라면: 65 - 65 = 0
			// ++count[...] : 해당 알파벳 칸에 있는 값을 + 1 하고 더해진 결과 값을 들고옴.
			maxCount = Math.max(maxCount, ++count[s.charAt(right) - 'A']);

			// 2. [핵심 조건] (윈도우 전체 길이 - 가장 많은 문자 개수) > k(정해진 변경 횟수) 라면
			// 즉, 가장 많은 문자를 제외한 나머지(바꿔야 할 문자들)가 K개를 넘었다면 윈도우를 오른쪽으로 한칸 이동.
			// right - left + 1  : 윈도우 크기 구하기. ( 인덱스 사이의 거리가 아니라 그 안에 포함된 칸수(개수)를 구할떄는 반드시 + 1해야함.
			while (right - left + 1 - maxCount > k) {
				count[s.charAt(left) - 'A']--;
				left++;
				// 어차피 새로운 maxLength 를 갱신하기위해선 지금보다 큰 maxCount 값이 나와야한다.
				// 더 작은 값은 어차피 결과에는 영향을 미치지 못하기때문에 굳이 maxCount값을 여기서 갱신 할 필요가 없다.
				// 더 큰 값은 위의 코드에서 maxCount = Math.max() 해줄 것이기 떄문.
			}

			// 3. 조건을 만족하는 윈도우의 최대 길이를 갱신
			maxLength = Math.max(maxLength, right - left + 1);
		}

		return maxLength;
	}
}
