package io.github.mapogolions.fpinscala.ch03.raw

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestDrop {
  @Test
  def testDrop(): Unit = {
    assertEquals(MyList.drop(MyList(1, 2, 3), 3), Nil)
    assertThat(
      MyList(1, -2),
      allOf(
        equalTo(MyList.drop(MyList(1, -2), 0)),
        equalTo(MyList.drop(MyList(1, -2), -1)),
        equalTo(MyList.drop(MyList(1, -2), -10)),
      )
    )
  }
}
