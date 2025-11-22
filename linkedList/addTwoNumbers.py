# 16 ) 두수의 덧셈

class ListNode:
    def __init__(self, val = 0 , next = None):
        self.val = val
        self.next = next

# 내 풀이
def solution(node1 : ListNode, node2 : ListNode):

    node1_str = ""
    node2_str = ""
    while node1:
        node1_str += str(node1.val)
        node1 = node1.next

    while node2:
        node2_str += str(node2.val)
        node2 = node2.next

    node1_str = node1_str[::-1]
    node2_str = node2_str[::-1]

    sum_result = int(node1_str) + int(node2_str)

    sum_result_list = list(str(sum_result))
    prev : ListNode = None
    for number in sum_result_list[::-1] :
        node = ListNode(number)
        node.next = prev
        prev = node

    return node

# 전가산기로 구현 (책 풀이 )
def solution2(l1: ListNode, l2: ListNode) -> ListNode:

    root = head = ListNode(0)
    carry = 0

    while l1 or l2 or carry:
        sum_val = 0
        if l1:
            sum_val += l1.val
            l1 = l1.next

        if l2:
            sum_val += l2.val
            l2 = l2.next

        carry, val = divmod(sum_val + carry, 10)

        head.next = ListNode(val)
        head = head.next
    return root.next

def reverse(head: ListNode) -> ListNode:
    prev = None
    curr = head
    while curr:
        next, curr.next = curr.next, prev
        prev, curr = curr, next
    return prev

if __name__ == "__main__":
    # 팰린드롬 문자열 입력
    node = ListNode(2)
    node.next = ListNode(4)
    node.next.next = ListNode(3)

    node2 = ListNode(5)
    node2.next = ListNode(6)
    node2.next.next = ListNode(4)

    resultList = solution(node, node2)
    result = ""

    while resultList:
        result += str(resultList.val)
        resultList = resultList.next
        if resultList:
            result += " -> "

    print(result)




