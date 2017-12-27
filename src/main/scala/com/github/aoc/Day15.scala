package com.github.aoc

object Day15 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 15..." )
    
    var startA = 65L
    var startB = 821L 
    // var startA = 679L
    // var startB = 771L 
    
    var count = 0
    
    for( i <- 0 until 40000000 ){
      startA = genA( startA ) 
      startB = genB( startB ) 
      val a = sub( pad( startA ) )
      val b = sub( pad( startB ) )
      
      if( a.equals(b) ){
        count = count + 1
      }
    }
    
    Console.println( count )
    
  }
  
  def sub( s : String ) : String = {
    s.substring( 64 - 16 )
  }
  
  def pad( l : Long ) : String = {
    l.toBinaryString.reverse.padTo(64, '0').reverse
  }
  
  def prodFunc( factor : Long)( a : Long ) : Long = {
    
    val c = a * factor
    val r = c % 2147483647L
    r
  }
  
  val genA = prodFunc( 16807 )( _ )
  val genB = prodFunc( 48271 )( _ )
}