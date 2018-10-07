package io.github.mapogolions.fpinscala.ch03.raw


sealed trait Tree[+A]

case object Empty extends Tree[Nothing]
case class Node[+A](
  val value: A,
  val left: Tree[A],
  val right: Tree[A]
) extends Tree[A]


sealed trait BST[A] {
  def members(t: Tree[A], n: A): Boolean
  def insert(t: Tree[A], n: A): Tree[A]
  def size(t: Tree[A]): Int
}

object BST {
  def insert[A](t: Tree[A], n: A)(implicit sh: BST[A]): Tree[A] =
    sh.insert(t, n)

  def members[A](t: Tree[A], n: A)(implicit sh: BST[A]): Boolean =
    sh.members(t, n)

  def size[A](t: Tree[A])(implicit sh: BST[A]): Int =
    sh.size(t)

  implicit val forInt: BST[Int] =
    new BST[Int] {
      def size(t: Tree[Int]): Int = {
        t match {
          case Empty => 0
          case Node(v, l, r) => 1 + (size(l) + size(r))
        }
      }

      def insert(t: Tree[Int], n: Int): Tree[Int] =
        t match {
          case Empty => Node(n, Empty, Empty)
          case Node(v, _, _) if v == n => t
          case Node(v, l, r) if v > n => Node(v, insert(l, n), r)
          case Node(v, l, r) => Node(v, l, insert(r, n))
        }

      def members(t: Tree[Int], n: Int): Boolean =
        t match {
          case Empty => false
          case Node(v, _, _) if v == n => true
          case Node(v, l, _) if v > n => members(l, n)
          case Node(v, _, r) => members(r, n)
        }
    }
}
