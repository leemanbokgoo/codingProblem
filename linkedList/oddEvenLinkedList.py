# ) 18 홀짝 연결 리스트
class ListNode:
    def __init__(self, val = 0 , next = None ):
        self.val = val
        self.next = next

def solution( head : ListNode ) -> ListNode:

    if not head and not head.next:
        return None

    odd = head
    even = head.next
    even_head = head.next # 현재 짝수 번쨰 가장 앞에 있는 노드 위치를 백업 해둠.

    while even and even.next :
        # 홀수 번째 애들끼리, 짝수번째 애들 끼리 연결
        odd.next, even.next = odd.next.next, even.next.next
        odd, even = odd.next, even.next # 칸 이동

    odd.next = even_head

    # 현재 odd 노드는 홀수번째 가장 마지막 노드를 가리키고 있음으로 head를 반호나
    return head


# 책의 풀이
def solution2( head : ListNode ) -> ListNode:

    if head is None :
        return None

    odd = head # 홀수
    even = head.next # 짝수
    even_head = head.next

    while even and even.next:
        odd.next, even.next = odd.next.next , even.next.next
        odd, even = odd.next, even.next

    odd.next = even_head
    return head

if __name__ == "__main__":
    node = ListNode(1)
    node.next = ListNode(2)
    node.next.next = ListNode(3)
    node.next.next.next = ListNode(4)
    node.next.next.next.next = ListNode(5)

    result_node = solution(node)
    result = ""

    while result_node:
        result += str(result_node.val)
        result_node = result_node.next
        if result_node:
            result += " -> "

    print(result)
