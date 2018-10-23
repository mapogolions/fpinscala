package io.github.mapogolions.fpinscala.ch04.linkedlist


case class LinkedList[A](private var head: Option[Bucket[A]] = None) {
  type T[A] = Option[Bucket[A]]

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
    def recur(elem: T[A], n: Int): Int = {
      elem match {
        case None => -1
        case Some(Bucket(data, next)) => if (value == data) n else recur(next, n + 1)
      }
    }
    recur(head, 0)
  }

  def lastIndexOf(value: A): Int = {
    @annotation.tailrec
    def recur(elem: T[A], index: Int, n: Int): Int = {
      elem match {
        case None => index
        case Some(Bucket(data, next)) if (data == value) => recur(next, n, n + 1)
        case Some(Bucket(_, next)) => recur(next, index, n + 1)
      }
    }
    recur(head, -1, 0)
  }

  private def search(index: Int): T[A] = {
    @annotation.tailrec
    def recur(elem: T[A], n: Int): T[A] = {
      elem match {
        case None => None
        case Some(Bucket(data, next)) => if (n == index) elem else recur(next, n+1)
      }
    }
    recur(head, 0)
  }

  private def searchWhere(f: Bucket[A] => Boolean): T[A] = {
    @annotation.tailrec
    def recur(elem: T[A]): T[A] = {
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

  def remove(n: Int): Option[A] = {
    if (isEmpty) None
    else if (n == 0) removeFirst
    else {
      val elem = search(n - 1)
      elem match {
        case Some(Bucket(data, next)) if (next != None) => (ripOff compose renext)(elem)
        case _ => None
      }
    }
  }

  def removeLast: Option[A] =
    if (isEmpty) None
    else if (size == 1) removeFirst
    else (ripOff compose relast)(lastButOne)

  def removeFirst: Option[A] =
    if (isEmpty) None else (ripOff compose refirst)(head)

  private def ripOff(elem: T[A]): Option[A] =
    elem match {
      case Some(Bucket(data, _)) => Some(data)
      case _ => None
    }

  private def renext(elem: T[A]): T[A] = {
    val buck1 = elem.get
    val elem2 = buck1.next
    val buck2 = elem2.get
    buck1.next = buck2.next
    buck2.next = None
    _size -= 1
    elem2
  }

  private def relast(elem: T[A]): T[A] = {
    val bucket = elem.get
    val tmp = bucket.next
    bucket.next = None
    _size -= 1
    tmp
  }

  private def refirst(elem: T[A]): T[A] = {
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

  def last: T[A] = search(size - 1)
  def lastButOne: T[A] = search(size - 2)

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
