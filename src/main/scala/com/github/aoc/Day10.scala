package com.github.aoc

import scala.collection.mutable.ListBuffer

object Day10 {
  
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 10...")
    
    val ns = ListBuffer[Int]()
    for( i <- 0 to 255 ){
      ns += i
    }
    
    val cl = CList.createCList(ns.toList)
    // initial list
    Console.println(cl)
    
    // val lens = List(3, 4, 1, 5 )
    val lens = List(106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36)
    
    hash( cl, lens )

    Console.println(cl)
    
  }
  
  def hash[T]( cl : CList[T], lens : List[Int] ) : Unit = { 
    
    // starting pos and skip are 0
    var pos = 0
    var skip = 0
    
    for( len <- lens ){
    
      // starting with 0 get a list of 3 items from the clist
      val sub = CList.getSub(cl, pos, len)
    
      // reverse the values
      reverse(sub)
    
      // increment pos
      pos = CList.calcPos( cl, pos, (len + skip) ) 
    
      // increment skip
      skip = skip + 1
      Console.println( "l:"+ len + " p:"+ pos + " s:"+ skip +" cl:"+  cl )
    }
  }
  
  def reverse[T]( list : List[CNode[T]] ) : Unit = {
    
    for( i <- 0 until ( list.size / 2) ){
      val a = list(i).value
      val b = list(list.size - 1 - i).value
      list(i).value = b
      list(list.size - 1 - i).value = a
      
      
    }
    
  }
  
  
}