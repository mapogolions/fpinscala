package io.github.mapogolions.fpinscala.monoid


trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

object Monoid {
  def apply[A: Monoid]: Monoid[A] = implicitly
  // def apply[A](implicit monoidInstance: Monoid[A]) = monoidInstance
}

object MonoidSyntax {
  implicit class MonoidOps[A](M: A)(implicit monoidInstance: Monoid[A]) {
    def zero = monoidInstance.zero
    def op(a2: A) = monoidInstance.op(M, a2)
  }
}

object MonoidInstances {
  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def zero = ""
    def op(a1: String, a2: String) = a1 + a2;
  }

  implicit val intAdditionMonoid: Monoid[Int] = new Monoid[Int] {
    def zero = 0
    def op(a1: Int, a2: Int) = a1 + a2
  }
  
  val intMultiplicationMonoid: Monoid[Int] = new Monoid[Int] {
    def zero = 1
    def op(a1: Int, a2: Int) = a1 * a2
  }

  implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    def zero = Nil
    def op(a1: List[A], a2: List[A]) = a1 ++ a2
  }

  val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def zero = false
    def op(a1: Boolean, a2: Boolean) = a1 || a2
  }

  val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def zero = true
    def op(a1: Boolean, a2: Boolean) = a1 && a2
  }

  implicit def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
    def zero = None
    def op(a1: Option[A], a2: Option[A]) = a1 orElse a2
  }

  def EndoMonoid[A]: Monoid[A => A] = new Monoid[A => A] {
    def zero = identity
    def op(a1: A => A, a2: A => A) = a1 andThen a2
  }
}