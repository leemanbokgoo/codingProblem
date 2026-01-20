package javaAnswer.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 54 ) 전위, 중위 순회 결과로 이진 트리 구축
 *
 */
public class ConstructBinaryTreeFromPreorderAndInOrderTraversal {
	private Map<Integer, Integer> inMap;


	// preOrder로 루트 만들고 inOrder을 통해 오른쪽 왼쪽 구분
	public TreeNode solution( int[] preorder, int[] inorder ){

		// HashMap에 inorder의 [값 : 인덱스]를 미리 저장
		inMap = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}
		return helper(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
	}

	private TreeNode helper(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
		// base case: 더 이상 처리할 노드가 없을 때
		if (preStart > preEnd || inStart > inEnd) return null;

		// 2. preorder의 첫 번째 값이 현재 서브트리의 루트
		int rootVal = preorder[preStart];
		TreeNode root = new TreeNode(rootVal);

		// 3. inorder에서 루트의 위치를 찾음
		int inRootIndex = inMap.get(rootVal);

		// 4. 왼쪽 서브트리에 몇 개의 노드가 있는지 계산
		// leftSize : 왼쪽 서브 트리 노드의 갯수
		int leftSize = inRootIndex - inStart;

		// 5. 재귀적으로 왼쪽과 오른쪽 서브트리 구축
		// 왼쪽: preorder는 루트 다음부터 leftSize만큼, inorder는 시작부터 루트 전까지
		root.left = helper(preorder, preStart + 1, preStart + leftSize, inStart, inRootIndex - 1);

		// 오른쪽: preorder는 왼쪽 끝난 지점부터 마지막까지, inorder는 루트 다음부터 마지막까지
		root.right = helper(preorder, preStart + leftSize + 1, preEnd, inRootIndex + 1, inEnd);

		return root;
	}
}
