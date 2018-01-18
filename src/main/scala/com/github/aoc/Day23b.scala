package com.github.aoc

object Day23b {

  def main(args: Array[String]): Unit = {
    Console.println("day 23b...")
    run()
  }

  def run(): Unit = {

    var h: Int = 0

    for( b <-  105700 to 122700 by 17 )  {

      val factors = for( n <- 2 until ( b / 2 ); 
        if( b % n == 0 ) ) yield { n }
      
      if( !factors.isEmpty ){
        h = h + 1
      }

    } 
    //              |
    //              V
    Console.println("exit:  h:" + h)
  }
}