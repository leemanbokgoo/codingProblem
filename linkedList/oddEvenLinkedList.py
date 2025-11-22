
class ListNode:
    def __init__(self, val = 0 , next = None ):
        self.val = val
        self.next = next

def solution( head : ListNode ) -> ListNode:
    return None














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
    # 팰린드롬 문자열 입력
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
