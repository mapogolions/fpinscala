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
      MyList.foldLeft(MyList(3, 7, 23), 0)((a, b) => a + b),
      33
    )

  @Test
  def testFoldRight(): Unit =
    assertEquals(
      MyList.foldRight(MyList(3, 4, 9), 0)((a, b) => a + b),
      16
    )

  @Test
  def testFoldRightInTermsOfLeft(): Unit =
    assertEquals(
      MyList.foldRightInTermsOfLeft(MyList(3, 4, 9), 0)((a, b) => a + b),
      16
    )

  @Test
  def testFoldLeftInTermsOfRight(): Unit =
    assertEquals(
      MyList.foldLeftInTermsOfRight(MyList(3, 4, 9), 0)((a, b) => a + b),
      16
    )
}
