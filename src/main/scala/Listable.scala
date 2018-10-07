package io.github.mapogolions.fpinscala

import io.github.mapogolions.fpinscala._


/* E - from embellished */
trait Listable[E[_]] {
  def length[A](l: E[A]): Int
  def drop[A](l: E[A], n: Int): E[A]
  def dropWhile[A](l: E[A], f: A => Boolean): E[A]
  def foldRight[A, B](l: E[A], z: B)(f: (A, B) => B): B
  def foldLeft[A, B](l: E[A], z: B)(f: (B, A) => B): B
  def sum(l: E[Int]): Int
  def product(l: E[Int]): Int
  def append[A](xs: E[A], ys: E[A]): E[A]
  def init[A](l: E[A]): E[A]
  def reverse[A](l: E[A]): E[A]
  def copy[A](l: E[A]): E[A]
  def map[A, B](l: E[A])(f: A => B): E[B]
}

object ListableSyntax {
  implicit class ListableOps[E[_], A](E: E[A])(implicit listable: Listable[E]) {
    def length: Int = listable.length(E)
    def drop(n: Int): E[A] = listable.drop(E, n)
    def dropWhile(f: A => Boolean): E[A] = listable.dropWhile(E, f)
    def foldRight[B](f: (A, B) => B, z: B): B = listable.foldRight(E, z)(f)
    def foldLeft[B](f: (B, A) => B, z: B): B = listable.foldLeft(E, z)(f)
    def map[B](f: A => B): E[B] = listable.map(E)(f)
    def append(l: E[A]): E[A] = listable.append(E, l)
    def init: E[A] = listable.init(E)
    def reverse: E[A] = listable.reverse(E)
    def copy: E[A] = listable.copy(E)
  }

  implicit class ListableOpsInt[E[Int]](E: E[Int])(implicit listable: Listable[E]) {
    def sum: Int = listable.sum(E)
    def product: Int = listable.product(E)
  }
}

object ListableInstances {
  implicit val list: Listable[List] = new Listable[List] {
    def length[A](l: List[A]): Int = {
      @annotation.tailrec
      def loop(l: List[A], counter: Int): Int = {
        l match {
          case Nil        => counter
          case Cons(h, t) => loop(t, counter + 1)
        }
      }
      loop(l, 0)
    }

    def copy[A](l: List[A]): List[A] =
      foldRight(l, Nil: List[A])(Cons(_, _))

    def reverse[A](l: List[A]): List[A] =
      foldLeft(l, Nil: List[A])((t, h) => Cons(h, t))

    def init[A](l: List[A]): List[A] =
      l match {
        case Nil          => Nil
        case Cons(_, Nil) => Nil
        case Cons(h, t)   => Cons(h, init(t))
      }


    def append[A](xs: List[A], ys: List[A]): List[A] =
      xs match {
        case Nil        => ys
        case Cons(h, t) => Cons(h, append(t, ys))
      }

    def sum(l: List[Int]): Int = foldRight(l, 0)(_ + _)
    def product(l: List[Int]): Int = foldRight(l, 1)(_ * _)

    @annotation.tailrec
    def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
      l match {
        case Nil        => z
        case Cons(h, t) => foldLeft(t, f(z, h))(f)
      }
    }

    def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = {
      l match {
        case Nil        => z
        case Cons(h, t) => f(h, foldRight(t, z)(f))
      }
    }

    @annotation.tailrec
    def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
      l match {
        case Nil        => Nil
        case Cons(h, t) => if (f(h)) dropWhile(t, f) else l
      }
    }

    @annotation.tailrec
    def drop[A](l: List[A], n: Int): List[A] = {
      (l, n) match {
        case (Nil, _)         => Nil
        case (_, 0)           => l
        case  (Cons(h, t), n) => drop(t, n - 1)
      }
    }

    def map[A, B](l: List[A])(f: A => B): List[B] = {
      l match {
        case Nil        => Nil
        case Cons(h, t) => Cons(f(h), map(t)(f))
      }
    }
  }
}
