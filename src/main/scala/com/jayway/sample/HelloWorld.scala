package com.jayway.sample

object HelloWorld {
  def main(args: Array[String]) {
    println("Hello World")
  }
}


import java.util.Date
import java.text.SimpleDateFormat

class HelloWorld {

  // variable definitions
  val greeting = "Hello World"
  var count = 0
  
  // Scala method definition
  def fun(x: Int): Int = {
    x * x
  }
  // parameterless method
  def today: String = {
    val sdf = new SimpleDateFormat("yyyy.MM.dd")
    sdf.format(new Date())
  }

}

