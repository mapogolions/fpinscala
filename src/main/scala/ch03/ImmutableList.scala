package io.github.mapogolions.fpinscala.ch03


sealed trait ImmutableList[+A] {
  def empty: Boolean =
    this match {
      case EmptyList => true
      case Cons(x, xs) => false
    }
}


object ImmutableList {
  def apply[A](l: A*): ImmutableList[A] = {
    if (l.isEmpty) EmptyList
    else Cons(l.head, apply(l.tail: _*))
  }

  def tail[A](xs: ImmutableList[A]): ImmutableList[A] =
    xs match {
      case Cons(h, t) => t
      case EmptyList => xs
    }

  @annotation.tailrec
  def drop[A](xs: ImmutableList[A], n: Int): ImmutableList[A] =
    (xs, n) match {
      case (_, i) if (i <= 0) => xs
      case (EmptyList, _) => EmptyList
      case (Cons(h, t), n) => drop(t, n - 1)
    }

  @annotation.tailrec
  def dropWhile[A](xs: ImmutableList[A])(f: A => Boolean): ImmutableList[A] =
    xs match {
      case EmptyList => xs
      case Cons(h, t) => if (f(h)) dropWhile(t)(f) else t
    }

  def foldRightInTermsOfLeft[A, B](xs: ImmutableList[A], z: B)(f: (A, B) => B): B = {
    foldLeft(xs, z)((b, a) => f(a, b))
  }

  def foldLeftInTermsOfRight[A, B](xs: ImmutableList[A], z: B)(f: (B, A) => B): B = {
    foldRight(xs, z)((a, b) => f(b, a))
  }

  @annotation.tailrec
  def foldLeft[A, B](xs: ImmutableList[A], z: B)(f: (B, A) => B): B =
    xs match {
        case EmptyList => z
        case Cons(h, t) => foldLeft(t, f(z, h))(f)
    }

  def foldRight[A, B](xs: ImmutableList[A], z: B)(f: (A, B) => B): B =
    xs match {
      case EmptyList => z
      case Cons(h, t) => f(h, foldRight(t, z)(f))
    }

  def sum(xs: ImmutableList[Int]): Int =
    foldRight(xs, 0)(_ + _)

  def sum(xs: ImmutableList[Double]): Double =
    foldRight(xs, 0.0)(_ + _)

  def product(xs: ImmutableList[Int]): Int =
    foldRight(xs, 1)(_ * _)

  def product(xs: ImmutableList[Double]): Double =
    foldRight(xs, 1.0)(_ * _)

  def append[A](xs: ImmutableList[A], ys: ImmutableList[A]): ImmutableList[A] =
    xs match {
      case EmptyList => ys
      case Cons(h, t) => Cons(h, append(t, ys))
    }

  def init[A](xs: ImmutableList[A]): ImmutableList[A] =
    xs match {
      case EmptyList => EmptyList
      case Cons(_, EmptyList) => EmptyList
      case Cons(h, t) => Cons(h, init(t))
    }

  def reverse[A](xs: ImmutableList[A]): ImmutableList[A] =
    foldLeft(xs, EmptyList: ImmutableList[A])((t, h) => Cons(h, t))

  def copy[A](xs: ImmutableList[A]): ImmutableList[A] =
    foldRight(xs, EmptyList: ImmutableList[A])((h, t) => Cons(h, t))

  def appendRight[A](xs: ImmutableList[A], ys: ImmutableList[A]): ImmutableList[A] =
    foldRight(xs, ys)(Cons(_, _))

  def appendLeft[A](xs: ImmutableList[A], ys: ImmutableList[A]): ImmutableList[A] =
    foldLeft(reverse(xs), ys)((t, h) => Cons(h, t))

  def addOne(xs: ImmutableList[Int]): ImmutableList[Int] =
    foldRight(xs, EmptyList: ImmutableList[Int])((h, t) => Cons(h + 1, t))

  def convert(xs: ImmutableList[Double]): ImmutableList[String] =
    foldRight(xs, EmptyList: ImmutableList[String])((h, t) => Cons(h.toString, t))

  def map[A, B](xs: ImmutableList[A])(f: A => B): ImmutableList[B] =
    foldRight(xs, EmptyList: ImmutableList[B])((h, t) => Cons(f(h), t))

  def flatMap[A, B](xs: ImmutableList[A])(f: A => ImmutableList[B]): ImmutableList[B] =
    foldRight(xs, EmptyList: ImmutableList[B])((h, t) => append(f(h), t))

  def filter[A](xs: ImmutableList[A])(f: A => Boolean): ImmutableList[A] =
    flatMap(xs)(x => if (f(x)) ImmutableList(x) else ImmutableList())

  def zip[A, B](xs: ImmutableList[A], ys: ImmutableList[A])(f: (A, A) => B): ImmutableList[B] =
    (xs, ys) match {
      case (Cons(a, as), Cons(b, bs)) => Cons(f(a, b), zip(as, bs)(f))
      case _ => EmptyList
    }

  def hasSubsequence[A](sup: ImmutableList[A], sub: ImmutableList[A]): Boolean = {
    @annotation.tailrec
    def loop[A](sup: ImmutableList[A], sub: ImmutableList[A], pattern: ImmutableList[A]): Boolean = {
      (sup, sub, pattern) match {
        case (_, EmptyList, _) => true
        case (Cons(x, xs), Cons(y, ys), Cons(z, zs)) if x == y => loop(xs, ys, pattern)
        case (Cons(x, xs), Cons(y, ys), Cons(z, zs)) if (x != y && y == z) => loop(xs, sub, pattern)
        case (Cons(x, xs), Cons(y, ys), Cons(z, zs)) if (x != y && y != z) => loop(sup, pattern, pattern)
        case _ => false
      }
    }
    loop(sup, sub, sub)
  }
}

case object EmptyList extends ImmutableList[Nothing]
case class Cons[+A](val head: A, val tail: ImmutableList[A]) extends ImmutableList[A]
