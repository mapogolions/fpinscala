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
      MyList.init(MyList(1, 2)),
      MyList(1)
    )

    assertEquals(
      MyList.init(EmptyList),
      EmptyList
    )

    assertEquals(
      MyList.init(Cons(1, EmptyList)),
      EmptyList
    )
  }
}
