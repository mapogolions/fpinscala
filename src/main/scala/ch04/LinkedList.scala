package io.github.mapogolions.fpinscala.ch04.linkedlist


case class LinkedList[A](private var head: Option[Bucket[A]] = None) {
  private var _size = 0
  def size = _size

  def element = head

  def add(value: A): Boolean = {
    last match {
      case None => head = Some(Bucket(value))
      case Some(bucket) => bucket.next = Some(Bucket(value))
    }
    _size += 1
    true
  }

  def contains(value: A): Boolean = if (indexOf(value) != -1) true else false

  def indexOf(value: A): Int = {
    @annotation.tailrec
    def recur(elem: Option[Bucket[A]], n: Int): Int = {
      elem match {
        case None => -1
        case Some(Bucket(data, next)) => if (value == data) n else recur(next, n + 1)
      }
    }
    recur(head, 0)
  }

  def lastIndexOf(value: A): Int = {
    @annotation.tailrec
    def recur(elem: Option[Bucket[A]], index: Int, n: Int): Int = {
      elem match {
        case None => index
        case Some(Bucket(data, next)) if (data == value) => recur(next, n, n + 1)
        case Some(Bucket(_, next)) => recur(next, index, n + 1)
      }
    }
    recur(head, -1, 0)
  }

  private def search(index: Int): Option[Bucket[A]] = {
    @annotation.tailrec
    def recur(elem: Option[Bucket[A]], n: Int): Option[Bucket[A]] = {
      elem match {
        case None => None
        case Some(Bucket(data, next)) => if (n == index) elem else recur(next, n+1)
      }
    }
    recur(head, 0)
  }

  private def searchWhere(f: Bucket[A] => Boolean): Option[Bucket[A]] = {
    @annotation.tailrec
    def recur(elem: Option[Bucket[A]]): Option[Bucket[A]] = {
      elem match {
        case None => None
        case Some(bucket) => if (f(bucket)) elem else recur(bucket.next)
      }
    }
    recur(head)
  }


  def get(n: Int): Option[A] = (ripOff compose search)(n)
  def getFirst: Option[A] = ripOff(head)
  def getLast: Option[A] = ripOff(last)


  def removeLast: Option[A] =
    if (isEmpty) None
    else if (size == 1) removeFirst
    else (ripOff compose relast)(lastButOne)

  def removeFirst: Option[A] =
    if (isEmpty) None else (ripOff compose refirst)(head)


  private def ripOff(elem: Option[Bucket[A]]): Option[A] =
    elem match {
      case Some(Bucket(data, _)) => Some(data)
      case _ => None
    }

  private def relast(elem: Option[Bucket[A]]): Option[Bucket[A]] = {
    val bucket = elem.get
    val tmp = bucket.next
    bucket.next = None
    _size -= 1
    tmp
  }

  private def refirst(elem: Option[Bucket[A]]): Option[Bucket[A]] = {
    val bucket = elem.get
    head = bucket.next
    bucket.next = None
    _size -= 1
    elem
  }

  def isEmpty: Boolean = head match {
    case None => true
    case _ => false
  }

  def last: Option[Bucket[A]] = search(size - 1)
  def lastButOne: Option[Bucket[A]] = search(size - 2)

  def clear: Unit = {
    if (!isEmpty) {
      _size = 0
      head = None
    }
  }
}

case class Bucket[A](
  var data: A,
  var next: Option[Bucket[A]] = None
)
