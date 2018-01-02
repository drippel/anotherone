package com.github.aoc

import scala.collection.mutable.ListBuffer


 class CNode[T]( var value : T, var next : CNode[T] = null ) {
    override def toString() : String = {
      var res = "("
      res += ( value )
      res += ")"
      res
    }
  }
  
  class CList[T] {

    val list = ListBuffer[CNode[T]]()

    override def toString() : String = {
      var res = "(" + list.size +" "
      
      for( cn <- list ){
        res += cn
      }
      
      res += ")"
      res
    }
  }

  object CList {
  
    def createCList[T]( src : List[T] ) : CList[T] = {
      
      val cl = new CList[T]()
      
      for( i <- 0 until src.size ){
        cl.list += new CNode( src(i) ) 
      }
      
      // now link em up
      for( i <- 0 until src.size - 1 ){
        cl.list(i).next = cl.list(i+1)
      }
      
      cl.list.last.next = cl.list.head
      
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
    
    def insert[T]( list : CList[T], pos : Int, value : T ) : Unit = {
      
      if( pos >= list.list.size ){
        
        // insert at the end
        val c1 = list.list.last
        val c2 = list.list.head
        
        val cn = new CNode( value )
        c1.next = cn
        cn.next = c2
      
        // and because we are really just cheating
        list.list += cn 
      }
      else {
        // get the node at pos
        // and the node after pos
        val c1 = list.list(pos)
      
        val cn = new CNode( value )
        cn.next = c1.next
        c1.next = cn
      
        // and because we are really just cheating
        list.list.insert(pos, cn)
      }
      
      
    }
  }