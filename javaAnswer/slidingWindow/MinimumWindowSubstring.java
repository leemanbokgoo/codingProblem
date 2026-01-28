package javaAnswer.slidingWindow;

/**
 * 76 ) 부분 문자열이 포함된 최소 윈도우
 *
 */

public class MinimumWindowSubstring {

	// 오른쪽 범위를 넓혀서 조건을 만족시키고 왼쪽을 깍아서 최소 길이를 찾는 방식.
	public String solution(String s, String t){
		if (s.length() < t.length()) return "";

		// 1. t에 필요한 문자들의 개수를 저장 (아스키 코드,영문자 기준 128개)
		int[] targetMap = new int[128];
		for (char c : t.toCharArray()) targetMap[c]++;

		// 2. 필요한 변수 세팅
		int left = 0, right = 0; // 윈도우의 양 끝 포인터
		int minLen = Integer.MAX_VALUE;
		int startIdx = 0; // 가장 짧은 구간의 시작 지점을 저장하는 변수
		int requiredCount = t.length(); // 채워야 할 총 문자의 개수

		// 3. 오른쪽 포인터(right)를 하나씩 밀면서 확장
		while (right < s.length()) {
			char rightChar = s.charAt(right);

			// 현재 문자가 t에 필요한 문자였다면, 요구 수치(requiredCount)를 줄임
			// targetMap[rightChar] > 0 이여야 현재 필요한 문자인 것. (targetMap[rightChar] 가 0 이라면 필요하지않은 상태
			if (targetMap[rightChar] > 0) {
				requiredCount--;
			}

			// 해당 문자열이 윈도우에 포함되었음을 표시하기위해 -1 하여 값을 뺸다.
			targetMap[rightChar]--;
			right++;

			// 4. 조건을 만족했을 때(t의 모든 문자가 윈도우 안에 들어옴)
			while (requiredCount == 0) {

				// 현재까지의 구간이 최소 길이라면 갱신
				if (right - left < minLen) {
					minLen = right - left;
					// 최소 구간을 찾았음으로 left 범위를 줄이기위해 startIndex 에 left를 넣어줌.
					startIdx = left;
				}

				// 현재 left에 있는 문자열을 찾는다. 해당 문자열을 뺄 것임.
				char leftChar = s.charAt(left);

				// 위에서 해당 문자열이 윈도우에 포함되어있음을 표시하기위해 -1 했는데 이제 해당 문자열(leftChar)을 뺼꺼니까 targetMap 값을 + 1
				targetMap[leftChar]++;

				// 만약 윈도우에서 뺀 문자가 t에 꼭 필요한 문자였다면 targetMap[leftChar]값이 0보다 커진다.
				if (targetMap[leftChar] > 0) {
					requiredCount++; // 다시 채워야 하므로 요구 수치 증가
				}
				// 왼쪽 범위 한칸 줄임.
				left++;
			}
		}

		return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);
	}
}
