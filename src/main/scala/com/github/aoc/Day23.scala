package com.github.aoc

import scala.collection.mutable.HashMap
import org.apache.commons.lang3.math.NumberUtils

object Day23 {
  
  val data = """set b 57
set c b
jnz a 2
jnz 1 5
mul b 100
sub b -100000
set c b
sub c -17000
set f 1
set d 2
set e 2
set g d
mul g e
sub g b
jnz g 2
set f 0
sub e -1
set g e
sub g b
jnz g -8
sub d -1
set g d
sub g b
jnz g -13
jnz f 2
sub h -1
set g b
sub g c
jnz g 2
jnz 1 3
sub b -17
jnz 1 -23"""
  
  class Instruction
  case class Set( x : String, y : String ) extends Instruction
  case class Subtract( x : String, y : String ) extends Instruction 
  case class Multiply( x : String, y : String ) extends Instruction 
  case class JumpNotZero( x : String, y : String ) extends Instruction  
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 23..." )
    val lines = Common.toLines(data)
    
    val program = lines.map( toInstruction( _ ) )
    
    run(program)
    Console.println( "done" )
  }
  
  def toInstruction( line : String ) : Instruction = {
    
    val ps = line.split(" ")
    
    ps(0) match {
      case "set" => { Set( ps(1), ps(2) ) }
      case "sub" => { Subtract( ps(1), ps(2) ) }
      case "mul" => { Multiply( ps(1), ps(2) ) }
      case "jnz" => { JumpNotZero( ps(1), ps(2) ) }
    }
    
  }
  
  def initRegs() : HashMap[Char,Long] = {
    val map = HashMap[Char,Long]()
    
    // map += ( 'a' -> 0 )
    map += ( 'a' -> 1 )
    map += ( 'b' -> 0 )
    map += ( 'c' -> 0 )
    map += ( 'd' -> 0 )
    map += ( 'e' -> 0 )
    map += ( 'f' -> 0 )
    map += ( 'g' -> 0 )
    map += ( 'h' -> 0 )
    
    
    map
  }
  
  def regOrValue( in : String, regs : HashMap[Char,Long] ) : Long = {
    
    if( NumberUtils.isCreatable(in) ){
      in.toLong
    }
    else {
      regs(in(0))
    }
    
  }
  
  def printRegs( rs : HashMap[Char,Long] ) : Unit = {
    Console.println( "A:"+ rs('a') +" B:"+ rs('b') +" C:"+ rs('c') +" D:"+ rs('d') +" E:"+ rs('e') +" F:"+ rs('f') +" G:"+ rs('g') +" H:"+ rs('h') )
  }
  
  
  def run( program : List[Instruction] ) : Unit = {
    
    var mults = 0
    val registers = initRegs()
    var continue = true 
    var pos = 0
    printRegs(registers)
    
    while( continue && pos < program.size ){
      
      Console.println( "Executing:" + pos +" "+ program(pos) )
      
      // get the instruction 
      program(pos) match {
        
        case Set(x,y) => {
          registers += (x(0) -> regOrValue(y,registers) )
          pos = pos + 1
        }
        case Multiply(x,y) => {
          mults = mults + 1
          val a = regOrValue(x,registers)
          val b = regOrValue(y,registers)
          val m = a * b
          registers += (x(0) -> m )
          pos = pos + 1
        }
        case Subtract(x,y) => {
          val a = regOrValue(x,registers)
          val b = regOrValue(y,registers)
          val s = a - b
          registers += (x(0) -> s )
          pos = pos + 1
        }
        case JumpNotZero(x,y) => {
          val a = regOrValue(x,registers)
          val b = regOrValue(y,registers)
          if( a != 0 ){
            pos = pos + b.toInt
          }
          else {
            pos = pos + 1
          }
        }
        
      }
      
      printRegs( registers )
      Console.println("")

      
    }
    Console.println(mults)
    
  }
}