package io.github.mapogolions.fpinscala.ch03.raw

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo


class TestTree {
  @Test
  def testSize(): Unit = {
    assertEquals(BST.size(BST.insert(Node(34, Empty, Empty), 45)), 2)
    assertEquals(BST.size(Node(34, Empty, Empty)), 1)
    assertEquals(BST.size[Int](Empty),0)
  }

  @Test
  def testInsert(): Unit = {
    assertEquals(
      BST.insert(Node(100, Empty, Node(102, Empty, Empty)), 101),
      Node(100, Empty, Node(102, Node(101, Empty, Empty), Empty))
    )

    assertEquals(
      BST.insert(Node(100, Empty, Empty), 89),
      Node(100, Node(89, Empty, Empty), Empty)
    )

    assertEquals(
      BST.insert(Empty, 34),
      Node(34, Empty, Empty)
    )

    assertEquals(
      BST.insert(Node(100, Empty, Empty), 102),
      Node(100, Empty, Node(102, Empty, Empty))
    )
  }

  @Test
  def testMembers(): Unit = {
    assertTrue(BST.members(Node(50, Empty, Empty), 50))
    assertFalse(BST.members(Node(50, Empty, Node(90, Empty, Empty)), 89))
  }
}
