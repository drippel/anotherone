package com.github.aoc

import scala.collection.mutable.HashMap

object Day25 {
  
  
  class State( 
    val nm : Char,
    val zeroWrite : Int,
    val zeroMove : Int,
    val zeroState : Char,
    val oneWrite : Int,
    val oneMove : Int,
    val oneState : Char )
  case class A() extends State( 'A', 1, 1, 'B', 0, 1, 'F' )
  case class B() extends State( 'B', 0, -1, 'B', 1, -1, 'C' )
  case class C() extends State( 'C', 1, -1, 'D', 0, 1, 'C' )
  case class D() extends State( 'D', 1, -1, 'E', 1, 1, 'A' )
  case class E() extends State( 'E', 1, -1, 'F', 0, -1, 'D' )
  case class F() extends State( 'F', 1, 1, 'A', 0, -1, 'E' )

  val stateMap = {
    val map = HashMap[Char,State]()
    
    map += ( A().nm -> A() )
    map += ( B().nm -> B() )
    map += ( C().nm -> C() )
    map += ( D().nm -> D() )
    map += ( E().nm -> E() )
    map += ( F().nm -> F() )
    
    map.toMap
  }

  
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 25..." )
    run()
  }
  
  def run() : Unit = {
    
    val tape = HashMap[Long,Long]()
    tape += ( 0L -> 0L )
    var cursor : Long = 0
    var checkSumLimit : Long = 12964419 // 6 
    
    var state : State = A()
    
    Console.println( "start" )

    printTape( tape )
    
    var stepCount : Long = 0
    while( stepCount < checkSumLimit ) {
      
      val res = execute( tape.getOrElse( cursor, 0L ), state )
      
      tape += (cursor -> res._1)
      cursor = cursor + res._2
      state = res._3
      
      stepCount = stepCount + 1
      
    }
    
    
    Console.println( checksum( tape.toMap ) )
    printTape(tape)
    
  }
  
  def printTape( tape : HashMap[Long,Long] ) : Unit = {
    
    val min = tape.keys.toList.sorted.head
    val max = tape.keys.toList.sorted.last
    
    for( i <- min to max ){
      
      Console.print( tape.getOrElse(i, 0) )
      Console.print( ' ' )
      
    }
    Console.print( '\n' )
    
  }
  
  def execute( cv : Long, state : State ) : (Long,Long,State) = {
    cv match {
      case 0L => { (state.zeroWrite, state.zeroMove, stateMap(state.zeroState)  ) }
      case 1L => { (state.oneWrite, state.oneMove, stateMap(state.oneState) ) }
    }
  }
  
  
  def checksum( map : Map[Long,Long] ) : Long = {
    
    val ones = map.values.filter( _ == 1 )
    ones.size
    
  }
  
}