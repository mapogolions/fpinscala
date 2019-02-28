package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestFilter {
  @Test
  def testFilter(): Unit = {
    assertEquals(
      ImmutableList.filter(ImmutableList('I', 'p', 'L', 'a', 'Y'))( _.isUpper),
      ImmutableList('I', 'L', 'Y')
    )

    assertEquals(
      ImmutableList.filter(ImmutableList(1, -12, 3, 4))( _ > 0),
      Cons(1, Cons(3, Cons(4, EmptyList)))
    )

    assertEquals(
      ImmutableList.filter(ImmutableList(-1, 0, 10, -3))(_ < 0),
      ImmutableList(-1, -3)
    )
  }
}
