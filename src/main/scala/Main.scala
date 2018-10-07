package io.github.mapogolions.fpinscala


object Main {
  import io.github.mapogolions.fpinscala.ch03.typeclass.ListableSyntax._
  import io.github.mapogolions.fpinscala.ch03.typeclass.ListableInstances._
    import io.github.mapogolions.fpinscala.ch03.typeclass.{ List, Nil, Cons }

  def main(args: Array[String]): Unit = {
    println(msg)
    println(List(1, -1, 2, -10, 0, 200) filter { _ > 0 } )
  }

  def msg = "I was compiled by dotty :)"

}
