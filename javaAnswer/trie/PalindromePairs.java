package javaAnswer.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  57 ) 펠린드롬 페어
 */
public class PalindromePairs {
	class Node {
		int wordIdx = -1; // 단어의 끝인 경우 해당 단어의 index 저장
		List<Integer> palindromeRestIndices = new ArrayList<>(); // 남은 부분이 팰린드롬인 인덱스들
		Node[] children = new Node[26];
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		Node root = new Node();

		// 1. 모든 단어를 트라이에 '역순'으로 삽입
		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i], i);
		}

		List<List<Integer>> result = new ArrayList<>();

		// 2. 각 단어별로 짝이 되는 단어 찾기
		for (int i = 0; i < words.length; i++) {
			search(words, i, root, result);
		}

		return result;
	}

	private void addWord(Node root, String word, int wordIdx) {
		Node curr = root;
		// 단어를 거꾸로 순회하며 트라이 생성
		for (int i = word.length() - 1; i >= 0; i--) {
			// 단어의 앞부분(0~i)이 팰린드롬인지 체크 (Case 3 대응)
			if (isPalindrome(word, 0, i)) {
				curr.palindromeRestIndices.add(wordIdx);
			}
			int c = word.charAt(i) - 'a';
			if (curr.children[c] == null) curr.children[c] = new Node();
			curr = curr.children[c];
		}
		curr.wordIdx = wordIdx;
	}

	private void search(String[] words, int i, Node root, List<List<Integer>> result) {
		Node curr = root;
		String word = words[i];

		for (int j = 0; j < word.length(); j++) {
			// 탐색 중에 단어가 끝났고, 남은 뒷부분이 팰린드롬이면 (Case 2 대응)
			if (curr.wordIdx != -1 && curr.wordIdx != i && isPalindrome(word, j, word.length() - 1)) {
				result.add(Arrays.asList(i, curr.wordIdx));
			}

			int c = word.charAt(j) - 'a';
			if (curr.children[c] == null) return;
			curr = curr.children[c];
		}

		// 탐색 완료 후 (Case 1 또는 Case 3 대응)
		// 1. 길이가 정확히 같거나 역순인 경우
		if (curr.wordIdx != -1 && curr.wordIdx != i) {
			result.add(Arrays.asList(i, curr.wordIdx));
		}
		// 2. 트라이에 더 긴 단어가 있고 그 나머지가 팰린드롬인 경우
		for (int restIdx : curr.palindromeRestIndices) {
			result.add(Arrays.asList(i, restIdx));
		}
	}

	private boolean isPalindrome(String s, int left, int right) {
		while (left < right) {
			if (s.charAt(left++) != s.charAt(right--)) return false;
		}
		return true;
	}
}
