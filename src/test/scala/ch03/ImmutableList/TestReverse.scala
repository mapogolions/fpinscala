package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestReverse {
  @Test
  def testAppendLeft(): Unit =
    assertEquals(ImmutableList.appendLeft(ImmutableList(1), ImmutableList(2)), Cons(1, Cons(2, EmptyList)))

  @Test
  def testAppendRight(): Unit =
    assertEquals(
      ImmutableList.appendRight(ImmutableList(1), ImmutableList(2)),
      Cons(1, Cons(2, EmptyList))
    )

  @Test
  def testCopy(): Unit =
    assertEquals(ImmutableList.copy(ImmutableList(1, 3)), Cons(1, Cons(3, EmptyList)))

  @Test
  def testReverse(): Unit = {
    assertEquals(ImmutableList.reverse(ImmutableList('a', 'b', 'c')), ImmutableList('c', 'b', 'a'))

    assertThat(
      ImmutableList(1, -2),
      allOf(
        equalTo(ImmutableList.reverse(Cons(-2, Cons(1, EmptyList)))),
      )
    )
  }
}
