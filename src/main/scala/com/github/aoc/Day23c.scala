package com.github.aoc

import org.apache.commons.lang3.math.NumberUtils

object Day23c {
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 23 b..." )
    run()
  }
  
  def run() : Unit = {
    
    var c = 0 
    for( n <- 105700 to 122700 by 17 ){
      if( !Primes.isPrime( n ) ){
        c = c +1
      }
    }

    Console.println(c)
    
  }
}