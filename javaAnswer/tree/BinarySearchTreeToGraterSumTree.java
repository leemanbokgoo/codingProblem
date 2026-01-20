package javaAnswer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  51 ) 이진 탐색 트리(BST)를 더 큰 수 합계 트리로
 */
public class BinarySearchTreeToGraterSumTree {

	private int sum = 0;

	public TreeNode solution( TreeNode root ){
		sum = 0;
		dfs(root);
		return root;
	}

	public void dfs(TreeNode node) {
		if ( node == null ) return;
		dfs(node.right);
		sum += node.val;
		node.val = sum;
		dfs(node.left);
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

	public static List<Integer> arrayToList(TreeNode root) {

		List<Integer> result = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while ( !q.isEmpty()){
			TreeNode node = q.poll();

			if (node != null) {
				result.add(node.val);
				q.add(node.left);
				q.add(node.right);
			} else {
				result.add(null);
			}
		}

		while ( !result.isEmpty() && result.get(result.size() - 1 ) == null ){
			result.remove(result.size() - 1);
		}

		return result;
	}

	public static void main(String[] args){

		Integer[] nodes = new Integer[]{4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
		TreeNode node = buildNode(nodes);

		BinarySearchTreeToGraterSumTree app = new BinarySearchTreeToGraterSumTree();

		TreeNode resultNode = app.solution(node);
		System.out.println(arrayToList(resultNode));

	}
}
