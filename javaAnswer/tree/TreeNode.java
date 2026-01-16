package javaAnswer.tree;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	// 생성자 1: 기본
	TreeNode() {}

	// 생성자 2: 값만 넣을 때
	TreeNode(int val) {
		this.val = val;
	}

	// 생성자 3: 값과 자식 노드들을 동시에 연결할 때
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
