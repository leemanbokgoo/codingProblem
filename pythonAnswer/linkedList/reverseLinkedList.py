# 15 ) 역순 연결 리스트

class ListNode:
    def __init__(self, val = 0 , next = None):
        self.val = val
        self.next = next

# 나 혼자 풀이 : 반복 구조로 풀이
def solution_myselft( node : ListNode ):
    list = ListNode(None)

    while node :
        tmp = node.next
        node.next = list
        list = node
        node = tmp

    return list



def solution( head : ListNode ) -> ListNode:

    def reverse(node : ListNode , prev : ListNode = None) :
        if not node :
            return prev

        next_node, node.next = node.next, prev
        return reverse(next_node, node)

    return reverse(head)


if __name__ == "__main__":
    # 팰린드롬 문자열 입력
    node = ListNode(1)
    node.next = ListNode(2)
    node.next.next = ListNode(3)
    node.next.next.next = ListNode(4)
    node.next.next.next.next = ListNode(5)

    resultList = solution(node)
    result = ""

    while resultList:
        result += str(resultList.val)
        resultList = resultList.next
        if resultList:
            result += " -> "

    print(result)
