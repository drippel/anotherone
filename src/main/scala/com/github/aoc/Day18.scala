package com.github.aoc

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayStack
import org.apache.commons.lang3.math.NumberUtils

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
  
  def main( args : Array[String] ) : Unit = {
    Console.println("day 18...")
    
    val lines = Common.toLines(data)
    val program = lines.map( convert( _ ) )
    
    Console.println( program )
    run(program)
  }
  
  def run( program : List[(Context) => (Int,Int) ]) : Unit = {
    
    val ctx = new Context()
    
    debug(ctx)
    
    // init
    
    while( ctx.instr < program.size ){
      val res = program(ctx.instr)(ctx)
      if( res._1 != 1 ){
        ctx.instr = program.size + 1
      }
      else {
        ctx.instr = ctx.instr + res._2
      }
      // debug(ctx)
    }
    
  }
  
  def debug( ctx : Context ) : Unit = {
    Console.println( "Instr:"+ ctx.instr )
    Console.println( "Regs:"+ ctx.registers )
    Console.println( "Sounds:"+ ctx.sounds )
    Console.println( "" )
  }
  
  
  class Context() {
    val registers = HashMap[String,Int]()
    val sounds = ArrayStack[Int]()
    var instr = 0
  }
  
  def convert( line : String ) : (Context) => (Int,Int) = {
    
    val ps = line.split(" ")
    
    ps(0) match {
      
      case "snd" => { send( ps(1) ) }
      case "set" => { set( ps(1) )( ps(2) ) }
      case "add" => { add( ps(1) )( ps(2) ) }
      case "mul" => { mult( ps(1) )( ps(2) ) }
      case "mod" => { mod( ps(1) )( ps(2) )}
      case "rcv" => { recover( ps(1) )}
      case "jgz" => { jump( ps(1) )( ps(2) ) }
      
    }

  }
  
  def regOrValue( r : String, ctx : Context ) : Int = {
    
    if( NumberUtils.isCreatable(r) ){
      r.toInt
    }
    else {
      ctx.registers.get(r) match {
        case Some(i) => { i }
        case None => { 0 }
      }
    }
    
  }
  
  def send( x : String )( ctx : Context) : (Int,Int) = {
    val s = regOrValue( x, ctx )
    ctx.sounds.push(s)
    Console.println("Send:"+ s )
    (1,1)
  }

  def set( x : String)(y : String )( ctx : Context) : (Int,Int) = { 
    val v = regOrValue( y, ctx )
    ctx.registers += (x -> v)
    (1,1)
  }

  def add( x : String)(y : String )( ctx : Context) : (Int,Int) = { 
    
    val a = regOrValue( x, ctx )
    val b = regOrValue( y, ctx )
    
    val s = a + b
    ctx.registers += (x -> s)
    (1,1)
  }

  def mult( x : String)(y : String )( ctx : Context) : (Int,Int) = { 

    val a = regOrValue( x, ctx )
    val b = regOrValue( y, ctx )
    
    val s = a * b
    ctx.registers += (x -> s)
    (1,1)
  }

  def mod( x : String)(y : String )( ctx : Context) : (Int,Int) = { 
    val a = regOrValue( x, ctx )
    val b = regOrValue( y, ctx )
    
    val s = a % b
    ctx.registers += (x -> s)
    (1,1)
  }

  def recover( x : String )( ctx : Context) : (Int,Int) = { 
    val v = regOrValue(x,ctx)
    if( v != 0 ){
      val r = ctx.sounds.pop()
      Console.println( "Recover:"+ r ) 
      (0,0) 
    }
    else {
      (1,1)
    }
  }

  def jump( x : String)(y : String )( ctx : Context) : (Int,Int) = { 
    val a = regOrValue( x, ctx )
    if( a > 0 ){
      (1,regOrValue(y, ctx))
    }
    else {
      (1,1)
    }
  }  

}