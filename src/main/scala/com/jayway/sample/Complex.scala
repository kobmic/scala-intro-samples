package com.jayway.sample

object Complex {
  def apply(re: Double, im: Double) = {
    new Complex(re, im)
  }

  def add(a: Complex, b: Complex) = {
    a + b
  }
}

class Complex(val re: Double, val im: Double) {
 
  def +(b: Complex): Complex = {
    new Complex(re + b.re, im + b.im)
  }

  def -(b: Complex): Complex = {
    new Complex(re - b.re, im - b.im)
  }

  override def toString: String = {
    (re, im) match {
      case (0, 0) => "0"
      case (0, im) => im + "i"
      case (re, 0) => re + ""
      case (re, im) => re + " + " + im + "i"
    }
  }
}