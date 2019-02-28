package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestDrop {
  @Test
  def testDrop(): Unit = {
    assertEquals(
      ImmutableList.drop(ImmutableList(1, 2, 3), 3), 
      EmptyList
    )
    
    assertThat(
      ImmutableList(1, -2),
      allOf(
        equalTo(ImmutableList.drop(ImmutableList(1, -2), 0)),
        equalTo(ImmutableList.drop(ImmutableList(1, -2), -1)),
        equalTo(ImmutableList.drop(ImmutableList(1, -2), -10)),
      )
    )
  }
}
