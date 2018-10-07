package io.github.mapogolions.fpinscala.ch03.raw


sealed trait MyList[+A] {
  def empty: Boolean =
    this match {
      case Nil => true
      case Cons(x, xs) => false
    }
}


object MyList {
  def apply[A](l: A*): MyList[A] = {
    if (l.isEmpty) Nil
    else Cons(l.head, apply(l.tail: _*))
  }

  def tail[A](xs: MyList[A]): MyList[A] =
    xs match {
      case Cons(h, t) => t
      case Nil => xs
    }

  @annotation.tailrec
  def drop[A](xs: MyList[A], n: Int): MyList[A] =
    (xs, n) match {
      case (_, i) if (i <= 0) => xs
      case (Nil, _) => Nil
      case (Cons(h, t), n) => drop(t, n - 1)
    }

  @annotation.tailrec
  def dropWhile[A](xs: MyList[A])(f: A => Boolean): MyList[A] =
    xs match {
      case Nil => xs
      case Cons(h, t) => if (f(h)) dropWhile(t)(f) else t
    }

  def foldRightInTermsOfLeft[A, B](xs: MyList[A], z: B)(f: (A, B) => B): B = {
    foldLeft(xs, z)((b, a) => f(a, b))
  }

  def foldLeftInTermsOfRight[A, B](xs: MyList[A], z: B)(f: (B, A) => B): B = {
    foldRight(xs, z)((a, b) => f(b, a))
  }

  @annotation.tailrec
  def foldLeft[A, B](xs: MyList[A], z: B)(f: (B, A) => B): B =
    xs match {
        case Nil => z
        case Cons(h, t) => foldLeft(t, f(z, h))(f)
    }

  def foldRight[A, B](xs: MyList[A], z: B)(f: (A, B) => B): B =
    xs match {
      case Nil => z
      case Cons(h, t) => f(h, foldRight(t, z)(f))
    }

  def sum(xs: MyList[Int]): Int =
    foldRight(xs, 0)(_ + _)

  def sum(xs: MyList[Double]): Double =
    foldRight(xs, 0.0)(_ + _)

  def product(xs: MyList[Int]): Int =
    foldRight(xs, 1)(_ * _)

  def product(xs: MyList[Double]): Double =
    foldRight(xs, 1.0)(_ * _)

  def append[A](xs: MyList[A], ys: MyList[A]): MyList[A] =
    xs match {
      case Nil => ys
      case Cons(h, t) => Cons(h, append(t, ys))
    }

  def init[A](xs: MyList[A]): MyList[A] =
    xs match {
      case Nil => Nil
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
    }

  def reverse[A](xs: MyList[A]): MyList[A] =
    foldLeft(xs, Nil: MyList[A])((t, h) => Cons(h, t))

  def copy[A](xs: MyList[A]): MyList[A] =
    foldRight(xs, Nil: MyList[A])((h, t) => Cons(h, t))

  def appendRight[A](xs: MyList[A], ys: MyList[A]): MyList[A] =
    foldRight(xs, ys)(Cons(_, _))

  def appendLeft[A](xs: MyList[A], ys: MyList[A]): MyList[A] =
    foldLeft(reverse(xs), ys)((t, h) => Cons(h, t))

  def addOne(xs: MyList[Int]): MyList[Int] =
    foldRight(xs, Nil: MyList[Int])((h, t) => Cons(h + 1, t))

  def convert(xs: MyList[Double]): MyList[String] =
    foldRight(xs, Nil: MyList[String])((h, t) => Cons(h.toString, t))

  def map[A, B](xs: MyList[A])(f: A => B): MyList[B] =
    foldRight(xs, Nil: MyList[B])((h, t) => Cons(f(h), t))

  def flatMap[A, B](xs: MyList[A])(f: A => MyList[B]): MyList[B] =
    foldRight(xs, Nil: MyList[B])((h, t) => append(f(h), t))

  def filter[A](xs: MyList[A])(f: A => Boolean): MyList[A] =
    flatMap(xs)(x => if (f(x)) MyList(x) else MyList())

  def zip[A, B](xs: MyList[A], ys: MyList[A])(f: (A, A) => B): MyList[B] =
    (xs, ys) match {
      case (Cons(a, as), Cons(b, bs)) => Cons(f(a, b), zip(as, bs)(f))
      case _ => Nil
    }

  def hasSubsequence[A](sup: MyList[A], sub: MyList[A]): Boolean = {
    @annotation.tailrec
    def go[A](sup: MyList[A], sub: MyList[A], pattern: MyList[A]): Boolean = {
      (sup, sub, pattern) match {
        case (_, Nil, _) => true
        case (Cons(x, xs), Cons(y, ys), Cons(z, zs)) if x == y => go(xs, ys, pattern)
        case (Cons(x, xs), Cons(y, ys), Cons(z, zs)) if (x != y && y == z) => go(xs, sub, pattern)
        case (Cons(x, xs), Cons(y, ys), Cons(z, zs)) if (x != y && y != z) => go(sup, pattern, pattern)
        case _ => false
      }
    }
    go(sup, sub, sub)
  }
}

case object Nil extends MyList[Nothing]
case class Cons[+A](val head: A, val tail: MyList[A]) extends MyList[A]
