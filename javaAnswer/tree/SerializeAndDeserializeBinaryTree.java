package javaAnswer.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 47 ) 이진 트리 직렬화 & 역직렬화
 * 기존의 이진트리를 배열로 직렬화하고 반대로 역직렬화하여 출력하는 방식은 BFS를 이용한 직렬화/역직렬화임으로 DFS(전위 순회)를 사용해보도록 함.
 */
public class SerializeAndDeserializeBinaryTree {

	// 직렬화: 루트 -> 왼쪽 -> 오른쪽 순으로 문자열 생성
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	private void buildString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("null,");
		} else {
			sb.append(node.val).append(",");
			buildString(node.left, sb);
			buildString(node.right, sb);
		}
	}

	// 역직렬화: 문자열을 큐에 넣고 재귀적으로 복원
	public TreeNode deserialize(String data) {
		Queue<String> nodes = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(",")));
		return buildTree(nodes);
	}

	private TreeNode buildTree(Queue<String> nodes) {
		String val = nodes.poll();
		if (val == null || val.equals("null") || val.isEmpty()) return null;

		TreeNode node = new TreeNode(Integer.parseInt(val));
		node.left = buildTree(nodes);
		node.right = buildTree(nodes);
		return node;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		SerializeAndDeserializeBinaryTree ser = new SerializeAndDeserializeBinaryTree();
		SerializeAndDeserializeBinaryTree deser = new SerializeAndDeserializeBinaryTree();

		// 2. 직렬화 (Tree -> String)
		String serializedData = ser.serialize(root);
		System.out.println("직렬화 결과 (String): " + serializedData);

		// 3. 역직렬화 (String -> Tree)
		TreeNode deserializedRoot = deser.deserialize(serializedData);

		// 4. 복원 확인을 위한 재직렬화 출력
		String finalCheck = ser.serialize(deserializedRoot);
		System.out.println("복원 후 다시 직렬화: " + finalCheck);

		// 결과 비교
		if (serializedData.equals(finalCheck)) {
			System.out.println("=> 성공: 트리가 완벽하게 복원되었습니다!");
		}
	}
}
