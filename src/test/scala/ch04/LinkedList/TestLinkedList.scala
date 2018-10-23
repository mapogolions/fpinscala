import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo

import io.github.mapogolions.fpinscala.ch04.linkedlist._


class TestLinkedList {
  @Test
  def removeTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "yes") ls.add(ch)
    assertEquals(ls.size, 3)
    assertEquals(ls.remove(2), Some('s'))
    assertEquals(ls.size, 2)
  }

  @Test
  def getTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "todo") ls.add(ch)
    assertEquals(ls.getLast, Some('o'))
    assertEquals(ls.getFirst, Some('t'))
    assertEquals(ls.get(2), Some('d'))
    ls.removeFirst
    assertEquals(ls.getFirst, Some('o'))
    assertEquals(ls.getFirst, ls.get(0))
    assertEquals(ls.getLast, ls.get(ls.size - 1))
  }

  @Test
  def lastAndLastButOneTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "yes") ls.add(ch)
    assertEquals(ls.getLast, Some('s'))
    ls.removeLast
    assertEquals(ls.size, 2)
    assertEquals(ls.removeLast, Some('e'))
    assertEquals(ls.removeLast, Some('y'))
  }

  @Test
  def searchTest: Unit = {
    val ls =  LinkedList[Char]()
    for (ch <- "hello") ls.add(ch)
    assertEquals(ls.get(0), Some('h'))
    assertEquals(ls.get(ls.size-1), Some('o'))
  }

  @Test
  def containsTest: Unit = {
    val ls =  LinkedList[Char]()
    for (ch <- "hello") ls.add(ch)
    assertTrue(ls.contains('h'))
    assertFalse(ls.contains('d'))
    assertTrue(ls.contains('o'))
  }

  @Test
  def lastIndexOfTest: Unit = {
    val ls = LinkedList[Char]()
    for (ch <- "hello") ls.add(ch)
    assertEquals(ls.indexOf('o'), ls.size - 1)
    assertEquals(ls.lastIndexOf('l'), 3)
    assertEquals(ls.indexOf('l'), 2)
  }

  @Test
  def indexOfTest: Unit = {
    val ls = LinkedList[Char]()
    ls.add('a')
    ls.add('b')
    ls.add('c')
    assertEquals(ls.indexOf('d'), -1)
    assertEquals(ls.indexOf('a'), 0)
    assertEquals(ls.indexOf('b'), 1)
  }

  @Test
  def clearTest: Unit = {
    val ls = LinkedList[Int]()
    assertEquals(ls.element, None)
    for (i <- 1 to 100) ls.add(i)
    ls.clear
    assertEquals(ls.size, 0)
    assertEquals(ls.element, None)
  }

  @Test
  def sizeTest: Unit = {
    val ls = LinkedList[Int]()
    for (i <- 1 to 100) ls.add(i)
    assertEquals(ls.size, 100)
    for (i <- 1 to 50) ls.removeFirst
    assertEquals(ls.size, 50)
    assertEquals(ls.element.get.data, 51)
  }
}
