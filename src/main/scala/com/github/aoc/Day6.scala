package com.github.aoc

import scala.collection.mutable.ListBuffer

object Day6 {
  
  val example = "0 2 7 0"
  
  val data = "10	3	15	10	5	15	5	15	9	2	5	8	5	2	3	6"
  
  
  
  def main( args : Array[String] ) : Unit = {
    
    
    Console.println( "day 6" )
    val is = Common.toInts(data,'\t')
    Console.println( is )
    val real = reallocate(is) 
    Console.println( real )
    Console.println( "Iters:"+ ( real.size - 1 ) )
    Console.println( "Size:"+ loopSize(real) )

  }
  
  def loopSize( src : List[List[Int]] ) : Int = {
    
    val end = src.last
    
    val idx = src.indexOf(end)
    
    
    src.size - idx - 1
    
    
  }
  
  def reallocate( src : List[Int] ) : List[List[Int]] = {
    
    def inner( current : List[List[Int]] ) : List[List[Int]] = {
      
      val next = distribute( current.last )
    
      if( current.contains(next) ){ current :+ next }
      else { inner( current :+ next ) }
      
    }
    
    inner( List(src) )
  }
  
  def distribute( src : List[Int] ) : List[Int] = {
    
    val target = ListBuffer[Int]()
    target ++= src
    
    var idx  = findHigh(target.toList)
    var amt = target(idx)
    target(idx) = 0
    
    while( amt > 0 ){
      
      idx = next( idx, src )
      target(idx) = target(idx) + 1
      amt = amt - 1
      
    }
    
    target.toList
  }
  
  def next( idx : Int, src : List[Int] ) : Int = {
    val n = idx + 1
    if( n < src.size ){ n }
    else { 0 }
  }
  
  def findHigh( src : List[Int] ) : Int = {
    
    var hi = (-1,-1)
    
    for( i <- 0 until src.size ){
      
      if( src(i) > hi._2 ){
        hi = (i,src(i))
      }
    }
    
    hi._1
    
  }
  
}