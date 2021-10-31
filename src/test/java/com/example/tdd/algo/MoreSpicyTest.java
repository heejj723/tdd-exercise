package com.example.tdd.algo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;

public class MoreSpicyTest {

  private MoreSpicy moreSpicy;
  private PriorityQueue<Integer> q;

  /**
   1. 가장 작은 원소를 똑바로 고르는가
   2. 두번째로 작은 원소를 똑바로 고르는가
   3. 1, 2의 결과를 섞을 때 올바른 연산을 하는가
   4. 한번 섞을 때마다 음식의 개수가 -1 되는가
   5. 섞기 전과 섞은 후에 count 지수가 +1 되는가
   6. 모든 스코빌 지수를 K로 만들 수 없는 조건은?
   7. 모든 스코빌 지수가 K 이상을 판단하는 조건은?
   * */

  @BeforeEach
  void setUp() {
    moreSpicy = new MoreSpicy();
    q = new PriorityQueue<>();
    q.offer(1);
    q.offer(2);
    q.offer(3);
  }

  @Test
  void 솔루션_테스트_성공() {
    // given
    q.offer(9);
    q.offer(10);
    q.offer(12);
    moreSpicy.setScoville(q);
    moreSpicy.setK(7);

    // when
    Integer result = moreSpicy.solution();

    // then
    assertAll(
        () -> assertThat(result).isNotEqualTo(-1),
        () -> assertThat(result).isEqualTo(2)
    );
  }

  @Test
  void 섞기_연산_테스트() {
    // given
    moreSpicy.setScoville(q);

    // when
    Integer result = moreSpicy.getMixOfFirstMinAndSecondMin();

    // then
    assertThat(result).isEqualTo(5);
    assertThat(moreSpicy.getScoville().size()).isEqualTo(1);
  }

  @Test
  void 섞은_후_count_올리기() {
    // given
    moreSpicy.setScoville(q);

    // when
    Integer result = moreSpicy.addCount(0);

    // then
    assertAll(
        () -> assertThat(result).isEqualTo(1),
        () -> assertThat(moreSpicy.getScoville().size()).isEqualTo(2)
    );
  }

  @Test
  void 조건_테스트_남은_음식_숫자가_1() {
    // given
    q.clear();
    q.offer(1);
    moreSpicy.setScoville(q);

    // then
    assertThat(moreSpicy.checkMixCondition()).isFalse();
  }

  @Test
  void 조건_테스트_최소_스코빌_K_보다_높을때() {
    // given
    q.clear();
    q.offer(4);
    q.offer(7);
    q.offer(5);
    moreSpicy.setScoville(q);
    moreSpicy.setK(4);
    // then
    assertAll(
        () -> assertThat(moreSpicy.getScoville().size() > 1),
        () -> assertThat(moreSpicy.checkMixCondition()).isFalse()
    );
  }

  @Test
  void 스코빌_지수를_모두_K_이상으로_할수없을때() {
    // given
    q.clear();
    q.offer(4);
    moreSpicy.setScoville(q);
    moreSpicy.setK(5);

    // then
    assertAll(
        () -> assertThat(moreSpicy.isNotValidCondition()).isTrue()
    );
  }



}
