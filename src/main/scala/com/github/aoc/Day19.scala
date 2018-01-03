package com.github.aoc

object Day19 {
  
  val ex = 
"""     |          
        |  +--+    
        A  |  C    
    F---|----E|--+ 
        |  |  |  D 
        +B-+  +--+"""
  
  val lines = List(
"     |          ",
"     |  +--+    ",
"     A  |  C    ",
" F---|----E|--+ ",
"     |  |  |  D ",
"     +B-+  +--+ " )
  
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 19..." )
    val g = convert( lines )
    print(g)
  }
  
  def convert( lines : List[String] ) : Array[Array[Char]] = {
    
    val y = lines.size
    val x = lines(0).size
    Console.println( "x:"+ x +" y:" + y ) 
    
    val grid = Array.ofDim[Char](y,x)
    
    for( yy <- 0 until y ){
      for( xx <- 0 until x ) { 
        val c = lines(yy)(xx)
        grid(yy)(xx) = c
      }
    }
    
    grid
    
  }
  
  def print( grid : Array[Array[Char]] ) : Unit = {
    
    val my = grid.size
    val mx = grid(0).size
    
    for( y <- 0 until my ){
      for( x <- 0 until mx ) {
        Console.print( grid(y)(x) )
      }
      Console.print( '\n')
    }
    Console.print( '\n')
    
  }
}