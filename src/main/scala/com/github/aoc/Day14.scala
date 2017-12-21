package com.github.aoc

import scala.collection.mutable.ListBuffer

object Day14 {
  
  val ex = "flqrgnkx"
  val data = "vbqugkhl"
  
  def main( args : Array[String] ) : Unit = {
    Console.println("day 14...")
    val keys = genKeys(data)
    
    val hashedKeys = keys.map( Day10B.knotHash( _ ) ) 
    Console.println( hashedKeys )
    
    val binaryKeys = hashedKeys.map( toBinary( _ ) )
    
    val usedRows = binaryKeys.map( used( _ ) )
    
    val totalUsed = usedRows.foldLeft(0)( (a:Int,b:Int) => { a + b } )
    
    Console.println( "total used:"+ totalUsed )
    
    val regions = buildRegions(binaryKeys)
    Console.println( "total regions:"+ regions.size )
    
  }
  
  class Dir( val x : Int, val y : Int )
  case class N() extends Dir( 0, -1 )
  case class E() extends Dir( 1, 0 )
  case class S() extends Dir( 0, 1 )
  case class W() extends Dir( -1, 0 )
  
  val dirs = List(N(), S(), E(), W() )
  
  def buildRegions( input : List[String] ) : List[List[(Int,Int)]] = {
    
    val regions = ListBuffer[List[(Int,Int)]]()
    val visited = ListBuffer[(Int,Int)]()
    
    for( y <- 0 until 128 ){
      for( x <- 0 until 128 ){

        Console.print( input(y)(x) )
        
        val c = (x,y)
        
        // have we visited this coord already?
          val reg = visit( c, List() )
          if( reg.size > 0 ){
            regions += reg
          }
        
      }

      Console.print( '\n' )
    }
    
    def visit( c : (Int,Int), cs : List[(Int,Int)] ) : List[(Int,Int)] = {
      
      var region = cs

      if( !visited.contains(c) ){
        
        visited += c
        
        if( input(c._2)(c._1) == '1' ){ 
          
          region = region :+ c
      
          // calc neighbors
          val ns = calcNeighbors(c,128)
          for( n <- ns ){
            region = visit( n, region )
          }
      
          // already visited?
      
          // is it a '1'
        }

      }
      
      region

    }
    
    regions.toList
    
  }
  
  def calcNeighbors( c : (Int,Int), max : Int ) : List[(Int,Int)] = {
    
    val coords = ListBuffer[(Int,Int)]()
    
    for( d <- dirs ){
      
      if( isValid( c._1, d.x, max ) && isValid( c._2, d.y, max ) ){
        
        val n = ( c._1 + d.x, c._2 + d.y )
        coords += n
      }
    }
    coords.toList

  }
  
  def isValid( i : Int, inc : Int, max : Int ) : Boolean = {
    i + inc >= 0 && i + inc < max
  }
  
  def used( src : String ) : Int = {
    
    src.foldLeft(0)( (a:Int,c:Char) => {
      c match {
        case '1' => a + 1
        case _ => a
      }
    } )
    
  }
  
  def toBinary( src : String ) : String = {
    src.foldLeft("")( (a:String,c:Char) => { a + convert(c) } )
  }
  
  def convert( c : Char ) : String = {
    
    c match {
      case '0' => "0000"
      case '1' => "0001"
      case '2' => "0010"
      case '3' => "0011"
      case '4' => "0100"
      case '5' => "0101"
      case '6' => "0110"
      case '7' => "0111"
      case '8' => "1000"
      case '9' => "1001"
      case 'a' => "1010"
      case 'b' => "1011"
      case 'c' => "1100"
      case 'd' => "1101"
      case 'e' => "1110"
      case 'f' => "1111"
      case _ => "zzzz"
    }
  }
  
  
  
  def genKeys( src : String ) : List[String] = {
    
    val res = ListBuffer[String]()
    
    for( i <- 0 until 128 ){
      res += src + "-" + i
    }
    
    res.toList
    
  }
}