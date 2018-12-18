package io.github.mapogolions.laziness

/**
 * -- Stack overflow problem (infinite right-recursive)-- 
 * data Stream a = (a, Stream a) or
 * data Strea a = Cons a (Stream a)
 * 
 * Decision: use `thunk` unit => Stream a
 * data Stream a = (a, unit => Stream a) or 
 * data Stream a = Cons a ( unit => Stream a)
 */


trait Stream[A] {
  def uncons: Option[(A, Stream[A])]
  def isEmpty = uncons.isEmpty
}


object Stream {
  def empty[A]: Stream[A] = new Stream[A] { def uncons = None }
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = new Stream[A] {
    lazy val ucons = Some(hd, tl)
  }
  def apply[A](items: A*): Stream[A] =
    if (items.isEmpty) empty else cons(items.head, apply(items.tail: _*))
}