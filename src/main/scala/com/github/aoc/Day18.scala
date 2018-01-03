package com.github.aoc

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayStack
import org.apache.commons.lang3.math.NumberUtils
import scala.collection.mutable.Queue

object Day18 {
  
  val ex = """set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2"""
  
  val ex2 = """snd 1
snd 2
snd p
rcv a
rcv b
rcv c
rcv d"""
  
  val data = """set i 31
set a 1
mul p 17
jgz p p
mul a 2
add i -1
jgz i -2
add a -1
set i 127
set p 622
mul p 8505
mod p a
mul p 129749
add p 12345
mod p a
set b p
mod b 10000
snd b
add i -1
jgz i -9
jgz a 3
rcv b
jgz b -1
set f 0
set i 126
rcv a
rcv b
set p a
mul p -1
add p b
jgz p 4
snd a
set a b
jgz 1 3
snd b
set f 1
add i -1
jgz i -11
snd a
jgz f -16
jgz a -19"""
  
  class Instruction()
  case class Send( x : String ) extends Instruction
  case class Set( x : String, y : String ) extends Instruction
  case class Add( x : String, y : String ) extends Instruction
  case class Mult( x : String, y : String ) extends Instruction
  case class Mod( x : String, y : String ) extends Instruction
  case class Receive( x : String ) extends Instruction
  case class Jump( x : String, y : String ) extends Instruction
  
  def main( args : Array[String] ) : Unit = {
    Console.println("day 18...")
    
    val lines = Common.toLines(data)
    val program = lines.map( convert( _ ) )
    
    run( List(new Context( "0" ), new Context( "1" )), program )
  }
  
  def convert( line : String ) : Instruction = {
    
    val parts = line.split(" ")
    parts(0) match {
      case "snd" => { Send( parts(1) ) }
      case "set" => { Set( parts(1), parts(2) ) }
      case "add" => { Add( parts(1), parts(2) )}
      case "mul" => { Mult( parts(1), parts(2) )}
      case "mod" => { Mod( parts(1), parts(2) )}
      case "rcv" => { Receive( parts(1) )}
      case "jgz" => { Jump( parts(1), parts(2) )}
      
    }
    
  }
  
  class Context( val id : String ) {

    val registers = HashMap[String,Long]( ( "p" -> id.toLong ) )
    val messages = Queue[Long]()
    
    var sent = 0
    var received = 0
    
    var pos = 0
    
    override def toString() : String = {

      var s = "(id:"+ id +" "
      
      s += "(Regs:[" + registers + "]) "
      s += "(Msgs:[" + messages + "]) "
      s += "(Sent:" + sent + ") "
      s += "(Recd:" + received + ") "
      
      s = s + ")"
      
      s
    }
  }
  
  def regOrValue( ctx : Context,  x : String ) : Long = {
    if( NumberUtils.isCreatable(x) ){
      x.toLong
    }
    else {
      ctx.registers.get(x).getOrElse(0)
    }
  }
  
  def send( from : Context, cs : List[Context], value : Long ) : Unit = {
    from.sent = from.sent + 1
    
    val cf = cs.filter( !_.id.equals( from.id ) )
    cf.foreach( ( c : Context ) => {
      c.messages.enqueue(value)
      
    } )
  }
  
  def deadlock( cs : List[Context] ) : Boolean = {
    cs.forall( _.messages.isEmpty )
  }
  
  def switch( curr : Context, cs : List[Context] ) : Context = {
    val cf = cs.filter( !_.id.equals( curr.id ) )
    cf.head
  }
  
  def run( cs : List[Context], program : List[Instruction] ) : Int = {
    
    // start with 0
    var ctx = cs(0)

    while( ctx.pos < program.size ){
      Console.println( program(ctx.pos) )
      Console.println( ctx.registers )

      program(ctx.pos) match {
        case Send(x) => {
          val msg = regOrValue( ctx, x)
          Console.println("Send:"+ msg )
          send( ctx, cs, msg )
          ctx.pos = ctx.pos + 1
        }
        case Set( x, y ) => {
          val a = regOrValue( ctx, y)
          ctx.registers += ( x -> a )
          ctx.pos = ctx.pos + 1
        }
        case Add( x, y ) => {
          val a = regOrValue( ctx, x)
          val b = regOrValue( ctx, y)
          ctx.registers += ( x -> (a + b) )
          ctx.pos = ctx.pos + 1
        }
        case Mult( x, y ) => {
          val a = regOrValue( ctx, x)
          val b = regOrValue( ctx, y)
          ctx.registers += ( x -> (a * b) )
          ctx.pos = ctx.pos + 1
        }
        case Mod( x, y ) => {
          val a = regOrValue( ctx, x)
          val b = regOrValue( ctx, y)
          ctx.registers += ( x -> (a % b) )
          ctx.pos = ctx.pos + 1
        }
        case Receive( x ) => {
          
          // is this a deadlock situation?
          if( deadlock( cs ) ){
            // stop processing
            Console.println(  "deadlock" )
            ctx.pos = program.size + 1
          }
          else {
          
            // are there messages in the queue?
            if( ctx.messages.isEmpty ){
              // switch
              val next = switch( ctx, cs )
              Console.println( "switch curr:"+ ctx.id +" new:" + next.id )
              ctx = next
            }
            else {
            
              val msg = ctx.messages.dequeue()
              Console.println( "Recieved:" + msg )
              ctx.registers += ( x -> msg )
              ctx.received = ctx.received + 1
              ctx.pos = ctx.pos + 1
          
            }
          }
        }
        case Jump( x, y ) => {
          val a = regOrValue( ctx, x)
          val b = regOrValue( ctx, y)
          if( a > 0 ){
            ctx.pos = ctx.pos + b.toInt
          }
          else {
            ctx.pos = ctx.pos + 1
          }
        }
      }
    }
    
    cs.foreach( Console.println( _ ) ) 
    0
    
  }
  
  
}