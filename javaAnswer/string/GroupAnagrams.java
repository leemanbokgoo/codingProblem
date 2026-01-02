package javaAnswer.string;

import java.util.*;

// 05 ) 그룹 에너 그럼
// 힌트 : 애너그럼은 정렬하면 똑같은 형태가 된다.
public class GroupAnagrams {

	public List<List<String>> solution(String[] strs) {
		// anagrams = collections.defaultdict(list)
		// 정렬된 문자열을 Key로, 원래 단어들의 리스트를 Value로 저장
		Map<String, List<String>> anagrams = new HashMap<>();

		for (String word : strs) {
			// sortedWord = ''.join(sorted(word))
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String sortedWord = new String(chars);

			// anagrams[sortedWord].append(word)
			// 키가 없으면 새 리스트를 만들고, 있으면 가져와서 단어 추가
			anagrams.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
		}

		// return list(anagrams.values())
		return new ArrayList<>(anagrams.values());
	}


	public static void main(String[] args) {
		GroupAnagrams app = new GroupAnagrams();
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

		List<List<String>> result = app.solution(strs);
		System.out.println(result);
		// 출력 예시: [[eat, tea, ate], [bat], [tan, nat]] (순서는 다를 수 있음)
	}
}