package com.github.aoc

object Day15 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 15..." )
    
    // var startA = 65L
    // var startB = 8921L 
    var startA = 679L
    var startB = 771L 
    

    /*
    for( i <- 0 until 5 ){
      startB = genB( startB ) 
      Console.println( "Found:"+ startB )
      val a = sub( pad( startB ) )
      Console.println( a )
    }
    * 
    */
    
    var count = 0
    
    for( i <- 0 until 5000000 ){
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
  
  def prodFunc( factor : Long)( mult : Long )( a : Long ) : Long = {
    
    def inner( l : Long ) : Long = {
    
      val c = l * factor
      val r = c % 2147483647L
      
      if( r % mult == 0 ){
        r
      }
      else {
        inner( r )
      }

    }
    
    inner( a )
  }
  
  val genA = prodFunc( 16807 )( 4L )( _ )
  val genB = prodFunc( 48271 )( 8L )( _ )
}