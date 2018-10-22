import org.junit.Test
import org.junit.Assert._
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo

import io.github.mapogolions.fpinscala.ch04.linkedlist._


class TestLinkedList {
  @Test
  def lastIndexOfTest: Unit = {
    val ls = LinkedList[Int]()
    for (i <- 1 to 10)
      ls.add(i)

    assertEquals(ls.indexOf(10), 9)
    assertEquals(ls.lastIndexOf(10), ls.indexOf(10))
    for (i <- 1 to 10)
      ls.add(i)
    assertEquals(ls.indexOf(10), 9)
    assertEquals(ls.lastIndexOf(10), 19)
  }

  @Test
  def getLastAndgFirst: Unit = {
    val ls = LinkedList[String]()
    for (elem <- "i love this game".split(" "))
      ls.add(elem)
    assertEquals(ls.getFirst, Some("i"))
    assertEquals(ls.getLast, Some("game"))
    assertEquals(ls.removeFirst, Some(Bucket("i")))
    assertEquals(ls.getFirst, Some("love"))
    assertEquals(ls.removeLast, Some(Bucket("game")))
    assertEquals(ls.getLast, Some("this"))
    assertEquals(ls.removeLast, Some(Bucket("this")))
    assertEquals(ls.getLast, Some("love"))
    assertEquals(ls.size, 1)
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
  def lastButOne: Unit = {
    val ls = LinkedList[Char]()
    ls.add('a')
    ls.add('b')
    ls.add('c')
    assertEquals(ls.lastButOne, Some(Bucket('b', Some(Bucket('c', None)))))
    ls.removeFirst
    assertEquals(ls.lastButOne, Some(Bucket('b', Some(Bucket('c', None)))))
    ls.removeFirst
    assertEquals(ls.lastButOne, None)

    val codes = LinkedList[Int]()
    for (code <- 90 to 100) codes.add(code)
    assertEquals(
      (codes.last, codes.lastButOne),
      (Some(Bucket(100)), Some((Bucket(99, Some(Bucket(100, None))))))
    )

  }

  @Test
  def clearTest: Unit = {
    val ls = LinkedList[Int]()
    assertEquals(ls.element, None)
    for (i <- 1 to 100) ls.add(i)
    assertEquals(ls.last, Some(Bucket(100)))
    ls.clear
    assertEquals(ls.size, 0)
    assertEquals(ls.element, ls.last)
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


  @Test
  def initLinkedList(): Unit = {
    val ls = LinkedList[Int]()
    assertTrue(ls isEmpty)
    assertTrue(ls.add(2))
    assertTrue(ls.add(3))
    assertFalse(ls.isEmpty)

    assertEquals(ls.last, Some(Bucket(3)))
    assertEquals(ls.element, Some(Bucket(2, Some(Bucket(3)))))
    assertEquals(ls.size, 2)
    assertEquals(ls.removeFirst, Some(Bucket(2)))
    assertEquals(ls.size, 1)
    assertEquals(ls.element, ls.last)
    assertEquals(ls.removeFirst, Some(Bucket(3)))
    assertEquals(ls.size, 0)
    ls.add(4)
    ls.add(5)
    ls.add(6)
    assertEquals(ls.size, 3)
    assertEquals(ls.element, Some(Bucket(4, Some(Bucket(5, Some(Bucket(6)))))))
    assertEquals(ls.removeFirst, Some(Bucket(4)))
    assertEquals(ls.element, Some(Bucket(5, Some(Bucket(6)))))
    ls.removeFirst
    assertEquals(ls.size, 1)
    ls.removeFirst
    ls.removeFirst
    assertEquals(ls.removeFirst, None)
    assertEquals(ls.size, 0)

    // assertEquals(MyList.sum(MyList(2, 5)), 7)

    // assertThat(
    //   10,
    //   allOf(
    //     equalTo(MyList.sum(MyList(10))),
    //     equalTo(MyList.sum(MyList(-10, 20))),
    //     equalTo(MyList.sum(MyList(200, -190)))
    //   )
    // )
  }
}
