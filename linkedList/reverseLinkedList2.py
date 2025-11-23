# 19 ) 역순 연결 리스트 2
class ListNode:
    def __init__(self, val = 0 , next = None):
        self.val = val
        self.next = next

# 이 풀이는 다른 데서 쓰일 것 같아서 외워두는 게 좋을듯
def solution( head : ListNode, m : int, n : int ) -> ListNode:

    if not head and m == n :
        return head

    # 값을 셋팅
    root = start = ListNode(None)

    # start 를 역순으로 뒤집어야하는 노드의 바로 이전 노드까지 이동.
    for _ in range( m - 1 ):
        start = start.next
    # end는 start 다음 노드로 셋팅.
    # start.next가 end 노드가 되는 이유는 역순으로 뒤집고 나면 이 노드가 가장 뒤에 있어야하기때문에 end노드다.
    end = start.next

    for _ in range( m - n ):
        # 현재 m번째 노드를 임시로 저장.
        tmp = start.next
        # m-1자리에 (즉 역순으로 뒤집어야하는 구간의 바로 이전 노드)에 end.next 노드를 넣어준다.
        # 즉, end.next노드를 뽑아서 start 바로 다음으로 이동시킨다. 이런식으로 end뒤에 있는 노드를 순서대로 하나씩 가장 앞으로 옮겨준다.
        # 그러면 1 -> 2 -> 3 -> 4이 주어지지고  2 -> 3-> 4 구간을 이렇게 역순으로 뒤집어야할때 1은 start지점, 2는 end지점이 된다.
        # 1 -> 3 -> 2 -> 4
        # 1 -> 4 -> 3 - > 2
        # 이런식으로 계속 end(2)뒤에 있는 3과 4를 뽑아서 start(1)뒤로 옮겨주다보면 역순이 된다.
        start.next = end.next
        # 기존의 end.next는 start.next로 연결 되었음으로 기존의 연결을 지우기위해 end.next를 새로 연결한다.
        # end시점에서 바라보았을때는 end.next가 사라졌으니 그 다음 리스트를 end와 연결 한다. 즉, end(2)와 나머지 리스트(end.next.next)를 연결하는 작업이다.
        end.next = end.next.next
        # 새로 뽑아서 앞으로 데리고온 온 노드(start.next) 뒤에 기존의 첫노드(tmp)를 연결한다.
        # 1 -> 3 과 2 -> 4 , 1 -> 4 와 3 -> 2를 연결 하는 작업에 해당되는 부분.
        start.next.next = tmp

    return root.next

# 책풀이
def solution2( head : ListNode, m : int, n : int ) -> ListNode:

    if not head or m == n :
        return head

    root = start = ListNode(None)
    root.next = head

    for _ in range(m-1):
        start = start.next

    end = start.next

    for _ in range(n-m):
        tmp, start.next, end.next = start.next, end.next, end.next.next
        start.next.next = tmp

    return root.next

if __name__ == "__main__":
    node = ListNode(1)
    node.next = ListNode(2)
    node.next.next = ListNode(3)
    node.next.next.next = ListNode(4)
    node.next.next.next.next = ListNode(5)

    m, n = 2, 4

    result_node = solution(node, m, n)
    result = ""

    while result_node:
        result += str(result_node.val)
        result_node = result_node.next
        if result_node:
            result += " -> "

    print(result)