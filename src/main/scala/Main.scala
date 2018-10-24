package io.github.mapogolions.fpinscala

import io.github.mapogolions.fpinscala.ch04.linkedlist._


object Main {
  def main(args: Array[String]): Unit = {
    println(msg)
  }

  def msg = "I was compiled by dotty :)"
}


case class Person(
  val firstName: String,
  val lastName: String
)
