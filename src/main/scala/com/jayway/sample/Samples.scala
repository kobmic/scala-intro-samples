package com.jayway.sample


object ForSample {
  def main(args: Array[String]) {
    val filesHere = (new java.io.File(".")).listFiles
    for(file <- filesHere) {
      println(file)
    }
    
    for(file <- filesHere
        if file.isDirectory) {
      println("DIR " + file)
    }
    
    val dirs = for(file <- filesHere
                   if file.isDirectory) yield file
    
    for(i <- 0 to 5) {
      println("iteration " + i)
    }
  }
}

object Closure {
  def main(args: Array[String]) {
    // Closure example
    var factor = 3
    val multiplier = (i: Int) => i * factor // factor defined in enclosing scope
    val l1 = List(1, 2, 3, 4, 5) map multiplier

    factor = 10
    val l2 = List(1, 2, 3, 4, 5) map multiplier
    println(l1)
    println(l2)
  }
}



object Currying {
  def multiplier(factor: Int)(i: Int) = factor * i
  val byFour = multiplier(4)(_)
  val byTen = multiplier(10)(_)

  def add(x: Int, y: Int) = x + y
  // make existing functions curried
  val addCurried = Function.curried(add _)
  val incr = addCurried(1)(_)

  def times(i: Int)(block: => Unit) {
    (0 until i).foreach(_ => block)
  }

  def main(args: Array[String]) {
    println(multiplier(5)(3))
    println(byTen(5))

    times(5) {
      println("Scala is cool")
    }
  }
}

object PatternMatching {

  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "true"
    case "ping" => "pong"
    case x: String => "a string"
    case Nil => "empty list"
    case (a: Int, b: Int) => "a tuple of ints"
    case List(_, 1, _) => "a list with 3 elems, with a 1 as second element"
    case _ => "something else"
  }

  case class Point(x: Double, y: Double)
  abstract class Shape()
  case class Circle(center: Point, radius: Double) extends Shape()
  case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape()
  case class Square(lowerLeft: Point, height: Double) extends Shape()

  def stretch(s: Shape): Shape = s match {
    case Circle(c, r) => Circle(c, r * 2)
    case Square(l, h) => Square(l, h * 2)
    case Rectangle(l, h, w) => Rectangle(l, h * 2, w * 2)
    case _ => throw new IllegalArgumentException("don't know how to stretch")
  }

  def main(args: Array[String]) {
    val shapes: List[Shape] = List(
      Circle(Point(0.0, 1.0), 2.0),
      Circle(Point(0.0, 0.0), 1.0),
      Square(Point(2.0, 2.0), 3.0),
      Rectangle(Point(0.0, 0.0), 2.0, 3.0))
    val stretchedShapes = shapes.map(stretch(_))
    println(stretchedShapes)
  }
}




object Implicits {
  // Predef.int2double
  val d: Double = 1 // 1 is Int

  val name: java.lang.String = "scala"
  // Predef.stringWrapper
  name.capitalize.reverse

  class MyRichString(str: String) {
    def acronym = str.toCharArray.foldLeft("Acronym: ") { (t, c) =>
      t + (if (c.isUpperCase) c.toString else "")
    }
  }

  implicit def str2MyRichString(str: String) = new MyRichString(str)

  def main(args: Array[String]) {
    println("HyperText Transfer Protocol".acronym)
  }

}

object TupleSample {
  
  def getHostAndPort() : Tuple2[String, Int] = {
    ("localhost", 8080)
  }
  
  def main(args: Array[String]) {
    val (host, port) = getHostAndPort
    println("use host " + host + " and port " + port)

    val pair = getHostAndPort
    println(pair._1)

    val (server, _) = getHostAndPort
  }
  
}


object ActorSample {
  import scala.actors.Actor
  // simple actor
  class Simple extends Actor {
    def act = {
      loop {
        react {
          case "ping" => Thread.sleep(500); println("pong") 
          case _ => println("something else")
        }
      }
    }
  }
  // (0 until 9).foreach(i => { val simple = new Simple().start; simple ! "ping"}); (0 until 9).foreach(i => { val simple = new Simple().start; simple ! "hello"})
  
  sealed trait Message
  case class Accumulate(amount: Int) extends Message
  case class Reset extends Message
  case class Total extends Message
  case class Stop extends Message
  
  class Accumulator extends Actor {
    var sum = 0
    def act = {
      loop {
        react {
          case Accumulate(n) => sum += n
          case Reset => sum = 0
          case Total => println(sum)
          case Stop => exit
        }
      }
    }
  }

  def main(args: Array[String]) {
    val accu = new Accumulator()
    accu.start
    accu ! Accumulate(5)
    accu ! Accumulate(3)
    accu ! Total
    accu ! Stop
  }

}

