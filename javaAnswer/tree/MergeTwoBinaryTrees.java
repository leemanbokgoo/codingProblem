package javaAnswer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  46 ) 두 이진 트리 병합
 *
 */
public class MergeTwoBinaryTrees {

	public TreeNode solution( TreeNode node1 , TreeNode node2){

		// 종료 조건
		if ( node1 == null && node2 == null ) return null;

		if ( node1 == null ) return node1;
		if ( node2 == null ) return node2;

		TreeNode newNode = new TreeNode(node1.val + node2.val);

		newNode.right = solution(node1.right, node2.right);
		newNode.left = solution(node1.left, node2.left);

		return newNode;
	}


	public static List<Integer> treeToArrayList(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null ) return result;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()){
			TreeNode current = queue.poll();
			if ( current != null ){
				result.add(current.val);
				queue.add(current.left);
				queue.add(current.right);
			}else {
				result.add(null);
			}
		}

		while( !result.isEmpty() && result.get(result.size()  - 1) == null ){
			result.remove(result.size() - 1);
		}

		return result;
	}

	public static TreeNode buildTreeNode( Integer[] nodes){
		if ( nodes == null ) return null;

		Queue<TreeNode> q = new LinkedList<>();
		TreeNode root = new TreeNode(nodes[0]);
		q.add(root);

		int i = 1;

		while (!q.isEmpty() && i < nodes.length) {
			TreeNode node = q.poll();

			if ( nodes[i] != null) {
				node.left = new TreeNode(nodes[i]);
				q.add(node.left);
			}
			i++;
			if ( i < nodes.length && nodes[i] != null) {
				node.right = new TreeNode(nodes[i]);
				q.add(node.right);
			}
			i++;
		}

		return root;
	}
	public static void main(String[] args) {
		MergeTwoBinaryTrees app = new MergeTwoBinaryTrees();

		Integer[] node1Array = new Integer[] {1, 3, 2, 5};
		Integer[] node2Array = new Integer[] {2, 1, 3, null, 4, null,7};

		TreeNode node1 = buildTreeNode(node1Array);
		TreeNode node2 = buildTreeNode(node2Array);

		TreeNode result = app.solution(node1, node2);
		System.out.println(treeToArrayList(result));
	}
}
