package javaAnswer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  52 ) 이진 탐색 트리 (BST) 합의 범위
 *  리트코드 938
 */

public class RangeSumOfBST {

	// 내풀이 dfs
	public int solution(TreeNode node, int n , int r ){

		if ( node == null ) return 0;

		int left = solution(node.left, n, r);
		int right = solution(node.right, n, r);

		if ( node.val >= n && node.val <= r ) {
			return left + right + node.val;
		}

		return left + right;
	}

	// 책 풀이 BST의 성질을 이용해서 가지치기
	public int solution_book(TreeNode root, int low, int high) {
		if (root == null) return 0;

		// 현재 노드가 범위보다 너무 작으면? 오른쪽만 탐색 (왼쪽은 볼 필요 없음)
		if (root.val < low) {
			return solution_book(root.right, low, high);
		}
		// 현재 노드가 범위보다 너무 크면? 왼쪽만 탐색 (오른쪽은 볼 필요 없음)
		if (root.val > high) {
			return solution_book(root.left, low, high);
		}

		// 범위 내에 있다면? 내 값 + 양쪽 다 탐색한 값
		return root.val + solution_book(root.left, low, high) + solution_book(root.right, low, high);
	}

	public int solution_bfs(TreeNode root, int low, int high) {
		int sum = 0;
		Queue<TreeNode> queue = new LinkedList<>();

		if (root != null) {
			queue.add(root);
		}

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			if (node.val >= low && node.val <= high) {
				// 1. 범위 안에 있으면 값을 더하고, 양쪽 자식 모두 탐색 대상 후보
				sum += node.val;
			}

			// 2. BST 성질을 이용한 가지치기(Pruning)
			// 현재 노드값이 low보다 크다 = 왼쪽 자식 중에 범위 내 숫자가 있을 가능성이 있다
			if (node.val > low && node.left != null) {
				queue.add(node.left);
			}

			// 현재 노드값이 high보다 작다 = 오른쪽 자식 중에 범위 내 숫자가 있을 가능성이 있다
			if (node.val < high && node.right != null) {
				queue.add(node.right);
			}
		}

		return sum;
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
		Integer[] root = new Integer[]{10,5,15,3,7,null,18};
		int n = 7;
		int r = 15;

		RangeSumOfBST app = new RangeSumOfBST();
		TreeNode node = buildNode(root);
		System.out.println(app.solution(node, n , r ));
		System.out.println(app.solution_book(node, n , r ));
		System.out.println(app.solution_bfs(node, n , r ));
	}
}
