package com.github.aoc

object Day23b {
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 23b..." )
    run()
  }
  
  def run() : Unit = {
    
    var a : Long = 0
    var b : Long = 0
    var c : Long = 0
    var d : Long = 0
    var e : Long = 0
    var f : Long = 0
    var g : Long = 0
    var h : Long = 0
    
    a = 1

// set b 57
    b = 57

// set c b
    c = b

// mul b 100               
    b = b * 100 
// sub b -100000              
    b = b - (-100000 )

// set c b                    
    c = b
// sub c -17000               
    c = c - (-17000)

    var stop = false
    do {
// set f 1    <-----------| 
    f = 1
// set d 2                |
    d = 2

    do {
// set e 2    <-------|   |
    e = 2

    do {
// set g d     <---|  |   |
      g = d
// mul g e         |  |   |
    g = g * e
// sub g b         |  |   |
    g = g -b

// jnz g 2     --| |  |   |
    if( g == 0 ){
// set f 0       | |  |   |
      f = 0
    }
// sub e -1    <-| |  |   |
    e = e - (-1)

// set g e         |  |   |
    g = e
// sub g b         |  |   |
    g = g - b

// jnz g -8    ----|  |   |
    } while ( g != 0 )

// sub d -1           |   | 
    d = d - (-1)
// set g d            |   |
    g = d
// sub g b            |   |
    g = g - b
// jnz g -13  --------|   |
    } while( g != 0 )

// jnz f 2    --|         |
    if( f == 0 ){
// sub h -1     |         |
      h = h - (-1)
    }
// set g b    <-|         |
    g = b
// sub g c                |
    g = g - c
// jnz g 2   -------|     |
    if( g == 0 ){
// jnz 1 3   ---|   |     |
      stop = true
    }
// sub b -17    | <-|     |
    b = b - (-17)
// jnz 1 -23 ---|---------|
      Console.println( "a:"+ a +" b:"+ b +" c:"+ c +" d:"+ d +" e:"+ e +" f:"+ f +" g:"+ g +" h:"+ h  )
    } while( !stop )
//              |
//              V
    Console.println( "a:"+ a +" b:"+ b +" c:"+ c +" d:"+ d +" e:"+ e +" f:"+ f +" g:"+ g +" h:"+ h  )
  }
}