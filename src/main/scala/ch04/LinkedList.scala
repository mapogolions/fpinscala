package io.github.mapogolions.fpinscala.ch04.linkedlist


case class LinkedList[A](private var head: Option[Bucket[A]] = None) {
  private var _size = 0
  def size = _size

  def element = head

  def add(value: A): Boolean = {
    val elem = last
    elem match {
      case None => head = Some(Bucket(value))
      case Some(bucket) => bucket.next = Some(Bucket(value))
    }
    _size += 1
    true
  }

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

  def searchWhere(f: Bucket[A] => Boolean): Option[Bucket[A]] = {
    @annotation.tailrec
    def recur(elem: Option[Bucket[A]]): Option[Bucket[A]] = {
      elem match {
        case None => None
        case Some(bucket) => if (f(bucket)) elem else recur(bucket.next)
      }
    }
    recur(head)
  }

  def getFirst: Option[A] = head match {
    case None => None
    case Some(Bucket(data, _)) => Some(data)
  }

  def getLast: Option[A] = last match {
    case None => None
    case Some(Bucket(data, _)) => Some(data)
  }

  def removeLast: Option[Bucket[A]] = {
    if (isEmpty) None
    else if (size == 1) removeFirst
    else {
      _size -= 1
      relast(lastButOne)
    }
  }

  def removeFirst: Option[Bucket[A]] = {
    if (isEmpty) None
    else {
      _size -= 1
      refirst(head)
    }

  }

  private def relast(elem: Option[Bucket[A]]): Option[Bucket[A]] = {
    val bucket = elem.get
    val tmp = bucket.next
    bucket.next = None
    tmp
  }

  private def refirst(elem: Option[Bucket[A]]): Option[Bucket[A]] = {
    val bucket = elem.get
    head = bucket.next
    bucket.next = None
    elem
  }

  def isEmpty: Boolean = head match {
    case None => true
    case _ => false
  }

  def last: Option[Bucket[A]] = searchWhere { _.next == None }

  def lastButOne: Option[Bucket[A]] = searchWhere {
    _ match {
      case Bucket(_, Some(Bucket(_, None))) => true
      case _ => false
    }
  }

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
