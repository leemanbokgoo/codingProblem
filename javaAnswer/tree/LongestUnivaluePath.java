package javaAnswer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  44 ) 가장 긴 동일 값의 경로
 */
public class LongestUnivaluePath {

	public static int max = 0;

	public int solution( TreeNode node ){
		dfs(node);
		return max;
	}

	public int dfs( TreeNode node){

		if(node == null) return 0;

		int left = dfs(node.left);
		int right = dfs(node.right);

		int leftPath = 0;
		int rightPath = 0;

		// 둘 중 값이 같은 쪽만 보내므로 각각 + 1
		if (node.left != null && node.left.val == node.val) leftPath = left + 1;
		if (node.right != null && node.right.val == node.val) rightPath = right + 1;

		max = Math.max(max, leftPath + rightPath);
		return Math.max(leftPath, rightPath);
	}

	public static TreeNode buildTree( Integer[] nodes){

		// 예외 처리
		if ( nodes == null && nodes.length == 0) return null;

		TreeNode root = new TreeNode(nodes[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		int i = 1;

		while ( !queue.isEmpty() && i < nodes.length ){
			TreeNode node = queue.poll();

			if ( node != null ){
				node.left = new TreeNode(nodes[i]);
				queue.add(node.left);
			}
			i++;

			if ( i < nodes.length && node != null ){
				node.right = new TreeNode(nodes[i]);
				queue.add(node.right);
			}
			i++;
		}

		return root;
	}

	public static void main(String[] ars){
		Integer[] input = new Integer[]{5,4,5,1,1,5};
		TreeNode root = buildTree(input);
		LongestUnivaluePath app = new LongestUnivaluePath();
		System.out.println(app.solution(root));
	}
}
