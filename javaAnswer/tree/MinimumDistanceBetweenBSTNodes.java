package javaAnswer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  53 ) 이진 탐색 트리 노드 간 최소 거리
 */
public class MinimumDistanceBetweenBSTNodes {
	int minDiff;
	Integer prev;

	public int solution(TreeNode root) {
		// 테스트 케이스마다 초기화
		minDiff = Integer.MAX_VALUE;
		prev = null;

		dfs(root);
		return minDiff;
	}

	private void dfs(TreeNode node) {
		if (node == null) return;
		dfs(node.left);

		if (prev != null) {
			minDiff = Math.min(minDiff, node.val - prev);
		}
		prev = node.val;

		dfs(node.right);
	}

	public static TreeNode buildNode(Integer[] nodes) {

		if ( nodes == null ) return null;

		TreeNode root = new TreeNode(nodes[0]);
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		int i = 1;

		while (!q.isEmpty() && i < nodes.length)  {

			TreeNode node = q.poll();

			if (nodes[i] != null) {
				node.left = new TreeNode(nodes[i]);
				q.add(node.left);
			}
			i++;

			if (nodes[i] != null) {
				node.right = new TreeNode(nodes[i]);
				q.add(node.right);
			}
			i++;
		}

		return root;
	}

	public static void main(String[] args) {

		Integer[] root = new Integer[] {4, 2, 6, 1, 3, null, null};
		MinimumDistanceBetweenBSTNodes app = new MinimumDistanceBetweenBSTNodes();

		TreeNode node = buildNode(root);
		System.out.println(app.solution(node));

	}
}
