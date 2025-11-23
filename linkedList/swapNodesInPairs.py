# 17 ) 페어의 노드 스왑

class ListNode:
    def __init__(self, val = 0, next = None ):
        self.val = val
        self.next = next

# 내 풀이 값만 교환
def solution( node : ListNode ) -> ListNode:
    head = node

    while node and node.next :
        node.val, node.next.val = node.next.val , node.val
        node = node.next.next

    return head

# 책의 풀이 1 : 재귀 구조
def solution2( node : ListNode) -> ListNode:

    if node and node.next :
        p = node.next
        node.next = solution2(p.next)
        p.next = node
        return p

    return node

# 책의 풀이 2 : 반복 구조로 스왑
def solution3( node : ListNode ) -> ListNode:
    root, prev = ListNode(None)
    prev.next = node

    while node and node.next:

        b = node.next
        node.next = b.next
        b.next = node

        prev.next = b

        node = node.next
        prev = prev.next.next

    return root.next

if __name__ == "__main__":

    node = ListNode(1)
    node.next = ListNode(2)
    node.next.next = ListNode(3)
    node.next.next.next = ListNode(4)

    result_node = solution(node)

    result = ""
    while result_node :
        result += str(result_node.val )

        if result_node.next:
            result += "->"

        result_node = result_node.next

    print(result)