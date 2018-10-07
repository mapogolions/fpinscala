package io.github.mapogolions.fpinscala


sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[A](val head: A, val tail: List[A]) extends List[A]

object List {
  def apply[A](items: A*): List[A] = {
    if (items.length == 0) Nil
    else Cons(items.head, apply(items.tail: _*))
  }
}
