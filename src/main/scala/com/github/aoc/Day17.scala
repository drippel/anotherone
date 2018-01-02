package com.github.aoc

object Day17 {
  
  val limit = 50000000 
  
  var listSize = 0
  var listPos = 0
  var listLast = -1
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 17..." )
    
    // var pos = 0
    // val list = CList.createCList( List(0) )

    listSize = 1
    listPos = 0
    
    for( i <- 1 to limit ){
    
      // pos = CList.calcPos( list, pos, 380 )
      listPos = calcPos( listPos, 380 )
      // Console.println( "p:"+ pos +" lp:"+ listPos )

      // pos = pos + 1
      listPos = listPos + 1

      // CList.insert( list, pos, i )
      insert( listPos, i )

      // if( pos == 1 ){
        // Console.println( list )
      // }
    }

    // Console.println( "Pos:"+ pos  )
    Console.println( "Size:"+ listSize +" Pos:"+ listPos  +" Last:"+ listLast )
  }
  
  def calcPos[T]( pos : Int, offset : Int ) : Int = {
      
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
      listLast = value
      Console.println( listLast )
    }

    listSize = listSize + 1

  }

}