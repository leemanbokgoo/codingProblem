import sys
from typing import List

# 주식은 한 반향으로만 움직인다. 그럼으로 계속 가면서 가장 낮은 값을 교체해나가며 현재 가장 낮은 값 - 현재 값 = 이익 이다.
# 이렇게 계산한 이익을 max()를 통해 갱신해나가면 된다.
def solution( stocks : List[int]) -> int:

    most_low_price = sys.maxsize
    max_profit = -sys.maxsize

    for stock in stocks:
        most_low_price = min(stock, most_low_price)
        max_profit = max(max_profit, stock- most_low_price)

    return max_profit

# 실행 테스트
if __name__ == "__main__":
    stocks = [ 7,1,5,3,6,4]
    print(solution(stocks))