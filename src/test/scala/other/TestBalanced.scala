package io.github.mapogolions.fpinscala.other

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestBalanced {
  import Token._
  @Test
  def testBalanced: Unit = {
    assertTrue(balanced apply "()(){}[(){}]")
    assertFalse(balanced apply "([{}()]){[(])}")
    assertTrue(balanced apply "([{}()]){[]}")
    assertTrue(balanced apply "([{}()])")
    assertTrue(balanced apply "[{}]")
    assertTrue(balanced apply "{}")
    assertTrue(balanced apply "[]")
    assertTrue(balanced apply "()")
    assertTrue(balanced apply "")
  }

  @Test
  def testTokenize: Unit = {
    assertEquals(tokenize("".toList), Nil)
    assertEquals(
      tokenize("(]{}]}".toList), 
      LParen::RSqBrac::LBrac::RBrac::RSqBrac::RBrac::Nil
    )
  }
}
