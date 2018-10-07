package io.github.mapogolions.fpinscala.ch03.raw

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestAppend {
  @Test
  def testAppend(): Unit = {
    assertEquals(
      MyList.append(MyList(1, 2), Nil),
      MyList(1, 2)
    )

    assertEquals(
      MyList.append(Nil, MyList('a', 'b')),
      MyList('a', 'b')
    )

    assertEquals(
      MyList.append(MyList(1, 2), MyList(10, 11)),
      Cons(1, Cons(2, Cons(10, Cons(11, Nil))))
    )
  }
}
