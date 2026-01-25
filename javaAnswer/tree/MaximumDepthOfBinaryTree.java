package javaAnswer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  42 ) 이진 트리의 최대 깊이
 *  루트 노드에서 가장 멀리 떨어진 리프 노드까지 몇층인지 묻는 문제.
 */
public class MaximumDepthOfBinaryTree {
	public int solution_anwser( TreeNode root ){

		if ( root == null ) return 0;

		int leftHeight = solution_anwser(root.left);
		int rightHeight = solution_anwser(root.right);

		// 현재 노드의 깊이는 : 오른쪽 , 왼쪽 중 큰 값 + 1(자기 자신)이다.
		// 이것이 부모노드에게는 자신의 상태값(높이)가 된다.
		return Math.max( leftHeight, rightHeight) + 1;
	}

	public static TreeNode buildTree(Integer[] nodes) {
		if (nodes == null || nodes.length == 0) return null;

		// 1. 루트 노드 생성
		TreeNode root = new TreeNode(nodes[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		int i = 1;
		while (!queue.isEmpty() && i < nodes.length) {
			TreeNode current = queue.poll();

			// 2. 왼쪽 자식 연결
			if (nodes[i] != null) {
				current.left = new TreeNode(nodes[i]);
				queue.add(current.left);
			}
			i++;

			// 3. 오른쪽 자식 연결 (배열 범위 체크)
			if (i < nodes.length && nodes[i] != null) {
				current.right = new TreeNode(nodes[i]);
				queue.add(current.right);
			}
			i++;
		}
		return root;
	}

	public static void main(String[] args) {

		Integer[] array = new Integer[]{3, 9, 20, null, null, 15, 7};
		MaximumDepthOfBinaryTree app = new MaximumDepthOfBinaryTree();
		TreeNode tree = buildTree(array);
		System.out.println(app.solution_anwser(tree));

	}
}
