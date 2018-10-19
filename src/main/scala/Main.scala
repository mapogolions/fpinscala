package io.github.mapogolions.fpinscala
import io.github.mapogolions.fpinscala.ch04.HashTable

object Main {
  def main(args: Array[String]): Unit = {
    val hashTable = new HashTable[Int, String]()

    hashTable
      .put(0 -> "First")
      .put(1 -> "One")
      .put(2 -> "Two")
      .put(3 -> "Three")
      
    println(hashTable.table(0))
    println(hashTable.containsKey(2))
    println(hashTable.containsKey(3))
    println(hashTable.containsKey(4))
    println(s"Contains value Two? ${hashTable.contains("Two")}")
    println(s"Contains value One? ${hashTable.contains("One")}")
    println(s"Contains value Four? ${hashTable.contains("Four")}")
    println(s"get elem with key 1 = ${hashTable.get(0)}")
    println(s"get elem with key 1 = ${hashTable.get(10)}")
    println(s"get elem with key 1 = ${hashTable.getOrElse(2, "nothing")}")
    println(s"get elem with key 1 = ${hashTable.getOrElse(10, "empty")}")
    println(s"hashTable before remove2 elem with key 0 = ${hashTable.table(0)}")
    println(s"remove elem with key 0 = ${hashTable.remove(0, "first")}")
    println(s"hashTable after remove2 elem with key 0 = ${hashTable.table(0)}")
  }

  def msg = "I was compiled by dotty :)"
}
