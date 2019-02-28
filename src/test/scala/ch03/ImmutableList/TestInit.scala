package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestInit {
  @Test
  def testInit(): Unit = {
    assertEquals(
      ImmutableList.init(ImmutableList(1, 2)),
      ImmutableList(1)
    )

    assertEquals(
      ImmutableList.init(EmptyList),
      EmptyList
    )

    assertEquals(
      ImmutableList.init(Cons(1, EmptyList)),
      EmptyList
    )
  }
}
