package javaAnswer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  50 ) 정렬된 배열의 이진 탐색 트리 변환
 *  계속 반을 쪼개서 왼쪽 서브 트리와 오른쪽 서브 트리로 나눈다. (트리는 재귀 구조라 마지막 리프 노드를 제외하고는 특정 노드를 기준으로 항상 왼쪽, 오른쪽 서브 트리가 존재하게 됨)
 *  이렇게 하면 왼쪽에 배치될 노드 개수와 오른쪽에 배치될 노드 개수를 항상 비슷하게 맞추게 된다.
 *  트리에서 높이가 높아지는 이유는 한쪽 방향으로만 노드가 쏠리기 떄문인데 반대로, 노드를 양쪽에 최대한 공평하게 나눠주면 트리는 아래로 깊어지는 대신 옆으로 퍼지게 된다.
 *  고로 배열의 정중앙(mid)을 기준으로 나누면, 왼쪽 부분 배열과 오른쪽 부분 배열의 크기 차이는 최대 1개밖에 나지않게 된다.
 *  이진 분할하는 것임.
 */
public class ConvertSortedArrayToBinarySearchTree {

	public TreeNode solution(int[] nums) {
		return dfs(nums, 0, nums.length - 1);
	}

	public TreeNode dfs( int[] nums, int left, int right ){

		if ( left > right ) return null;

		// right - left : 두 지점 사이의 거리
		// 구해서 반으로 나누며 그 반절만큼 left에서 전
		// (left + right) / 2 이런식으로 계산하면 숫자가 커질 경우 에러가 날 수 있음.
		int mid = left +  (right - left) / 2;

		TreeNode node = new TreeNode(nums[mid]);

		node.left = dfs(nums, left, mid - 1);
		node.right = dfs(nums, mid + 1, right);

		return node;
	}

	public static List<Integer> printNode(TreeNode root){
		List<Integer> result = new ArrayList<>();

		if ( root == null ) return result;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			if ( node!= null ){
				result.add(node.val);
				queue.add(node.left);
				queue.add(node.right);
			} else {
				result.add(null);
			}
		}

		while ( !result.isEmpty() && result.get(result.size() -1 ) == null ){
			result.remove(result.size() - 1);
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = new int[] {-10, -3, 0, 5, 9};

		ConvertSortedArrayToBinarySearchTree app = new ConvertSortedArrayToBinarySearchTree();
		TreeNode node = app.solution(nums);
		System.out.println(printNode(node));

	}
}
