package javaAnswer.trie;

/**
 * 56 ) 트라이 구현
 */
public class implementTire {

	// 노드 구조 정의
	class Node {
		Node[] children = new Node[26]; // 알파벳 26개 공간
		boolean isEnd = false;          // 단어의 완성 지점 표시
	}

	private Node root;

	public implementTire() {
		root = new Node();
	}

	// 1. 단어 삽입 (Insert)
	public void insert(String word) {
		Node curr = root;
		for (char c : word.toCharArray()) {
			int idx = c - 'a'; // 'a'를 0번 인덱스로 변환
			if (curr.children[idx] == null) {
				curr.children[idx] = new Node();
			}
			curr = curr.children[idx];
		}
		curr.isEnd = true; // 단어가 끝났음을 표시
	}

	// 2. 단어 검색 (Search)
	public boolean search(String word) {
		Node node = find(word);
		// 노드가 존재하고, 그곳에 단어 끝 깃발(isEnd)이 꽂혀있어야 함
		return node != null && node.isEnd;
	}

	// 3. 접두사 검색 (StartsWith) - 트라이의 존재 이유!
	public boolean startsWith(String prefix) {
		// 끝 깃발 상관없이 경로만 존재하면 true
		return find(prefix) != null;
	}

	// 공통 로직: 문자열을 따라가며 마지막 노드를 반환
	private Node find(String str) {
		Node curr = root;
		for (char c : str.toCharArray()) {
			int idx = c - 'a';
			if (curr.children[idx] == null) return null; // 길 없음
			curr = curr.children[idx];
		}
		return curr;
	}
}
