package javaAnswer.hashtable;
import java.util.HashMap;
import java.util.Map;

/**
 * 30 ) 중복 문자 없는 가장 긴 부분 문자열
 */
public class LongestSubstringWithoutRepeatingCharacters {

	// # 내 풀이
	public int solution(String s) {
		Map<Character, Integer> stack = new HashMap<>(); // 문자의 위치를 저장하는 맵
		int start = 0;
		int maxLength = 0;

		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char charItem = chars[i];

			// 중복 문자가 발생했고, 그 위치가 현재 슬라이딩 윈도우(start 이후) 안에 있다면
			if (stack.containsKey(charItem) && start <= stack.get(charItem)) {
				// 윈도우의 시작점을 중복된 문자 바로 다음 칸으로 옮겨서 중복을 제거함
				start = stack.get(charItem) + 1;

				// 현재 윈도우의 길이(i - start + 1)를 계산하여 최대값 갱신
			} else {
				maxLength = Math.max(maxLength, i - start + 1);
			}

			// 현재 문자의 위치를 최신화.
			stack.put(charItem, i);
		}

		return maxLength;
	}

	// # 책 풀이 변환
	public int solution2(String s) {
		Map<Character, Integer> used = new HashMap<>();
		int maxLength = 0;
		int start = 0;

		// for index, char in enumerate(s):
		char[] chars = s.toCharArray();
		for (int index = 0; index < chars.length; index++) {
			char charItem = chars[index];

			if (used.containsKey(charItem) && start <= used.get(charItem)) {
				start = used.get(charItem) + 1;
			} else {
				maxLength = Math.max(maxLength, index - start + 1);
			}
			used.put(charItem, index);
		}

		return maxLength;
	}

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters app = new LongestSubstringWithoutRepeatingCharacters();

		System.out.println(app.solution("abcabcbb")); // 3
		System.out.println(app.solution("bbbbb"));    // 1
		System.out.println(app.solution("pwwkew"));   // 3
	}
}