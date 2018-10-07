package io.github.mapogolions.fpinscala.ch03.raw

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestReverse {
  @Test
  def testAppendLeft(): Unit =
    assertEquals(MyList.appendLeft(MyList(1), MyList(2)), Cons(1, Cons(2, Nil)))

  @Test
  def testAppendRight(): Unit =
    assertEquals(
      MyList.appendRight(MyList(1), MyList(2)),
      Cons(1, Cons(2, Nil))
    )

  @Test
  def testCopy(): Unit =
    assertEquals(MyList.copy(MyList(1, 3)), Cons(1, Cons(3, Nil)))

  @Test
  def testReverse(): Unit = {
    assertEquals(MyList.reverse(MyList('a', 'b', 'c')), MyList('c', 'b', 'a'))

    assertThat(
      MyList(1, -2),
      allOf(
        equalTo(MyList.reverse(Cons(-2, Cons(1, Nil)))),
      )
    )
  }
}
