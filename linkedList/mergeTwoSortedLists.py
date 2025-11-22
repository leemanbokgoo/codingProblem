# 14 ) 두 정렬 리스트의 병합

class ListNode:
    def __init__(self, val = 0 , next = None):
        self.val = val
        self.next = next

# 힌트 받아서 푼 풀이
# 외우는 것이 좋을 듯
def solution( node : ListNode, node2 : ListNode ) -> ListNode:

    if (not node) or ( node2 and node.val > node2.val ) :
        node, node2 = node2, node

    if node :
        node.next = solution(node.next , node2)

    return node


if __name__ == "__main__":
    # 팰린드롬 문자열 입력
    node = ListNode(1)
    node.next = ListNode(2)
    node.next.next = ListNode(4)

    node2 = ListNode(1)
    node2.next = ListNode(3)
    node2.next.next = ListNode(4)

    resultList = solution(node, node2)
    result = ""

    while resultList:
        result += str(resultList.val)
        resultList = resultList.next
        if resultList:
            result += " -> "

    print(result)


