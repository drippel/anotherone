package com.github.aoc

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

object Day13 {
  
  val ex = """0: 3
1: 2
4: 4
6: 4"""
  
  val data = """0: 4
1: 2
2: 3
4: 4
6: 6
8: 5
10: 6
12: 6
14: 6
16: 8
18: 8
20: 9
22: 12
24: 8
26: 8
28: 8
30: 12
32: 12
34: 8
36: 12
38: 10
40: 12
42: 12
44: 10
46: 12
48: 14
50: 12
52: 14
54: 14
56: 12
58: 14
60: 12
62: 14
64: 18
66: 14
68: 14
72: 14
76: 14
82: 14
86: 14
88: 18
90: 14
92: 17"""
  
 def main( args : Array[String] ) : Unit = {

   Console.println( "day 13" )
   
   makeDepths( Common.toLines(data) )
   calcLayers()
   initScanners()
   
   Console.println("start")
   print()
   run()
   Console.println("final")
   print()
   
   // Console.println( calcCollisions() )
   
  }
  
  def run() : Unit = {
    
    var time = 0
    
    while( winners.isEmpty ){
      
      // Console.println("before")
      // print()
      
      // add a new runner at this time
      runners += (time -> new Runner( time ))  
    
      // move runners
      for( r <- runners ){

        r._2.pos = r._2.pos + 1
      
        // detect collision
        scanners.get(r._2.pos) match {
          case Some(s) => {
            if( s.pos == 0 ){
              // collision
              r._2.collisions += r._2.pos
            }
          }
          case None => {
            // open
          }
        }
        
        if( r._2.pos >= layers ){
          runners.remove(r._1)
          if( r._2.collisions.size > 0 ){
            losers += r._2 
          }
          else {
            winners += r._2 
          }
        }

      }
    
      // move scanners
      for( k <- scanners ){
        
        // whats the depth at this layer
        val d = depths(k._1)
        
        var pos = k._2.pos + k._2.dir 
        
        // if the new pos is out of range 
        if( pos < 0 || pos >= d ){
          
          // change the direction
          k._2.dir = 0 - k._2.dir
          
          k._2.pos = k._2.pos + k._2.dir
          
        }
        else {
          k._2.pos = pos
        }
      }
      
      Console.println("time:" + time )
      time = time + 1

    }
    // print()
  }

  def calcLayers() : Unit = {
    layers = depths.keys.toList.sorted.last
  }
  
  def print() : Unit = {
    for( r <- runners ){
      Console.println( "Runner: t: "+ r._2.start + " p:"+ r._2.pos +" cs:"+ r._2.collisions )
    }
    
    /*
    for( l <- losers ){
      Console.println( "Loser: t: "+ l.start + " p:"+ l.pos +" cs:"+ l.collisions )
    }
    */

    for( w <- winners ){
      Console.println( "Winner: t: "+ w.start + " p:"+ w.pos +" cs:"+ w.collisions )
    }

    
    for( i <- 0 to layers ){
      if( depths.contains(i) ){
        Console.print( "(" + i +","+ scanners(i).pos +")  " ) 
      }
      else {
        Console.print( "(" + i +",N)  " ) 
      }
    }
    Console.print( "\n" )

  }
  
  def initScanners() : Unit = {
    depths.foreach( ( p : (Int,Int) ) => {
      scanners += (p._1 -> new Scanner())
    } )
  }
  
  def makeDepths( lines : List[String] ) : Unit = {
    
    for( line <- lines ){
      val parts = line.split(':')
      depths += (parts(0).trim().toInt -> parts(1).trim().toInt )
    }
    
  }
  
  class Scanner( var pos : Int = 0, var dir : Int = 1 )
  
  // runners start outside
  class Runner( val start : Int, var pos : Int = -1, val collisions : ListBuffer[Int] = ListBuffer[Int]() )
  
  val depths = HashMap[Int,Int]()

  val scanners = HashMap[Int,Scanner]()
  
  val runners = HashMap[Int,Runner]() 
  
  val winners = ListBuffer[Runner]()
  val losers  = ListBuffer[Runner]()
  
  var layers = -1
  
  /*
  def calcCollisions() : Int = {
    
    var ret = 0
    
    for( c <- collision ){
      
      val d = depths(c)
      
      ret += ( c * d )
      
    }
    
    
    ret
    
  }
  * 
  */
}