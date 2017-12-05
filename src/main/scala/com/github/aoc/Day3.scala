package com.github.aoc

import scala.collection.mutable.ListBuffer

object Day3 {

  class Move( val x : Int, val y : Int )
  case class East() extends Move( 1, 0 ) 
  case class West() extends Move( -1, 0 ) 
  case class North() extends Move( 0, -1 ) 
  case class South() extends Move( 0, 1 ) 
  
  val SIZE = 527
  // 5 x 5 covers 25
  // 32 x 32 - covers 1024
  // 527 x 527 - covers 277678


  def main( args : Array[String] ) : Unit = {
    Console.println("day3")
    
    // create a grid of the desired size
    val grid = Array.ofDim[Int](SIZE,SIZE)
    fill(grid)
    print(grid)
    
    // find target location
    val target = find( 1, grid )
    Console.println( target )
    // find current location
    val loc = find( 277678, grid )
    Console.println( loc )
    // 2,2
    // 0,1
    Console.println( dist( target, loc ) )
    

  }
  
  
  def dist( a : (Int,Int), b : (Int,Int) ) : Int = {
    
    val x = a._1 - b._1
    val y = a._2 - b._2
    
    x.abs + y.abs

      
  }

  def find( i : Int, grid : Array[Array[Int]] ) : (Int,Int) = {
    val res = for( y <- 0 until SIZE; x <- 0 until SIZE; if( grid(x)(y) == i ) ) yield { (x,y) }
    res.head
  }

  def fill( grid : Array[Array[Int]] ) : Unit = {
    
    // start at bottom right with max value
    var x = ( SIZE - 1 )
    var y = ( SIZE - 1 )
    var dir : Move = West()
    
    for( cellVal <- ( SIZE * SIZE ) until 0 by -1 ){
      grid(x)(y) = cellVal
      dir = move( grid, x, y, dir ) 
      x = x + dir.x
      y = y + dir.y
    }
  }
  
  def move( grid : Array[Array[Int]], x : Int, y : Int, dir : Move ) : Move = {
    
    // can we move in that dir?
    val nx = x + dir.x
    val ny = y + dir.y
    if( inBounds( nx ) && inBounds( ny ) && grid(nx)(ny) == 0 ){
      dir
    }
    else {
      dir match {
        case West() => { North() }
        case North() => { East() }
        case East() => { South() }
        case South() => { West() }
      }
    }
  }
  
  def inBounds( i : Int ) : Boolean = {
    i >= 0 && i < SIZE
  }
  
  def print( grid : Array[Array[Int]] ) : Unit = {
    
    for( y <- 0 until SIZE ){
      for( x <- 0 until SIZE ){
        Console.print( grid(x)(y) +"\t" )
      }
      Console.print( '\n' )
    }
    
  }
  

}
