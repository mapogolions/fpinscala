package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestZip {
  @Test
  def testAddOne(): Unit = {
    assertEquals(
      ImmutableList.addOne(ImmutableList(2, 21)),
      Cons(3, Cons(22, EmptyList))
    )
  }

  @Test
  def testConvert(): Unit = {
    assertEquals(
      ImmutableList.convert(ImmutableList(2.0, 20.0)),
      ImmutableList("2.0", "20.0")
    )
  }

  @Test
  def testZip(): Unit = {
    assertEquals(
      ImmutableList.zip(ImmutableList(), ImmutableList(2, 3, 10))(_ + _),
      EmptyList
    )

    assertEquals(
      ImmutableList.zip(ImmutableList(1, 3, 5), ImmutableList(1, 1, 1))(_ + _),
      Cons(2, Cons(4, Cons(6, EmptyList)))
    )

    assertEquals(
      ImmutableList.zip(ImmutableList(10), ImmutableList(2, 3, 4, 5))(_ + _),
      Cons(12, EmptyList)
    )
  }
}
