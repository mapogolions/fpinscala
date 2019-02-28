package io.github.mapogolions.fpinscala.ch03


class MutableList[A] {
  case class Node(var data: A, var next: Node)

  var head: Node = null
  var size = 0
  def isEmpty = head == null && size == 0
  def hasSingleElement = size == 1

  // Big O(1) - complexity
  def addFirst(elem: A) = {
    val node = Node(elem, null)
    node.next = head
    head = node
    size += 1
  }

  // Big O(n) - complexity
  def addLast(elem: A) = head match {
    case null => addFirst(elem)
    case firstElement => {
      var tmp = firstElement
      while (tmp.next != null) tmp = tmp.next
      val node = Node(elem, null)
      tmp.next = node
      size += 1
    }
  }

  // Big O(1) - complexity
  def removeFirst: Option[A] = {
    if (isEmpty) return None
    val firstElement = head
    head = head.next
    size -= 1
    Some(firstElement.data)
  }
}