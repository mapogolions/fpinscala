package io.github.mapogolions.fpinscala.ch03.raw

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestZip {
  @Test
  def testAddOne(): Unit = {
    assertEquals(
      MyList.addOne(MyList(2, 21)),
      Cons(3, Cons(22, Nil))
    )
  }

  @Test
  def testConvert(): Unit = {
    assertEquals(
      MyList.convert(MyList(2.0, 20.0)),
      MyList("2.0", "20.0")
    )
  }

  @Test
  def testZip(): Unit = {
    assertEquals(
      MyList.zip(MyList(), MyList(2, 3, 10))(_ + _),
      Nil
    )

    assertEquals(
      MyList.zip(MyList(1, 3, 5), MyList(1, 1, 1))(_ + _),
      Cons(2, Cons(4, Cons(6, Nil)))
    )

    assertEquals(
      MyList.zip(MyList(10), MyList(2, 3, 4, 5))(_ + _),
      Cons(12, Nil)
    )
  }
}
