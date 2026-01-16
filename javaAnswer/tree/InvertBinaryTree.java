package javaAnswer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  45 ) 이진 트리 반전
 */
public class InvertBinaryTree {












	// 책 풀이 : DFS
	public TreeNode solution( TreeNode node ){

		if (node == null ) return null;

		TreeNode tmp = node.right;
		node.right = node.left;
		node.left = tmp;

		solution(node.left);
		solution(node.right);
		return node;
	}

	// 큐 방식으로 풀이
	public TreeNode solutionQueue(TreeNode root) {
		if (root == null) return null;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();

			// 큐에서 꺼낸 노드의 왼쪽, 오른쪽을 바꿈
			TreeNode temp = current.left;
			current.left = current.right;
			current.right = temp;

			// 자식이 있다면 큐에 추가 (다음에 얘네 자식들도 바꿔야 하니까)
			if (current.left != null) queue.add(current.left);
			if (current.right != null) queue.add(current.right);
		}
		return root;
	}

	public static TreeNode buildTreeNode( Integer[] nodes){

		if ( nodes == null || nodes.length ==0 ) return null;

		TreeNode root = new TreeNode(nodes[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		int i = 1;

		while (!queue.isEmpty() && i < nodes.length ) {

			TreeNode node = queue.poll();

			if (nodes[i] != null) {
				node.left = new TreeNode(nodes[i]);
				queue.add(node.left);
			}
			i++;

			if (nodes[i] != null && i < nodes.length) {
				node.right = new TreeNode(nodes[i]);
				queue.add(node.right);
			}
			i++;
		}
		return root;
	}

	public static List<Integer> treeToArrayList(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) return result;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		// 1. 일단 모든 노드(null 포함)를 큐 방식으로 리스트 삽입.
		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			if (current != null) {
				result.add(current.val);
				queue.add(current.left);
				queue.add(current.right);
			} else {
				// 중간에 자식이 없는 경우에는 null
				result.add(null);
			}
		}

		// 리스트의 마지막 요소가 null인 동안 계속 pop(remove)
		while (!result.isEmpty() && result.get(result.size() - 1) == null) {
			result.remove(result.size() - 1);
		}

		return result;
	}
	public static void main(String[] args){

		Integer[] input = new Integer[]{ 4,2,7,1,3,6,9};
		TreeNode root = buildTreeNode(input);
		InvertBinaryTree app = new InvertBinaryTree();
		TreeNode result = app.solution(root);
		System.out.println(treeToArrayList(result));
	}
}
