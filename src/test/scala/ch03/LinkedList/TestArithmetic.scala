package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestArithmetic {
  @Test
  def testSum(): Unit = {
    assertEquals(MyList.sum(MyList(2, 5)), 7)

    assertThat(
      10,
      allOf(
        equalTo(MyList.sum(MyList(10))),
        equalTo(MyList.sum(MyList(-10, 20))),
        equalTo(MyList.sum(MyList(200, -190)))
      )
    )
  }
}
