package io.github.mapogolions.fpinscala.ch04

import scala.language.implicitConversions

import io.github.mapogolions.fpinscala.ch03.typeclass.{
  List, Nil, Cons, ListableSyntax, ListableInstances
}

import ListableSyntax._
import ListableInstances._

class HashTable[K, V] { self =>
  val size = 100
  val table = Array.fill(size){ List(): List[Node[K, V]] }

  def hash(key: K): Int = key.hashCode.abs % size
  
  def put(pair: (K, V)) = {
    val (key, value) = pair
    val index = hash(key)
    val chain = table(index)
    // only unique key will be added
    if (!chain.containsWhere{ _.key == key }) {
      table(index) = List(Node(key, value)) append chain
    }
    self // return self for to be able chaining call
  }
  
  def remove(key: K, value: V): Option[V] = {
    val res = get(key)
    res match {
      case Some(v) if (v == value) => {
        val index = hash(key)
        table(index) = table(index) filter { _.key == key }
        res
      }
      case _ => None
    }
  }

  def remove(key: K): Option[V] = {
    get(key) match {
      case None => None
      case value    => {
        val index = hash(key)
        table(index) = table(index) filter { _.key == key }
        value
      } 
    }
  }

  def getOrElse[R >: V](key: K, default: => R): R = {
    table(hash(key)).findWhere { _.key == key } match {
      case None => default
      case Some(Node(k, v)) => v
    }
  }

  def get(key: K): Option[V] = {
    table(hash(key)).findWhere { _.key == key } match {
      case None => None
      case Some(Node(k, v)) => Some(v)
    }
  }

  def contains(value: V): Boolean =
    if (table.indexWhere {_.containsWhere(x => x.value == value) } == -1) false 
    else true
    
  def containsKey(key: K): Boolean = table(hash(key)) containsWhere { _.key == key}
}

case class Node[K, V](
  val key: K, 
  val value: V
)
