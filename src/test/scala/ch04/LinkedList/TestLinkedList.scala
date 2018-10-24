import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo

import io.github.mapogolions.fpinscala.ch04.linkedlist._


class TestLinkedList {
  @Test
  def updateTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "hello") ls.pushBack(ch)
    assertEquals(ls.getFirst, Some('h'))
    assertTrue(ls.update('H', 0))
    assertEquals(ls.getFirst, Some('H'))
    assertTrue(ls.push('!', ls.size))
    assertEquals(ls.get(ls.size - 1), Some('!'))
    assertTrue(ls.update('?', ls.size - 1))
    assertEquals(ls.getLast, Some('?'))
  }

  @Test
  def pushTest: Unit = {
    val ls = LinkedList[Char]()
    assertFalse(ls.push('a', -1))
    assertFalse(ls.push('a', 100))
    assertFalse(ls.push('a', 1))
    assertTrue(ls.push('a', 0))
    assertEquals(ls.size, 1)

    assertFalse(ls.push('b', 2))
    assertFalse(ls.push('b', -1))
    assertTrue(ls.push('b', 1))
    assertEquals(ls.getFirst, Some('a'))
    assertEquals(ls.getLast, Some('b'))

    assertTrue(ls.push('c', 0))
    assertEquals(ls.getFirst, Some('c'))

    assertTrue(ls.push('d', ls.size))
  }

  @Test
  def pushBackTest: Unit = {
    val ls = LinkedList[Char]()
    for (i <- "maybe") ls.pushBack(i)
    assertEquals("mabye".length, ls.size)
    assertEquals(ls.get(0), Some('m'))
    assertEquals(ls.getLast, Some('e'))
    ls.pushBack('~')
    assertEquals(ls.getLast, Some('~'))
  }

  @Test
  def pushFrontTest: Unit = {
    val ls = LinkedList[Char]()
    for (i <- "maybe") ls.pushFront(i)
    assertEquals("mabye".length, ls.size)
    assertEquals(ls.get(0), Some('e'))
    assertEquals(ls.getLast, Some('m'))
    ls.pushFront('~')
    assertEquals(ls.getFirst, Some('~'))
  }

  @Test
  def removeTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "yes") ls.pushBack(ch)
    assertEquals(ls.size, 3)
    assertEquals(ls.remove(2), Some('s'))
    assertEquals(ls.size, 2)
  }

  @Test
  def getTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "todo") ls.pushBack(ch)
    assertEquals(ls.getLast, Some('o'))
    assertEquals(ls.getFirst, Some('t'))
    assertEquals(ls.get(2), Some('d'))
    ls.removeFirst
    assertEquals(ls.getFirst, Some('o'))
    assertEquals(ls.getFirst, ls.get(0))
    assertEquals(ls.getLast, ls.get(ls.size - 1))
  }

  @Test
  def removeFirstTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "yes") ls.pushFront(ch)
    assertEquals(ls.getLast, Some('y'))
    ls.removeLast
    assertEquals(ls.size, 2)
    assertEquals(ls.removeLast, Some('e'))
    assertEquals(ls.removeLast, Some('s'))
  }

  @Test
  def removeLastTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "yes") ls.pushBack(ch)
    assertEquals(ls.getLast, Some('s'))
    ls.removeLast
    assertEquals(ls.size, 2)
    assertEquals(ls.removeLast, Some('e'))
    assertEquals(ls.removeLast, Some('y'))
  }

  @Test
  def searchTest: Unit = {
    val ls =  LinkedList[Char]()
    for (ch <- "hello") ls.pushBack(ch)
    assertEquals(ls.get(0), Some('h'))
    assertEquals(ls.get(ls.size-1), Some('o'))
  }

  @Test
  def containsTest: Unit = {
    val ls =  LinkedList[Char]()
    for (ch <- "hello") ls.pushBack(ch)
    assertTrue(ls.contains('h'))
    assertFalse(ls.contains('d'))
    assertTrue(ls.contains('o'))
  }

  @Test
  def lastIndexOfTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "hello") ls.pushBack(ch)
    assertEquals(ls.indexOf('o'), ls.size - 1)
    assertEquals(ls.lastIndexOf('l'), 3)
    assertEquals(ls.indexOf('l'), 2)
  }

  @Test
  def indexOfTest: Unit = {
    val ls = LinkedList[Char]()
    ls.pushBack('a')
    ls.pushBack('b')
    ls.pushBack('c')
    assertEquals(ls.indexOf('d'), -1)
    assertEquals(ls.indexOf('a'), 0)
    assertEquals(ls.indexOf('b'), 1)
    ls.clear
    ls.pushFront('a')
    assertEquals(ls.indexOf('a'), 0)
    assertEquals(ls.indexOf('z'), -1)
    ls.pushBack('b')
    ls.pushBack('c')
    ls.push('d', 1)
    assertEquals(ls.indexOf('d'), 1)
  }

  @Test
  def clearTest: Unit = {
    val ls = LinkedList[Int]()
    assertEquals(ls.element, None)
    for (i <- 1 to 100) ls.pushBack(i)
    ls.clear
    assertEquals(ls.size, 0)
    assertEquals(ls.element, None)
  }

  @Test
  def sizeTest: Unit = {
    val ls = LinkedList[Int]()
    for (i <- 1 to 100) ls.pushBack(i)
    assertEquals(ls.size, 100)
    for (i <- 1 to 50) ls.removeFirst
    assertEquals(ls.size, 50)
    assertEquals(ls.element.get.data, 51)
  }
}
