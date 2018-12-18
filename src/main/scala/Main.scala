package io.github.mapogolions.fpinscala

import scala.language.implicitConversions
import io.github.mapogolions.fpinscala.monoid.Monoid
import io.github.mapogolions.fpinscala.monoid.MonoidInstances._
import io.github.mapogolions.fpinscala.monoid.MonoidSyntax._


object Main {
  def main(args: Array[String]): Unit = {
    println(Monoid[String].zero)
    println(Monoid[String].op("hello ", "world"))
    println(Monoid[Option[Int]].zero)
    println(Monoid[Option[Int]].op(Some(10), Some(10)))
    println("hello" op " " op "world" op Monoid[String].zero)
    println(List(1, 2) op List(3, 4) op List(5, 6) op Monoid[List[Int]].zero)
    //println(2 op 3 op Monoid[Int].zero)
  }

  def msg = "I was compiled by dotty :)"
}
