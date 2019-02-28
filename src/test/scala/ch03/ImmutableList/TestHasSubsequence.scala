package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestHasSubsequence {
  // Simple cases
  @Test
  def testHasSubsequence(): Unit = {
    assertTrue(ImmutableList.hasSubsequence(EmptyList, EmptyList))
    assertFalse(ImmutableList.hasSubsequence(EmptyList, Cons(1, EmptyList)))
    assertTrue(ImmutableList.hasSubsequence(ImmutableList(1, 2, 3), ImmutableList(1)))
    assertTrue(ImmutableList.hasSubsequence(ImmutableList(1, 2, 3), ImmutableList(1, 2, 3)))

    // Difficult cases
    assertTrue(ImmutableList.hasSubsequence(ImmutableList('a', 'b', 'c', 'd', 'e', 'f'), ImmutableList('c', 'd')))
    assertFalse(ImmutableList.hasSubsequence(ImmutableList('a', 'b', 'c', 'd', 'e', 'f'), ImmutableList('c', 'f')))
    assertTrue(ImmutableList.hasSubsequence(ImmutableList('a', 'c', 'c', 'd', 'e', 'f'), ImmutableList('c', 'd')))
    assertTrue(ImmutableList.hasSubsequence(ImmutableList('a', 'c', 'c', 'e', 'f', 'f'), ImmutableList('e', 'f')))
  }
}
