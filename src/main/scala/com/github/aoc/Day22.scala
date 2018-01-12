package com.github.aoc

import scala.collection.mutable.HashMap

object Day22 {
  
  val ex = """..#
#..
..."""
  
  val data = """...#.##.#.#.#.#..##.###.#
......##.....#####..#.#.#
#..####.######.#.#.##...#
...##..####........#.#.#.
.#.#####..#.....#######..
.#...#.#.##.#.#.....#....
.#.#.#.#.#####.#.#..#...#
###..##.###.#.....#...#.#
#####..#.....###.....####
#.##............###.#.###
#...###.....#.#.##.#..#.#
.#.###.##..#####.....####
.#...#..#..###.##..#....#
##.##...###....##.###.##.
#.##.###.#.#........#.#..
##......#..###.#######.##
.#####.##..#..#....##.##.
###..#...#..#.##..#.....#
##..#.###.###.#...##...#.
##..##..##.###..#.##..#..
...#.#.###..#....##.##.#.
##.##..####..##.##.##.##.
#...####.######.#...##...
.###..##.##..##.####....#
#.##....#.#.#..#.###..##."""
  
  type Coord = (Int,Int)
  
  class Direction( val x : Int, val y : Int )
  case class North() extends Direction( 0, -1 )
  case class East() extends Direction( 1, 0 )
  case class South() extends Direction( 0, 1 )
  case class West() extends Direction( -1, 0 )
  
  class Carrier( val location : Coord, val direction : Direction )
  
  def left( dir : Direction ) : Direction = {
    dir match {
      case North() => { West() }
      case East() => { North() }
      case South() => { East() }
      case West() => { South() }
    }
  }

  def right( dir : Direction ) : Direction = {
    dir match {
      case North() => { East() }
      case East() => { South() }
      case South() => { West() }
      case West() => { North() }
    }
  }
  
  def turn( carrier : Carrier ) : Direction = {
    grid.getOrElse( carrier.location, '.' ) match {
      case '.' => { left( carrier.direction ) }
      case '#' => { right( carrier.direction ) }
    }
  }
  
  // stats
  var effectiveBursts = 0
  var cleaned = 0
  var infected = 0
  
  def burst( carrier : Carrier ) : Carrier = {
    
    // 
    val newDirection = turn( carrier )
    
    val newState = grid.getOrElse( carrier.location, '.' ) match {
      case '.' => { 
        infected = infected + 1
        effectiveBursts = effectiveBursts + 1
        '#'
      }
      case '#' => { 
        cleaned = cleaned + 1
        '.'
      }
    }
    
    grid += ( carrier.location -> newState )
    
    val newLocation = ( carrier.location._1 + newDirection.x, carrier.location._2 + newDirection.y )
    
    new Carrier( newLocation, newDirection )
    
  }
  val grid = HashMap[Coord,Char]()
  
  def main( args : Array[String] ) : Unit = {
    Console.println("day 22...")
    
    // read map 
    readMap()
    Console.println("start" )
    // var carrier = new Carrier( (1,1), North() ) 
    var carrier = new Carrier( (12,12), North() ) 
    Console.println( "carrier:"+ carrier.location +" "+ carrier.direction )
    printMap()
    
    for( i <- 0 until 10000 ){
      carrier = burst( carrier )
      Console.println("burst:"+ (i + 1) )
      Console.println( "carrier:"+ carrier.location +" "+ carrier.direction )
    }
    
    printMap()
    Console.println( "infected:"+ effectiveBursts )
  }
  
  def readMap() : Unit = {
    
    val lines = Common.toLines(data)
    
    val my = lines.size
    val mx = lines(0).size
    
    for( y <- 0 until my ) {
      for( x <- 0 until mx ){
        grid += ( (x,y) -> lines(y)(x) )
      }
    }
    
  }
  
  def printMap() : Unit = {
    val l = findMinMax()
    val min = l(0)
    val max = l(1)
    
    for( y <- min._2 to max._2 ) {
      for( x <- min._1 to max._1 ){
        val c = grid.getOrElse( (x,y), '.' )
        Console.print(c)
      }
      Console.print("\n")
    }
    
    
  }
  
  
  def findMinMax() : List[(Int,Int)] = {
    
    val ks = grid.keys.toList
    
    val xs = ks.map( _._1 ).sorted
    val ys = ks.map( _._2 ).sorted
    
    // mins 
    val min = (xs.head,ys.head)
    val max = (xs.last,ys.last)
    
    List(min,max)

    
  }
}