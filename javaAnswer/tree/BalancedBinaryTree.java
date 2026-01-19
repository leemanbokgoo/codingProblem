package javaAnswer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  48) 균형 이진트리
 *
 */

public class BalancedBinaryTree {

	public boolean solution(TreeNode node){
		return dfs(node) != -1;
	}

	public int dfs(TreeNode node) {

		if ( node == null ) return 0;

		// 왼쪽 서브 트리 높이 확인
		int leftHeight = dfs(node.left);
		if ( leftHeight == -1 ) return -1;

		// 왼쪽 서브 트리 높이 확인
		int rightHeight = dfs(node.right);
		if ( rightHeight == -1 ) return -1;

		// 모든 노드의 서브트리간의 높이차가 1이하여야하기때문에 각 재귀마다 계산함.
		if ( Math.abs( leftHeight - rightHeight) > 1 ){
			return -1 ;
		}

		return Math.max( leftHeight , rightHeight) + 1;
	}

	public static TreeNode buildTree(Integer[] nodes){
		if( nodes == null || nodes.length == 0 ) return null;

		TreeNode root = new TreeNode(nodes[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		int i = 1;

		while ( !queue.isEmpty() && i < nodes.length ){

			TreeNode node = queue.poll();

			if (nodes[i] != null) {
				node.left = new TreeNode(nodes[i]);
				queue.add(node.left);
			}
			i++;

			if( i < nodes.length && nodes[i] != null ){
				node.right = new TreeNode(nodes[i]);
				queue.add(node.right);
			}
			i++;
		}
		return root;
	}


	public static void main(String[] args) {
		Integer[] nodes = new Integer[] {3, 9, 20, null, null, 15, 7};
		Integer[] nodes2 = new Integer[] {3, 9, 20, null, null, 15, 7};

		BalancedBinaryTree app = new BalancedBinaryTree();
		TreeNode root = buildTree(nodes);
		TreeNode root2 = buildTree(nodes2);
		System.out.println(app.solution(root));
		System.out.println(app.solution(root2));
	}
}
