package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestMap {
  @Test
  def testMap(): Unit = {
    assertEquals(
      ImmutableList.flatMap(ImmutableList(1, 2, 3, 4))(x => ImmutableList(x, x)),
      ImmutableList(1, 1, 2, 2, 3, 3, 4, 4)
    )

    assertEquals(
      ImmutableList.map(ImmutableList(1, 2, 3, 4))(x => x * x),
      Cons(1, Cons(4, Cons(9, Cons(16, EmptyList))))
    )
  }
}
