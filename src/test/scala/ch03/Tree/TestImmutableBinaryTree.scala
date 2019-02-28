package io.github.mapogolions.fpinscala.ch03

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestImmutableBinaryTree {
  val tree = Node(
    15,
    Node(
      10, 
      Node(5, Leaf, Leaf), 
      Node(12, Leaf, Leaf)
    ),
    Node(
      25, 
      Leaf, 
      Node(50, Leaf, Leaf)
    )
  )

  @Test
  def testInsert(): Unit = {
    assertEquals(tree insert 15, tree)
    assertEquals(Leaf insert 1, Node(1, Leaf, Leaf))
    assertEquals(
      Node(10, Node(5, Leaf, Leaf), Node(15, Leaf, Leaf)).insert(20),
      Node(10, Node(5, Leaf, Leaf), Node(15, Leaf, Node(20, Leaf, Leaf)))
    )
  }

  @Test
  def testOrder(): Unit = {
    assertEquals(tree.inOrder, Seq(5, 10, 12, 15, 25, 50))
    assertEquals(tree preOrder, Seq(15, 10, 5, 12, 25, 50))
    assertEquals(tree postOrder, Seq(5, 12, 10, 50, 25, 15))
  }

  @Test
  def testMember(): Unit = {
    assertTrue(tree member 15)
    assertTrue(tree member 10)
    assertTrue(tree member 50)
    assertTrue(tree member 25)
    assertFalse(tree member 100)
  }

  @Test
  def testSize(): Unit = {
    assertEquals(Node(34, Leaf, Leaf).size, 1)
    assertEquals(Node(1, Leaf, Node(3, Leaf, Leaf)).size, 2)
  }
}
