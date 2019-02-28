package io.github.mapogolions.fpinscala.ch03


sealed trait BinaryTree[+A] { self =>
  def size: Int = self match {
    case Leaf => 0
    case Node(data, left, right) => 1 + left.size + right.size
  }

  def member[B >: A](elem: B)(implicit ord: Ordering[B]): Boolean =
    self match {
      case Leaf => false
      case Node(data, _, _) if (ord.compare(elem, data) == 0) => true
      case Node(data, left, _) if (ord.compare(elem, data) < 0) => left member elem
      case Node(data, _, right)  => right member elem
    }

  def insert[B >: A](elem: B)(implicit ord: Ordering[B]): BinaryTree[B] = self match {
    case Leaf => Node(elem, Leaf, Leaf)
    case Node(data, left, right) if (ord.compare(elem, data) == -1) => 
      Node(data, left.insert(elem), right)
    case Node(data, left, right) if (ord.compare(elem, data) == 0) =>
      Node(data, left, right)
    case Node(data, left, right) => Node(data, left, right.insert(elem))
  }

  def inOrder: Seq[A] = self match {
    case Node(data, left, right) => left.inOrder ++ Seq(data) ++ right.inOrder
    case Leaf => Nil
  }

  def preOrder: Seq[A] = self match {
    case Node(data, left, right) => Seq(data) ++ left.preOrder ++ right.preOrder
    case Leaf => Nil
  }

  def postOrder: Seq[A] = self match {
    case Node(data, left, right) => left.postOrder ++ right.postOrder ++ Seq(data)
    case Leaf => Nil
  }
}

case object Leaf extends BinaryTree[Nothing]
case class Node[A](
  val data: A, 
  val left: BinaryTree[A],
  val right: BinaryTree[A]
) extends BinaryTree[A]
