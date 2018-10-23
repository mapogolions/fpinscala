package io.github.mapogolions.fpinscala

import io.github.mapogolions.fpinscala.ch04.linkedlist._


object Main {
  def main(args: Array[String]): Unit = {
    val ls = LinkedList[Char]()

    for (i <- "i love this game")
      ls.add(i)

    println(ls.element)
    println()
    println(ls.remove(3))
    println()
    println(ls.remove(7))
    println(ls.removeLast)
  }

  def msg = "I was compiled by dotty :)"
}


case class Person(
  val firstName: String,
  val lastName: String
)
