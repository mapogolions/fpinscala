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
      MyList.filter(MyList('I', 'p', 'L', 'a', 'Y'))( _.isUpper),
      MyList('I', 'L', 'Y')
    )

    assertEquals(
      MyList.filter(MyList(1, -12, 3, 4))( _ > 0),
      Cons(1, Cons(3, Cons(4, EmptyList)))
    )

    assertEquals(
      MyList.filter(MyList(-1, 0, 10, -3))(_ < 0),
      MyList(-1, -3)
    )
  }
}
