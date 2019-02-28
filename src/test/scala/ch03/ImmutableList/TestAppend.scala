package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestAppend {
  @Test
  def testAppend(): Unit = {
    assertEquals(
      ImmutableList.append(ImmutableList(1, 2), EmptyList),
      ImmutableList(1, 2)
    )

    assertEquals(
      ImmutableList.append(EmptyList, ImmutableList('a', 'b')),
      ImmutableList('a', 'b')
    )

    assertEquals(
      ImmutableList.append(ImmutableList(1, 2), ImmutableList(10, 11)),
      Cons(1, Cons(2, Cons(10, Cons(11, EmptyList))))
    )
  }
}
