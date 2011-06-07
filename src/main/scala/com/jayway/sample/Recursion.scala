package com.jayway.sample

object Recursion {

  // recursion
  def factorial(i: Long): Long = i match {
    case 1L => 1L
    case _ => i * factorial(i - 1)
  }

  // tail call recursion
  def factorialTail(i: Long): Long = {
    def fact(i: Long, accumulator: Long): Long = i match {
      case 1L => accumulator
      case _ => fact(i - 1, i * accumulator)
    }
    fact(i, 1L)
  }

  def main(args: Array[String]) {
    println(factorial(5))
    println(factorialTail(5))
  }
}