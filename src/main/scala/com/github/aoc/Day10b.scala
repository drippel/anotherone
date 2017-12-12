package com.github.aoc

import scala.collection.mutable.ListBuffer

object Day10B {
  
  class CNode[T]( var value : T, var next : CNode[T] = null ) {
    override def toString() : String = {
      var res = "("
      res += ( value + "->" + next.value )
      res += ")"
      res
    }
  }
  
  class CList[T] {
    var list : List[CNode[T]] = List()

    override def toString() : String = {
      var res = "(" + list.size +" "
      
      for( cn <- list ){
        res += cn
      }
      
      res += ")"
      res
    }
  }
  
  object CList  {

    def createCList[T]( src : List[T] ) : CList[T] = {
      
      var cnList = ListBuffer[CNode[T]]() 
      
      for( i <- 0 until src.size ){
        cnList += new CNode( src(i) ) 
      }
      
      // now link em up
      for( i <- 0 until src.size - 1 ){
        cnList(i).next = cnList(i+1)
      }
      
      cnList.last.next = cnList.head
      
      val cl = new CList[T]()
      cl.list = cnList.toList
      cl
      
    }
    
    def getSub[T]( clist : CList[T], idx : Int, len : Int ) : List[CNode[T]] = {
      
      val ret = ListBuffer[CNode[T]]() 
      var node = clist.list(idx)
      
      for( i <- 0 until len ){
        ret += node
        node = node.next
      }
      
      ret.toList
      
    }
    
    def calcPos[T]( list : CList[T], pos : Int, offset : Int ) : Int = {
      
      var np = pos
      for( i <- offset until 0 by -1 ){
        
        np = np + 1
        if( np >= list.list.size ){
          np = 0
        }
        
      }
      
      np
    }
  }
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 10b...")
    
    val ns = ListBuffer[Int]()
    for( i <- 0 to 255 ){
      ns += i
    }
    
    // initial list
    val cl = CList.createCList(ns.toList)
    Console.println(cl)
    
    // val start = "1,2,3"
    val start = "106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36"
    val l1 = convert( start )
    Console.println(l1)
    
    val l2 = addSalt(l1)
    Console.println(l2)
    
    hash(cl,l2)
    Console.println(cl)
    
    // convert to a list of ints
    
    var sparse = ListBuffer[Int]()
    for( n <- cl.list ){
      sparse += n.value
    }
    
    // Console.println( sparse )
    
    val dense = reduce( sparse.toList )
    
    
    Console.println( dense )
    
    val hexes = dense.map( _.toHexString ) 
    
    Console.println( hexes )
    
    for( h <- hexes ){
      val s = h.reverse.padTo(2, '0').reverse
      Console.print(s)
    }
    Console.print('\n')
    
    
  }
  
  def xor( src : List[Int] ) : Int = {
    
    var res = src.head
    
    for( i <- src.tail ){
      res = res ^ i
    }
    
    res
    
  }
  
  def reduce( src : List[Int] ) : List[Int] = {
    
    Console.println(src)
    val groups = src.grouped(16)
    val res = groups.map( xor( _ ) )
    
    res.toList
    
  }
  
  def addSalt( src : List[Int] ) : List[Int] = {
    src ++ List(17,31,73,47,23)
  }
  
  
  def convert( src : String ) : List[Int] = {
    
    val is = ListBuffer[Int]()
    
    for( c <- src ){
      is += c.toInt
    }
    
    
    is.toList
    
  }
  
  def hash[T]( cl : CList[T], lens : List[Int] ) : Unit = { 
    
    // starting pos and skip are 0
    var pos = 0
    var skip = 0
    
    for( i <- 0 until 64 ){
    
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