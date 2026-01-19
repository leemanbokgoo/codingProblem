package javaAnswer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  43 ) 이진 트리의 직경
 *
 */
public class DiameterOfBinaryTree {
	public static int maxDiameter = 0;

	public int solution(TreeNode root){
		dfs(root);
		return maxDiameter;
	}
	public int dfs(TreeNode node){

		if (node == null ) return 0;

		int left = dfs(node.left);
		int right = dfs(node.right);

		maxDiameter = Math.max( maxDiameter , left + right);

		return Math.max(left, right) + 1;
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

	public static void main(String[] args){

		Integer[] array = new Integer[]{1,2,3,4,5};
		TreeNode root = buildTree(array);

		DiameterOfBinaryTree app = new DiameterOfBinaryTree();
		System.out.println(app.solution(root));
	}
}
