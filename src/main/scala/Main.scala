package io.github.mapogolions.fpinscala

import io.github.mapogolions.fpinscala.ch04.linkedlist._


object Main {
  def main(args: Array[String]): Unit = {
    // val ls = LinkedList[Int]()
    // println(s"Size = ${ls.size}")
    // ls.add(10)
    // ls.add(11)
    // println(s"Size = ${ls.size}")
    // println(s"Before remove first = ${ls.head}")
    // println(s"Removed element(1-th) = ${ls.removeFirst}")
    // println(s"After remove first = ${ls.head}")
    // println(s"Removed element(2-th) = ${ls.removeFirst}")
    // println(s"After remove second = ${ls.head}")
    // ls.removeFirst
    // ls.removeFirst
    // println(s"Head is the same that last elem ? = ${ls.head == ls.last}")
  }

  def msg = "I was compiled by dotty :)"
}


case class Person(
  val firstName: String,
  val lastName: String
)
