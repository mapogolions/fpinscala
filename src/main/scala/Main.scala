package io.github.mapogolions.fpinscala

import io.github.mapogolions.fpinscala.{ List, Listable }


object Main {
  import ListableInstances._
  import ListableSyntax._

  def main(args: Array[String]): Unit = {
    println("Hello world!")
    println(msg)
    println(List(1, 2, 3).length)
    println(List("i", "Love", "this", "game").length)
    println()

    println(List().drop(0))
    println(List(10).drop(0) == List(10))
    println(List(23, 34).drop(1000))
    println(List(24, 34).drop(1))
    println()

    println( List(1, 2, 0, -10) map { _ > 0 } )
    println( List('a', 'b', 'c', 'd', 'e', 'f') map { _.toInt } )
    println()

    println( List(1, 2, -2, 4) dropWhile { _ > 0 } )
    println( List(1, 2, 3).foldRight[Double]({ _ + _ }, 0) )
    println( List(1, 3, 3, 4).foldRight[Double]({ _ * _ }, 1) )
    println()

    println( List(1, 2, 3, 4).foldLeft[Int]({ _ + _ }, 0) )
    println( List(1, 2, 3, 4).foldLeft[Int]({ _ * _ }, 1) )
    println()

    println( List(1, 2, 3) sum )
    println( List(0, -1, -2, 10, -9) sum )
    println( List(1, 2, 3) product )
    println( List(-1, -2, 10, -9) product )
    println()

    println( List(2, 3) append List(4) )
    println( List('a', 'b', 'c') append List('d', 'e', 'f') )
    println( List() append List("hello") )
    println( List() append List() )
    println()

    println( List(1, 2).init )
    println( List("i", "this", "game").init )
    println( List().init )
    println()

    println( List(1, 2, 3, 4).reverse )
    println( List(1).reverse )
    println()

    println( List(1, 2, 3).copy )
    println( List().copy )
  }

  def msg = "I was compiled by dotty :)"

}
