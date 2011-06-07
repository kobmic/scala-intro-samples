package com.jayway.sample

object DuckTyping {

  class Duck {  
    def quack = "Quack!"  
  }

  class Frog {  
    def quack = "Quaaack Quaaack!"  
  }
  
  def doQuack(duck: { def quack : String }) = {  
    duck.quack     // compiler checks that it will work
  }

}