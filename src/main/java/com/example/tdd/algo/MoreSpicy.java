package com.example.tdd.algo;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
public class MoreSpicy {

  private PriorityQueue<Integer> scoville;
  private Integer count;
  private Integer K;

  public MoreSpicy () {
    this.scoville = new PriorityQueue<>();
    this.count = 0;
  }

  public Integer solution() {
    while (checkMixCondition()) {
      count = addCount(count);
    }
    if (isNotValidCondition()) return -1;
    return count;
  }

  public Integer addCount(Integer count) {
    scoville.add(getMixOfFirstMinAndSecondMin());
    count++;

    return count;
  }

  public Integer getMixOfFirstMinAndSecondMin() {
    Integer mixed = 0;
    mixed+=scoville.poll();
    mixed+=scoville.poll()*2;
    return mixed;
  }

  public boolean checkMixCondition() {
    if (scoville.size() <= 1) return false;
    return scoville.peek() < K;
  }

  public boolean isNotValidCondition() {
    return scoville.size() == 1 && scoville.peek() < K;
  }
}
