package javaAnswer.array;

import java.util.*;
// 08) 빗물 트래핑

public class TrappingRainWater {

	// 내 풀이
	// 코드가 복잡함.
	public static int solution(int[] nums) {
		if (nums == null || nums.length == 0) return 0;

		int totalRain = 0;

		// max_height_index 찾기
		int maxHeightIndex = 0;
		int maxVal = nums[0];


		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > maxVal) {
				maxVal = nums[i];
				maxHeightIndex = i;
			}
		}

		int left = 0;
		int right = 0;

		// 만약 시작이 0인 경우는 물이 담기지않음으로 0이 없는 곳까지 가서 left 포인터를 시작해야함.
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				left = i;
				right = i + 1;
				break;
			}
		}

		// 왼쪽에서 최고점까지 이동
		while (right < maxHeightIndex) {
			if (nums[left] > nums[right]) {
				totalRain += nums[left] - nums[right];
				right++;
			} else if (nums[left] <= nums[right]) {
				left = right;
				right++;
			}
		}

		// 오른쪽 끝에서 최고점까지 이동
		// right 포인터도 시작 할때 right 포인터가 left 포인터보다 큰 상태에서 시작해야 물이 담길 수 있음.
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				right = i + 1;
				left = i;
				break;
			}
		}

		while (left > maxHeightIndex) {
			if (nums[right] > nums[left]) {
				totalRain += nums[right] - nums[left];
				left--;
			} else if (nums[right] <= nums[left]) {
				right = left;
				left--;
			}
		}

		return totalRain;
	}

	// 책의 투포인터 풀이
	// 내 풀이와는 다르게 while문을 한번만 사용하고 0같은 전처리를 신경 쓰지않아도 된다.
	// if문을 통해 왼쪽 포인터 이동, 오른쪽 포인터 이동을 할 수 있어 while문 하나로 처리 가능하다.
	public static int twoPointer(int[] height) {
		if (height == null || height.length == 0) return 0;

		int volume = 0; // 총 물의 양
		int leftIndex = 0;
		int rightIndex = height.length - 1;
		int leftMax = height[leftIndex];
		int rightMax = height[rightIndex];

		// 투 포인터가 만나기전까지 반복
		while (leftIndex < rightIndex) {
			leftMax = Math.max(height[leftIndex], leftMax);
			rightMax = Math.max(height[rightIndex], rightMax);

			// left_max라는 말은 현재 left가 지나온 값들 중에 가장 큰 값임으로 자연스럽게 시작점이 0일시에 max()함수 갱신되면서 사라짐으로 걸러진다.
			// 예를 들어 시작점이 0 이면 left_max에 0 이 들어있을 것이고 height[left_index]도 0이다. 그리고 만약 1이 된다면 위에서 left_max가 갱신됨으로
			// left_max - height[left_index] 해도 1 - 1이다.
			if (leftMax <= rightMax) {
				volume += leftMax - height[leftIndex];
				leftIndex++;
			} else {
				volume += rightMax - height[rightIndex];
				rightIndex--;
			}
		}
		return volume;
	}

	// 풀이 2 stack
	public static int trap(int[] height) {
		Deque<Integer> stack = new ArrayDeque<>();
		int volume = 0;

		// 참고로 stack에는 막대의 길이 값이 아니라 index가 들어감.
		for (int i = 0; i < height.length; i++) {
			// 스택이 비어있지않고 현재 막대의 값이 stack의 top보다 높으면 물이 고일 수 있는 구간이 발생.
			// stack.peek() : 스택에서 가장 마지막 요소, 가장 최근에 들어간 요소. [3,7,10]이면 10이다.
			// 이 조건 때문에 반복문이 계속 돌아감. 유의할 것.
			// height[i] > height[stack.peek()] : 물이 고일 수 있는 구간이 생겼다는 뜻.
			while (!stack.isEmpty() && height[i] > height[stack.peek()]) {

				// 스택에서 꺼낸다.
				// stack.pop()하면 가장 마지막에 넣은 값을 꺼낼 수 있다
				// 여기서 주의해야할 점 pop하면 값이 사라지지만 stack.peek()은 값을 확인하는 거라서 값이 남아있다.
				int top = stack.pop();

				// 꺼낸 후 스택이 비어있다면
				if (stack.isEmpty()) {
					// 좌측 막대가 없다는 뜻임으로 물을 못 담는다.
					break;
				}

				// 가로폭 계산
				// 물의 양을 곱할때 가로폭 * 높이로 구한다.
				// 고로 i(현재 위치) - stack.peek()(이전위치)를 빼야한다. 여기서 계산식에 막대의 위치를 포함하지않도록 추가로 -1 해야함.
				int distance = i - stack.peek() - 1;
				// i(현재 위치) stack[-1](이전 위치) 중 낮은 쪽의 길이를 구한다. 거기서 top 위치의 막대 높이를 빼준다.
				// 막대 높이만큼은 물을 채울 수 없기때문에 제외해준다.
				int waters = Math.min(height[i], height[stack.peek()]) - height[top];

				// 가로 * 세로 = 현재 막대에서 채울 수 있는 물의 양이다.
				// 주의해야할 점은 물을 세로로 채운다고 생각하면 안된다. 가로로 채운다고 생각해야 이해가 쉽다.
				// [5,2,0,0,3,6]의 경우 while문이 돌면서 0,4,3,8 이렇게 채우게 되는데 그림을 그려보면 이해가 쉽다.
				// 3에서 while문이 돌기 시작하고  2, 0, 0
				// , 3 에서 4만큼 채우고 5, 2, 0, 0, 3 에서 3 만큼 채우고 5, 2, 0, 0, 3, 6에서 8만큼 채운다.
				// 이렇게 되면 계산식이 2(낮은 쪽) * 4 , 3 * 1 , 5 * 2 이런식으로 된다.
				// 이렇게 되는 이유는 2 높이에서 3까지의 거리 2 만큼 채운다. 이렇게 되면 2, 0, 0 , 3은 빗물이 다 차있다.
				// 그러니까 3(낮은 쪽이 기준이다) - 2를 하면 높이는 1이다. 여기서 5와 3까지의 거리만큼 곱한다 3 * 1
				// 이제 3 높이 까지는 다 찼다 그럼 이제 5의 높이까지 채워야한다.  5 - 3 = 2 , 5와 6까지의 거리는 4다. 2 * 4
				volume += distance * waters;
			}
			stack.push(i);
		}
		return volume;
	}

	public static void main(String[] args) {
		int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
		int[] nums9 = {5, 2, 0, 0, 3, 6};

		System.out.println("Solution 1: " + solution(nums));
		System.out.println("Two Pointer: " + twoPointer(nums));
		System.out.println("Stack (nums9): " + trap(nums9));
	}
}