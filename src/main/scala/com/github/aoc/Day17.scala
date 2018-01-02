package com.github.aoc

object Day17 {
  
  val limit = 50000000 
  
  var listSize = 0
  var pos = 0
  var last = -1
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 17..." )
    
    for( i <- 1 to 50000 ){
    
      pos = calcPos( 380 )
      pos = pos + 1
      insert( pos, i )
    }

    Console.println( "Size:"+ listSize +" Pos:"+ pos  +" Last:"+ last )
  }
  
  def calcPos[T]( offset : Int ) : Int = {
      
    var np = pos
    for( i <- offset until 0 by -1 ){
        
      np = np + 1
      if( np >= listSize ){
        np = 0
      }
    }
      
    np
  }
    
  def insert(pos : Int, value : Int ) : Unit = {
      
    if( pos == 1 ){
      last = value
    }

    listSize = listSize + 1

  }

}