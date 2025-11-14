from typing import List
# 08 ) 빗물 트래핑

# 내 풀이
# 코드가 너무 복잡함.
def solution( nums : List[int]) -> int :

    total_rain = 0 # 전체 물의 양을 잴 변수
    max_height_index =  nums.index(max(nums))

    # 만약 시작이 0인 경우는 물이 담기지않음으로 0이 없는 곳까지 가서 left 포인터를 시작해야함.
    for i, value in enumerate(nums):
        if value != 0:
            left = i
            right = i + 1
            break

    while right < max_height_index :

        if nums[left] > nums[right]:
            total_rain += nums[left] - nums[right]
            right += 1

        elif nums[left] < nums[right] or nums[left] == nums[right]:
            left = right
            right += 1

    # right 포인터도 시작 할때 right 포인터가 left 포인터보다 큰 상태에서 시작해야 물이 담길 수 있음.
    for i in range(len(nums) - 2, -1, -1):
        if nums[i] < nums[ i + 1 ]:
            right = i + 1
            left = i
            break

    while left > max_height_index :
        if nums[right] > nums[left]:
            total_rain += nums[right] - nums[left]
            left -= 1

        elif nums[right] < nums[left] or nums[right] == nums[left]:
            right = left
            left -= 1
    return total_rain


# 책의 투포인터 풀이
# 내 풀이와는 다르게 while문을 한번만 사용하고 0같은 전처리를 신경 쓰지않아도 된다.
# if문을 통해 왼쪽 포인터 이동, 오른쪽 포인터 이동을 할 수 있어 while문 하나로 처리 가능하다.
def tow_pointer(height : List[int]) -> int :
    if not height :
        return 0

    volume = 0 # 총 물의 양
    left_index, right_index = 0, len(height) -1
    left_max , right_max = height[left_index] , height[right_index]

    # 투 포인터가 만나기전까지 반복,
    while left_index < right_index :

        left_max , right_max = max(height[left_index], left_max),max(height[right_index],right_max)

        # left_max라는 말은 현재 left가 지나온 값들 중에 가장 큰 값임으로 자연스럽게 시작점이 0일시에 max()함수 갱신되면서 사라짐으로 걸러진다.
        # 예를 들어 시작점이 0 이면 left_max에 0 이 들어있을 것이고 height[left_index]도 0이다. 그리고 만약 1이 된다면 위에서 left_max가 갱신됨으로
        #  left_max - height[left_index] 해도 1 - 1이다.
        if left_max <= right_max:
            volume += left_max - height[left_index]
            left_index += 1

        else:
            volume += right_max - height[right_index]
            right_index -= 1

    return volume

# 풀이 2 stack
def trap(hegiht : List[int]) -> int :
    stack = []
    volume = 0

    ## 참고로 stack에는 막대의 길이 값이 아니라 index가 들어감.
    for i in range(len(hegiht)):

        # 스택이 비어있지않고 현재 막대의 값이 stack의 top보다 높으면 물이 고일 수 있는 구간이 발생.
        # stack[-1] : 스택에서 가장 마지막 요소, 가장 최근에 들어간 요소. [3,7,10]이면 10이다.
        # 이 조건 때문에 반복문이 계속 돌아감. 유의할 것.
        # hegiht[i] > hegiht[stack[-1]] : 물이 고일 수 있는 구간이 생겼다는 뜻.
        while stack and hegiht[i] > hegiht[stack[-1]]:
            # 스택에서 꺼낸다
            # stack.pop()하면 가장 마지막에 넣은 값을 꺼낼 수 있다.
            # @@@ 여기서 주의해야할 점 pop하면 값이 사라지지만 stack[-1]은 값을 확인하는 거라서 값이 남아있다.
            top = stack.pop()
            # 꺼낸 후 스택이 비어있다면
            if not len(stack):
                # 좌측 막대가 없다는 뜻임으로 물을 못 담는다
                break

            # 가로폭을 구하는 코드
            # 물의 양을 곱할때 가로폭 * 높이로 구한다.
            # 고로 i(현재 위치) - stack[-1](이전 위치) 를 빼야 한다. 여기서 계산식에 막대의 위치를 포함하지않도록 추가로 -1 해야함.
            distance = i - stack[-1] - 1
            # i(현재 위치) stack[-1](이전 위치) 중 낮은 쪽의 길이를 구한다. 거기서 top 위치의 막대 높이를 빼준다.
            # 막대 높이만큼은 물을 채울 수 없기때문에 제외해준다.
            waters = min(hegiht[i], hegiht[stack[-1]]) - hegiht[top]

            # 가로 * 세로 = 현재 막대에서 채울 수 있는 물의 양이다.
            # 주의해야할 점은 물을 세로로 채운다고 생각하면 안된다. 가로로 채운다고 생각해야 이해가 쉽다.
            # [5, 2,0,0,3,6]의 경우 while문이 돌면서 0,4,3,8 이렇게 채우게 되는데 그림을 그려보면 이해가 쉽다.
            # 3에서 while문이 돌기 시작하고  2, 0, 0 , 3 에서 4만큼 채우고 5, 2, 0, 0, 3 에서 3 만큼 채우고 5, 2, 0, 0, 3, 6에서 8만큼 채운다.
            # 이렇게 되면 계산식이 2(낮은 쪽) * 4 , 3 * 1 , 5 * 2 이런식으로 된다.
            # 이렇게 되는 이유는 2 높이에서 3까지의 거리 2 만큼 채운다. 이렇게 되면 2, 0, 0 , 3은 빗물이 다 차있다.
            # 그러니까 3(낮은 쪽이 기준이다) - 2를 하면 높이는 1이다. 여기서 5와 3까지의 거리만큼 곱한다 3 * 1
            # 이제 3 높이 까지는 다 찼다 그럼 이제 5의 높이까지 채워야한다.  5 - 3 = 2 , 5와 6까지의 거리는 4다. 2 * 4
            volume += distance * waters

        stack.append(i)
    return volume




# 실행 테스트
if __name__ == "__main__":
    nums = [ 0,1,0,2,1,0,1,3,2,1,2,1]
    nums2 = [5, 2, 3, 1, 4]
    nums3 = [3, 0, 1, 0, 5, 0, 2]
    nums4 = [1,2,3,4]
    nums5 = [4,3,2,1]
    nums6 = [2,0,2]
    nums7 =  [3,0,1,3]
    nums8 = [3,0,5,0,5,1,3]
    nums9 = [5,2,0,0,3,6]
    # print(solution(nums))
    # print(solution(nums2))
    # print(solution(nums3))
    # print(solution(nums4))
    # print(solution(nums5))
    # print(solution(nums6))
    # print(solution(nums7))
    # print(solution(nums8))
    print(trap(nums9))

