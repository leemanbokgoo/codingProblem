# 27 ) k개 정렬 리스트 병합
import heapq
from typing import List


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def solution2( lists : List[ListNode] ) -> ListNode:

    root = result = ListNode(None)
    heap = []

    # 각 연결 리스트의 첫번째 노드를 우선 순위 큐에다가 넣어줌.
    for i in range(len(lists)):
        if lists[i]:
            heapq.heappush(heap, ( lists[i].val , i , lists[i]))

    while heap :
        # 첫번째 노드들은 우선 순위 큐에 넣었기때문에 가장 작은 순서부터 차례대로 들어가있음.
        # 고로 heappop하면 현재 heap에 들어간 노드 중 가장 작은 노드가 나옴.
        node = heapq.heappop(heap)
        index = node[1]
        # 가장 작은 노드를 결과 연결 리스트에 넣어줌.
        result.next = node[2]

        # result 를 한칸당긴다.
        result = result.next

        # 현재 result는 위에서 넣어준 node[2] 이 node[2]에 연결 되어있는 next값이 곧 result.next가 된다.
        # result.next 값이 존재한다면 아직 연결 리스트에 값이 존재한다는 이야기가 됨으로 heap에 해당 값을 넣어준다.
        if result.next:
            heapq.heappush(heap, ( result.next.val , index , result.next))

    return root.next


# 책 풀이 우선순위 큐 활용
def solution2( lists : List[ListNode] ) -> ListNode:

    root = result = ListNode(None)
    heap = []

    for i in range(len(lists)):

        if lists[i]:
            heapq.heappush(heap, ( lists[i].val, i, lists[i]))

    while heap:
        node = heapq.heappop(heap)
        idx = node[1]
        result.next = node[2]

        result = result.next

        if result.next:
            heapq.heappush(heap, (result.next.val, idx, result.next))

    return root.next
