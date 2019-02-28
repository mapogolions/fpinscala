package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestArithmetic {
  @Test
  def testSum(): Unit = {
    assertEquals(ImmutableList.sum(ImmutableList(2, 5)), 7)

    assertThat(
      10,
      allOf(
        equalTo(ImmutableList.sum(ImmutableList(10))),
        equalTo(ImmutableList.sum(ImmutableList(-10, 20))),
        equalTo(ImmutableList.sum(ImmutableList(200, -190)))
      )
    )
  }
}
