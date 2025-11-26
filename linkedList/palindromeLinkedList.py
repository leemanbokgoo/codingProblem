# 13 ) 펠린드롬 연결 리스트

class ListNode :
    def __init__(self, val = 0, next = None):
        self.val = val
        self.next = next

def solution( node : ListNode ) -> bool:

    # node가 존재하지않거나 node.next가 존재하지않으면 펠린드롬이 아님.
    if node is None and node.next is None :
        return False

    str = []
    # 연결 리스트를 배열로 변경
    while node :
        str.append(node.val)
        node = node.next

    while str :
        # 배열 길이가 1이 될떄까지 밑의 if문에 걸리지않았다는 것은 펠린 드롬이라는 말.
        # 현재 배열의 길이 1에 해당되는 요소는 펠린드롬의 중앙값인 것. ( 해당 문자열이 홀수인 경우)
        if len(str) <= 1 :
            return True

        # 양쪽 끝이 동일한 문자열인지 비교
        if str.pop() != str.pop(0):
            return False

    return True

# 책의 풀이에서는 while len(str) > 1 : 으로 루프를 돌려서 if문을 두번 만들지않음.
#  if str.pop() != str.pop(0): 이 부분을 데크를 이용해 최적화 할 수 있다. 동적 배열은 맨 앞 아이템을 가져오기에 적합한 자료형이 아니다. 시간복잡도 O(n)이 발생하기 때문.

def stringToListNode(input_string: str) -> ListNode:
    # '1->2->3' 형태의 문자열을 연결 리스트로 변환합니다.
    if not input_string:
        return None

    # '->'를 기준으로 문자열을 분리하여 값 목록을 생성
    # 예: "1->2->2->1" -> ['1', '2', '2', '1']
    values = input_string.split('->')

    # 첫 번째 값을 사용하여 head 노드를 생성
    head = ListNode(int(values[0]))
    current = head

    # 나머지 값들을 순회하며 노드를 만들고 연결
    for val_str in values[1:]:
        # 새로운 노드를 만들고 이전 노드의 next에 연결
        current.next = ListNode(int(val_str))
        current = current.next

    return head

if __name__ == "__main__":
    # 팰린드롬 문자열 입력
    input_str_palindrome = "1->2->2->1"
    input_str_not_palindrome = "1->2"

    input1 = stringToListNode(input_str_palindrome)
    input2 = stringToListNode(input_str_not_palindrome)

    print(solution(input1))
    print(solution(input2))

