package javaAnswer.hashtable;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

	// # 내 풀이 변환
	public int solution(String s) {
		// stack = {} (여기서는 문자의 위치를 저장하는 맵)
		Map<Character, Integer> stack = new HashMap<>();
		int start = 0;
		int maxLength = 0;

		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char charItem = chars[i];

			// if stack and start < stack[char]
			// 자바에서는 해당 키가 있는지 먼저 확인해야 함
			if (stack.containsKey(charItem) && start <= stack.get(charItem)) {
				start = stack.get(charItem) + 1;
			} else {
				// max_length = max(max_length, i - start + 1)
				maxLength = Math.max(maxLength, i - start + 1);
			}
			// stack[char] = i
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

			// if char in used and start <= used[char] :
			if (used.containsKey(charItem) && start <= used.get(charItem)) {
				start = used.get(charItem) + 1;
			} else {
				maxLength = Math.max(maxLength, index - start + 1);
			}

			// used[char] = index
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