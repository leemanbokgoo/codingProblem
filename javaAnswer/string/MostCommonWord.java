package javaAnswer.string;

import java.util.*;

// 04 ) 가장 흔한 단어

public class MostCommonWord {

	// # 풀이: Counter 없이 HashMap으로 해결
	public String solution(String paragraph, String[] banned) {
		// 금지 목록을 검색 효율을 위해 Set으로 변환
		Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
		Map<String, Integer> wordCountMap = new HashMap<>();

		// split("\\W+") : \\W는 문자가 아닌 것(Non-Word)을 의미함. + 는 한개 이상이라는 뜻
		// 만약 공백이 여러 개(" ") 있거나, 쉼표와 공백이 같이 있는 경우(", ") 이것들을 각각 나누는 게 아니라, 하나의 큰 덩어리로 묶어서 기준으로 삼겠다는 뜻
		// 소문자로 다 바꾼 다음에(toLowerCase), 알파벳이 아닌 모든 기호나 공백 덩어리들을(\\W+) 기준으로 칼질을 해라(split)라는 뜻
		String[] words = paragraph.toLowerCase().split("\\W+");

		for (String word : words) {
			// 빈 문자열이 아니고 금지 단어가 아닌 경우만 카운트
			if (!word.isEmpty() && !bannedSet.contains(word)) {
				// getOrDefault : word가 Map에 있으면 그 단어의 현재 개수를 가져온다. word가 Map에 기본값으로 지정한 0을 가져온다
				wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
			}
		}

		// max(wordCountList, key=wordCountList.get) 부분
		// entrySet() : map안에 있는 key + value 쌍을 하나하나 꺼내서 set 꾸러미로 만든다.
		// {apple=3, banana=5} 라는 맵이 있다면, entrySet()은 [apple=3]과 [banana=5]라는 두 개의 '엔트리'를 담은 주머니를 준다.
		// Map.Entry.comparingByValue() : 무엇을 기준으로 비교할지 정하는 것으로 value를 기준으로 줄을 세운다.
		// entryset에서 .getKey()를 통해 최종적으로 key만을 꺼낸다.
		// Collections.max() : 에서 첫번째 인자는 정렬을 할 자료구조고, 두번째 인자는 정렬 시 기준을 뜻한다.
		// 고로 다 끝나면 이 함수는 값(Value)이 가장 큰 Map.Entry 덩어리를 통째로 반환한다.
		return Collections.max( wordCountMap.entrySet(), Map.Entry.comparingByValue() ).getKey();
	}

	// # 책의 풀이: 정규식 치환 및 정렬/빈도수 추출 컨셉
	public String solutionCounter(String paragraph, String[] banned) {
		Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

		String cleaned = paragraph.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
		// "\\s+" : 하나 이상의 공백(스페이스, 탭, 줄바꿈 등)
		String[] words = cleaned.split("\\s+");

		Map<String, Integer> counts = new HashMap<>();
		for (String word : words) {
			if (!word.isEmpty() && !bannedSet.contains(word)) {
				counts.put(word, counts.getOrDefault(word, 0) + 1);
			}
		}

		// 가장 큰 값을 가진 엔트리를 찾아 키를 반환
		return Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
	}

	public static void main(String[] args) {
		MostCommonWord app = new MostCommonWord();
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit";
		String[] banned = {"hit"};

		System.out.println("방법 1 결과: " + app.solution(paragraph, banned));         // ball
		System.out.println("방법 2(Counter) 결과: " + app.solutionCounter(paragraph, banned)); // ball
	}
}