package io.github.mapogolions.fpinscala.ch10


trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}


object Monoid {
  val stringMonoid: Monoid[String] = new Monoid[String] {
    def zero = ""
    def op(a1: String, a2: String) = a1 + a2;
  }

  val intAddMonoid: Monoid[Int] = new Monoid[Int] {
    def zero = 0
    def op(a1: Int, a2: Int) = a1 + a2
  }
  val intMulMonoid: Monoid[Int] = new Monoid[Int] {
    def zero = 1
    def op(a1: Int, a2: Int) = a1 * a2
  }

  def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    def zero = Nil
    def op(a1: List[A], a2: List[A]) = a1 ++ a2
  }

  val boolOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def zero = false
    def op(a1: Boolean, a2: Boolean) = a1 || a2
  }

  val boolAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def zero = true
    def op(a1: Boolean, a2: Boolean) = a1 && a2
  }

  def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
    def zero = None
    def op(a1: Option[A], a2: Option[A]) = a1 orElse a2
  }

  def endoMonoid[A]: Monoid[A => A] = new Monoid[A => A] {
    def zero = identity
    def op(f1: A => A, f2: A => A) = f1 compose f2
  }
}