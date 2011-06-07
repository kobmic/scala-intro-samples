package com.jayway.sample

object FunctionLiterals {

  val increase = (x: Int) => x + 1

  var func = increase

  var func2 = (x: Int) => {
    println("this is nice")
    x + 1
  }

  def functionFactory(y: Int): (Int) => Int = { (x: Int) =>
    x + y
  }

  // placeholder syntax
  var filterFunc = (_: Int) > 0
  
}

object HigherOrderFunctions {

  def main(args: Array[String]) {
   
    // foreach  
    List(1, 2, 3, 4, 5).foreach(println(_))
    
    // same as
    List(1, 2, 3, 4, 5) foreach println
    
    // for comprehension example using foreach
    val filesHere = (new java.io.File(".")).listFiles
    filesHere.foreach(println(_))
    
    // operator notation
    filesHere foreach println
    
    // or even shorter
    (new java.io.File(".")).listFiles foreach println
    
    // map
    var newList = List(1, 2, 3, 4, 5) map { _ * 2 } // '_ * 2' is shorthand for (i:Int) => i * 2
    val twoTimes = (i: Int) => i * 2 // '(i: Int) => i * 2' is a function literal

    newList = List(1, 2, 3, 4, 5) map twoTimes

    // filter
    List.range(1, 20).filter(_ % 2 == 0)

    // get directory names
    val dirNames = filesHere.filter(_.isDirectory).map(_.getName)
    
    // reduceLeft
    val result = List(1, 2, 3, 4, 5) reduceLeft { _ * _ }
  }
}
