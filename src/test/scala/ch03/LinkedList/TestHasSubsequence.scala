package io.github.mapogolions.fpinscala.ch03.raw

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestHasSubsequence {
  // Simple cases
  @Test
  def testHasSubsequence(): Unit = {
    assertTrue(MyList.hasSubsequence(Nil, Nil))
    assertFalse(MyList.hasSubsequence(Nil, Cons(1, Nil)))
    assertTrue(MyList.hasSubsequence(MyList(1, 2, 3), MyList(1)))
    assertTrue(MyList.hasSubsequence(MyList(1, 2, 3), MyList(1, 2, 3)))

    // Difficult cases
    assertTrue(MyList.hasSubsequence(MyList('a', 'b', 'c', 'd', 'e', 'f'), MyList('c', 'd')))
    assertFalse(MyList.hasSubsequence(MyList('a', 'b', 'c', 'd', 'e', 'f'), MyList('c', 'f')))
    assertTrue(MyList.hasSubsequence(MyList('a', 'c', 'c', 'd', 'e', 'f'), MyList('c', 'd')))
    assertTrue(MyList.hasSubsequence(MyList('a', 'c', 'c', 'e', 'f', 'f'), MyList('e', 'f')))
  }
}
