# 38) 일정 재구성
import collections
from typing import List

# 만약 문제에 주어진 모든 항공권을 사용해야한다는 조건이 있으면 오일러 경로를 찾으라는 조건이다.
# 오일러 경로는 후위 순회로 구현가능. 현재 문제에서는 그런말이 없긴함.
def solution( tickets : List[List[str]]) -> List[str]:

    route = []
    graph = collections.defaultdict(list)

    # 사전 순서대로 구성해야함으로 sorted를 이용해 정렬
    for a,b in sorted(tickets):
        graph[a].append(b)

    def dfs(a):
        # 오일러 문제 임으로 모든 항공권을 사용해야한다. 그래서 while문을 통해 a에서 출발하는 남아있는 모든 티켓을 소진해야함.
        # JFK에서 시작했고, 만약 JFK : b가 여러개라면 첫번째 루프에서 b[0]의 간선을 모두 조사하고 다시 JFK로 돌아와서 b[1]의 경로를 찾을 것이다.
        while graph[a]:
            dfs(graph[a].pop(0))
        route.append(graph[a])

    dfs("JFK")

    return route[::-1]

# 책 풀이
def solution_book( tickets : List[List[str]] ) -> List[str] :

    graph = collections.defaultdict(list)
    for a , b in sorted(tickets):
        graph[a].append(b)

    route = []

    def dfs(a):
        # 현재 노드 a에서 더이상 갈곳이 없을때까지 재귀 호출을 반복
        while graph[a]:
            dfs(graph[a].pop(0))
        route.append(a)

    dfs("JFK")
    return route[::-1]