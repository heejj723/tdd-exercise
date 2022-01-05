# tdd-exercise

TDD 방법론을 연습하기 위하여, 비교적 인풋과 아웃풋이 명확한 문제를 풀어보기로 하였습니다. </br>
프로그래머스 42626 번 문제를 풀이하였습니다. </br>

[문제 출처](https://programmers.co.kr/learn/courses/30/lessons/42626)

![스크린샷 2022-01-05 오전 11 22 33](https://user-images.githubusercontent.com/45758481/148150622-95f4a966-5f61-4dac-86eb-083ba4ee1b29.png)


## Test
- 두 음식을 섞었을 때 맵기 지수가 예상치로 나오는가?
- 섞은 횟수를 카운팅 하는가?
- 예외처리
  - 남은 음식이 하나일 때
  - 최소 스코빌 지수가 K 보다 높을 때
  - 스코빌 지수를 모두 K 이상으로 만들 수 없을 때 


## TDD 적용 전
```cpp
#include <string>
#include <vector>
#include <iostream>
#include <queue>
using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;
    
    priority_queue<int, vector<int>, greater<int> > pq;
    for(auto s : scoville){
        pq.push(s);
    }
    
    int mixed = 0;
    while(pq.top() < K){
        mixed+= pq.top();
        pq.pop();
        mixed+= (pq.top()*2);
        pq.pop();
        pq.push(mixed);
        
        mixed = 0;
        answer++;
        
        if(pq.size() == 1 && pq.top() < K)  return -1;
        
    }
    return answer;
}
```




### TDD (Java)

```java
import java.util.*; 

class Solution {
    private PriorityQueue<Integer> scoville;
    private Integer count;
    private Integer K;
    
    public int solution(int[] scov, int K) {
        scoville = new PriorityQueue<Integer>();
        for (int s : scov) {
            scoville.add(s);
        }
        this.K = K;
        this.count = 0;
        
        while (checkMixCondition()) {
          count = addCount(count);
          if (isNotValidCondition()) return -1;
        }
        return count;
    }
    
    private Integer addCount(Integer count) {
        scoville.add(getMixOfFirstMinAndSecondMin());
        count++;

        return count;
    }

    private Integer getMixOfFirstMinAndSecondMin() {
        Integer mixed = 0;
        mixed+=scoville.poll();
        mixed+=scoville.poll()*2;
        return mixed;
    }

    private boolean checkMixCondition() {
        return scoville.size() > 1 && scoville.peek() < K;
    }

    private boolean isNotValidCondition() {
        return scoville.size() == 1 && scoville.peek() < K;
    }
}

```
