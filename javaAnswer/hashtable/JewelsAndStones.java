package javaAnswer.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 29 ) 보석과 돌
 */

public class JewelsAndStones {

	// # 내 풀이
	public int solution(String J, String StringS) {
		Map<Character, Integer> table = new HashMap<>();
		int count = 0;

		// J에 있는 보석 종류를 테이블에 등록
		for (char charJ : J.toCharArray()) {
			// getOrDefault : 값을 가져오되 key가 없으면 default로 지정해둔 값을 들고옴.
			table.put(charJ, table.getOrDefault(charJ, 0) + 1);
		}

		// S를 돌면서 해당 돌이 보석 테이블에 있는지 확인
		for (char charS : StringS.toCharArray()) {
			if (table.getOrDefault(charS, 0) > 0) {
				count += 1;
			}
		}
		return count;
	}

	// # 책 풀이
	public int solution2(String J, String StringS) {
		Map<Character, Integer> freqs = new HashMap<>();
		int count = 0;

		for (char charJ : J.toCharArray()) {
			freqs.put(charJ, freqs.getOrDefault(charJ, 0) + 1);
		}

		for (char charS : StringS.toCharArray()) {
			// 보석이 아니면 0을 더하게 됨
			count += freqs.getOrDefault(charS, 0);
		}
		return count;
	}

	public static void main(String[] args) {
		JewelsAndStones app = new JewelsAndStones();
		String S = "aAAbbbb";
		String J = "aA";

		System.out.println("내 풀이 결과: " + app.solution(J, S));    // 3
		System.out.println("책 풀이 결과: " + app.solution2(J, S));   // 3
	}
}