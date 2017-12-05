package com.github.aoc

import scala.collection.mutable.ListBuffer

object Day3 {

  class Move( val x : Int, val y : Int )
  case class East() extends Move( 1, 0 ) 
  case class West() extends Move( -1, 0 ) 
  case class North() extends Move( 0, -1 ) 
  case class South() extends Move( 0, 1 ) 

  case class NorthEast() extends Move( 1, -1 ) 
  case class SouthEast() extends Move( 1, 1 ) 
  case class SouthWest() extends Move( -1, 1 ) 
  case class NorthWest() extends Move( -1, -1 ) 
  
  val DIRS = List(East(), West(), North(), South(), NorthEast(), SouthEast(), SouthWest(), NorthWest() )
  
  val SIZE = 10
  // 5 x 5 covers 25
  // 32 x 32 - covers 1024
  // 527 x 527 - covers 277678


  def main( args : Array[String] ) : Unit = {
    Console.println("day3")
    
    // create a grid of the desired size
    val grid = Array.ofDim[(Int,Int)](SIZE,SIZE)
    for( x <- 0 until SIZE ){
      for( y <- 0 until SIZE ){
        grid(x)(y) = (0,0)
      }
    }
    fill(grid)
    // print(grid)
    
    // find target location
    // val target = find( 1, grid )
    // Console.println( target )
    // find current location
    // val loc = find( 277678, grid )
    // Console.println( loc )
    // 2,2
    // 0,1
    // Console.println( dist( target, loc ) )
    sums(grid)
    print(grid)
    

  }
  
  def sums( grid : Array[Array[(Int,Int)]] ) : Unit = {
    
    // 1 is a special case
    val l = find( 1, grid ) 
    grid(l._1)(l._2) = (1,1)
    
    for( i <- 2 to (SIZE*SIZE) ){
      val l2 = find( i, grid )
      val sum = neighbors( l2 ).foldLeft(0)( (a:Int, b:(Int,Int)) => {
        a + grid(b._1)(b._2)._2
      } )
      val res = (grid(l2._1)(l2._2)._1,sum) 
      grid(l2._1)(l2._2) = res 
    }
  }
  
  def neighbors( loc : (Int,Int) ) : List[(Int,Int)] = {
    
    for( d <- DIRS;
      x = loc._1 + d.x;
      y = loc._2 + d.y;
      if( inBounds(x) && inBounds(y) ) ) yield { (x,y) }
    
    
  }
  
  
  def dist( a : (Int,Int), b : (Int,Int) ) : Int = {
    
    val x = a._1 - b._1
    val y = a._2 - b._2
    
    x.abs + y.abs

      
  }

  def find( i : Int, grid : Array[Array[(Int,Int)]] ) : (Int,Int) = {
    val res = for( y <- 0 until SIZE; x <- 0 until SIZE; if( grid(x)(y)._1 == i ) ) yield { (x,y) }
    res.head
  }

  def fill( grid : Array[Array[(Int,Int)]] ) : Unit = {
    
    // start at bottom right with max value
    var x = ( SIZE - 1 )
    var y = ( SIZE - 1 )
    var dir : Move = West()
    
    for( cellVal <- ( SIZE * SIZE ) until 0 by -1 ){
      grid(x)(y) = (cellVal,0)
      dir = move( grid, x, y, dir ) 
      x = x + dir.x
      y = y + dir.y
    }
  }
  
  def move( grid : Array[Array[(Int,Int)]], x : Int, y : Int, dir : Move ) : Move = {
    
    // can we move in that dir?
    val nx = x + dir.x
    val ny = y + dir.y
    if( inBounds( nx ) && inBounds( ny ) && grid(nx)(ny)._1 == 0 ){
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
  
  def print( grid : Array[Array[(Int,Int)]] ) : Unit = {
    
    for( y <- 0 until SIZE ){
      for( x <- 0 until SIZE ){
        Console.print( grid(x)(y) +"\t" )
      }
      Console.print( '\n' )
    }
    
  }
  

}
