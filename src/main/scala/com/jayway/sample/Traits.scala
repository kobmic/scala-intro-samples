package com.jayway.sample

object Traits {
  abstract class Widget
  class Label extends Widget

  trait Clickable {
    def click(): Unit = println("I'm clicked")
  }

  class Button extends Widget with Clickable
  class Calendar extends Widget with Clickable

  val clickableLabel = new Label() with Clickable
}


object StackableTraits {
  import scala.collection.mutable.ArrayBuffer
  abstract class IntQueue {
    def get(): Int
    def put(x: Int)
  }

  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) { buf += x }
  }

  trait Doubling extends IntQueue {
    abstract override def put(x: Int) { super.put(2 * x) }
  }
  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) { super.put(x + 1) }
  }
  trait Filtering extends IntQueue {
    abstract override def put(x: Int) { if (x > 0) super.put(x) }
  }

  def main(args: Array[String]) {
    val queue1 = new BasicIntQueue with Incrementing with Doubling
    val queue2 = new BasicIntQueue with Doubling with Incrementing

    List(1, 2, 3).foreach(queue1.put(_))
    println("BasicIntQueue with Incrementing with Doubling:")
    List(1, 2, 3).foreach(i => println(queue1.get))

    List(1, 2, 3).foreach(queue2.put(_))
    println("BasicIntQueue with Doubling with Incrementing")
    List(1, 2, 3).foreach(i => println(queue2.get))
  }
}
