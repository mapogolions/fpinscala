package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestFold {
  @Test
  def testFoldLeft(): Unit =
    assertEquals(
      ImmutableList.foldLeft(ImmutableList(3, 7, 23), 0)((a, b) => a + b),
      33
    )

  @Test
  def testFoldRight(): Unit =
    assertEquals(
      ImmutableList.foldRight(ImmutableList(3, 4, 9), 0)((a, b) => a + b),
      16
    )

  @Test
  def testFoldRightInTermsOfLeft(): Unit =
    assertEquals(
      ImmutableList.foldRightInTermsOfLeft(ImmutableList(3, 4, 9), 0)((a, b) => a + b),
      16
    )

  @Test
  def testFoldLeftInTermsOfRight(): Unit =
    assertEquals(
      ImmutableList.foldLeftInTermsOfRight(ImmutableList(3, 4, 9), 0)((a, b) => a + b),
      16
    )
}
