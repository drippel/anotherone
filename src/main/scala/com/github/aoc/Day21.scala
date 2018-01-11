package com.github.aoc

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap

object Day21 {
  
  val ex = """../.# => ##./#../...
.#./..#/### => #..#/..../..../#..#"""
  
  val data = """../.. => .##/##./.#.
#./.. => .#./#.#/##.
##/.. => #.#/#.#/###
.#/#. => #../.#./.#.
##/#. => ##./#.#/..#
##/## => #.#/#.#/...
.../.../... => ..##/##../##../#.#.
#../.../... => ##.#/..#./#.#./.#..
.#./.../... => ..#./##.#/#.##/###.
##./.../... => ###./##.#/.###/#.#.
#.#/.../... => ##../#..#/.###/#.#.
###/.../... => ...#/#..#/...#/...#
.#./#../... => ...#/.##./#.##/..#.
##./#../... => .##./.#../.##./.#..
..#/#../... => ####/.#../#.#./.###
#.#/#../... => ###./.#../##../....
.##/#../... => ##../#.#./#.#./##..
###/#../... => #.##/#..#/.#../##..
.../.#./... => .#.#/.###/.##./##..
#../.#./... => .###/.##./..##/..##
.#./.#./... => .##./.#.#/#.##/.###
##./.#./... => ..#./..../..#./###.
#.#/.#./... => ..../..#./..##/##..
###/.#./... => .#.#/#..#/.###/#..#
.#./##./... => ..../..#./.#../####
##./##./... => ..##/#.##/..#./#.##
..#/##./... => ..../#.##/.##./####
#.#/##./... => ..##/#.#./.#../.##.
.##/##./... => #.../...#/###./....
###/##./... => .#../#.#./#.##/....
.../#.#/... => #.#./####/#.../..#.
#../#.#/... => ...#/.#.#/###./.#.#
.#./#.#/... => #..#/#.../###./#.##
##./#.#/... => .##./#.../...#/#.##
#.#/#.#/... => #..#/##../##../.#..
###/#.#/... => #.#./...#/.#.#/.##.
.../###/... => .#.#/.##./..#./.#..
#../###/... => .###/..##/#.##/.#..
.#./###/... => #.../#.../.#../#...
##./###/... => .###/...#/.#.#/.#..
#.#/###/... => .#../..##/#..#/#...
###/###/... => .###/##../##.#/#.#.
..#/.../#.. => ##.#/..../...#/..##
#.#/.../#.. => .#.#/###./...#/.#.#
.##/.../#.. => ##.#/.#../####/#.##
###/.../#.. => #.../#..#/###./....
.##/#../#.. => #..#/..#./####/...#
###/#../#.. => ####/###./##.#/....
..#/.#./#.. => .##./.##./##../#..#
#.#/.#./#.. => #..#/#..#/#.../.#..
.##/.#./#.. => ##../##.#/#.##/..##
###/.#./#.. => #.##/..##/.##./#.#.
.##/##./#.. => #.##/..../##../....
###/##./#.. => ###./.#.#/.###/.#..
#../..#/#.. => .###/#.##/..#./.##.
.#./..#/#.. => #..#/..##/.#.#/##..
##./..#/#.. => ###./#.../..##/##..
#.#/..#/#.. => #.../.##./.###/###.
.##/..#/#.. => ...#/##.#/..#./...#
###/..#/#.. => ###./..#./.#../...#
#../#.#/#.. => #..#/...#/..#./.#.#
.#./#.#/#.. => #..#/##.#/####/.##.
##./#.#/#.. => .###/##../..../.#..
..#/#.#/#.. => ..#./##.#/####/###.
#.#/#.#/#.. => #.#./#.##/##.#/.###
.##/#.#/#.. => ..#./####/##../.###
###/#.#/#.. => .#.#/###./.#.#/#...
#../.##/#.. => .###/..##/.#.#/..#.
.#./.##/#.. => #.##/.#../.###/#.#.
##./.##/#.. => .###/#.../#.../..#.
#.#/.##/#.. => ##../...#/..#./...#
.##/.##/#.. => ..##/.#.#/...#/####
###/.##/#.. => ##../.###/##../###.
#../###/#.. => ###./#..#/#.#./....
.#./###/#.. => ..../#.#./.###/.###
##./###/#.. => .###/##../#..#/####
..#/###/#.. => ..../#.#./#..#/##..
#.#/###/#.. => .#.#/..##/##.#/#..#
.##/###/#.. => .#../...#/##../.#..
###/###/#.. => #.../.###/###./##.#
.#./#.#/.#. => .#.#/#.##/###./#...
##./#.#/.#. => .#../.#../.#../.#..
#.#/#.#/.#. => ##.#/..../###./.#..
###/#.#/.#. => #.#./##.#/.#.#/##..
.#./###/.#. => ##.#/..#./..#./#.#.
##./###/.#. => ####/.###/.#.#/.##.
#.#/###/.#. => .#../.###/##../#.#.
###/###/.#. => #.../.##./..##/####
#.#/..#/##. => ..../..#./##../...#
###/..#/##. => .###/..#./#.##/###.
.##/#.#/##. => .###/..../#.#./...#
###/#.#/##. => ###./...#/.###/####
#.#/.##/##. => #.##/#.../..../...#
###/.##/##. => #.../#.../#..#/...#
.##/###/##. => .#../###./.###/..#.
###/###/##. => ##.#/.#../###./.#..
#.#/.../#.# => #.#./#.#./..../...#
###/.../#.# => ####/###./..../##.#
###/#../#.# => .###/##.#/#.##/..#.
#.#/.#./#.# => ###./.###/#.##/....
###/.#./#.# => .##./###./#.#./##..
###/##./#.# => #.../.#.#/#.##/#..#
#.#/#.#/#.# => ..#./#.#./##../..##
###/#.#/#.# => ..#./.#../...#/.##.
#.#/###/#.# => ..#./###./##.#/####
###/###/#.# => #.../#.#./#..#/.#.#
###/#.#/### => ..##/.##./.#.#/#...
###/###/### => .##./..##/####/###."""
  
  val input = ".#./..#/###"
  val input2 = "#..#/..../..../#..#"

  val rules = ListBuffer[Rule]()
  val ruleMap = HashMap[Int,ListBuffer[Rule]]()
  
  case class Rule( val size : Int, val pattern : String, val output : String, val combos : List[String] )
  
  def main( args : Array[String] ) : Unit = {

    Console.println( "day 21 start" )
    
    val lines = Common.toLines( data )
    rules ++= lines.map( toRule( _ ) )
    
    val tmp = rules.groupBy( _.size )
    ruleMap ++= tmp

    var is = input.split("/").toList 
    
    var result = toGrid( is ) 
    Console.println( "start" )
    printGrid( result )
    
    for( i <- 0 until 18 ){
    
      result = transform( result )
      Console.println( "transform "+ i )
      printGrid( result )
      
      // count the on pixels
      Console.println( "on "+ onCount(result) )
      Console.println( "size "+ result.size )
    
    }
    
    Console.println( "day 21 done" )
  }
  
  def onCount( input : Array[Array[Char]] ) : Long = {
    
    var count : Long = 0
    
    val sz = input.size
    
    for( y <- 0 until sz ){
      for( x <- 0 until sz ){
        if( input(y)(x) == '#' ){
          count = count + 1
        }
      }
      
    }
    
    count 
    
  }
  
  def transform( input : Array[Array[Char]] ) : Array[Array[Char]] = {
    
    // first lets calc the size of the output grid
    
    val sz = input(0).size
    
    val subsize = if( sz % 2 == 0 ){ 2 } else { 3 }
    val dim = sz / subsize
    Console.println("grid dims:"+ dim +","+ dim )
    
    val newsubsize = if( subsize == 2 ){ 3 } else { 4 }
    val newsz = dim * newsubsize 
    Console.println("new cell size:"+ newsubsize )
    Console.println("new array size:"+ newsz )
    
    val output = Array.ofDim[Char](newsz,newsz)
    
    for( gy <- 0 until dim ){
      for( gx <- 0 until dim ){ 
        
        // now take subgrid of the current grid
        Console.println( "cell:"+ gx +","+ gy )
        var ss = ListBuffer[String]()
        for( sy <- 0 until subsize ){
          var sub = ""
          for( sx <- 0 until subsize ){
            
            val x = sx + (gx * subsize)
            val y = sy + (gy * subsize)
            // Console.println( "getting:"+ x +"," + y )
            sub = sub + input(y)(x)
          }
          ss += sub
        }

        val sub = ss.mkString("/")
        Console.println( "transforming from:'"+ sub + "'" )

        // transform according to rule
        val t = transform(sub)
        Console.println( "transforming to:'"+ t + "'" )
        val res = toGrid(t)

        // and put in the output
        for( sy <- 0 until newsubsize ){
          for( sx <- 0 until newsubsize ){
            
            val x = sx + (gx * newsubsize)
            val y = sy + (gy * newsubsize)
            output(y)(x) = res(sy)(sx)
          }
        }

      }
    }
    
    output
    
  }
  
  def transform( input : String ) : String = {
    
    val cell = input.split("/").toList
    val cs = makeCombos(cell)
    
    val sz = cell(0).size
    
    val rules = ruleMap(sz).filter( ( r: Rule ) => {
      cs.contains(r.pattern)
    } )
    
    if( rules.isEmpty ){
      Console.println( "no match found:"+ input )
      throw new IllegalStateException( "no match found:"+ input )
    }
    else if( rules.size > 1 ){
      Console.println( "should not match multiples:"+ input )
    }
    
    rules(0).output

  }
  
  def toGrid( input : String ) : Array[Array[Char]] = {
    val ps = input.split("/")
    val sz = ps.size
    val output = Array.ofDim[Char](sz,sz)
    
    for( y <- 0 until sz ){
      for( x <- 0 until sz ){
        output(y)(x) = ps(y)(x)
      }
    }
    
    
    output
  }
  
  def printGrid( lines : List[String] ) : Unit = {
    lines.foreach( Console.println( _ ) )
  }
  
  def printGrid( g : Array[Array[Char]] ) : Unit = {
    val sz = g(0).size
    for( y <- 0 until sz ){
      for( x <- 0 until sz ){
        Console.print( g(y)(x) )
      }
      Console.print( "\n" )
    }
  }

  def toGrid( lines : List[String] ) : Array[Array[Char]] = {
    
    val sz = lines(0).size
    val output = Array.ofDim[Char](sz,sz) 
    
    for( y <- 0 until sz ){
      for( x <- 0 until sz ){
        output(y)(x) = lines(y)(x)
      }
    }
    
    output
    
  }
  
  def toRule( line : String ) : Rule = {
    
    val p1 = line.split("=>")
    
    val pat = p1(0).trim()
    val out = p1(1).trim()
    
    val sp = p1(0).trim().split("/")
    val sz = sp(0).size
    
    // make all the combos
    val cs = makeCombos( sp.toList )
    
    Rule( sz, pat, out, cs ) 
    
  }
  
  def hFlip( in : List[String] ) : List[String] = {
    
    val out = ListBuffer[String]()
    
    for( s <- in ){
      out += s.reverse
    }
    
    out.toList
    
  }
  
  def rotate( in : List[String] ) : List[String] = {
    
    val out = ListBuffer[String]()
    
    // get the size
    val sz = in(0).size
    
    for( x <- 0 until sz ){
      var s = ""
      for( y <- sz - 1 to 0 by -1 ){
        s = s + in(y)(x)
        
      }
      out += s
    }
    
    out.toList
    
  }
  
  def vFlip( in : List[String] ) : List[String] = {
    in.reverse
  }
  
  def combine( a : List[String] ) : String = {
    a.mkString("/")
  }
  
  def makeCombos( a : List[String] ) : List[String] = {

    val all = HashSet[String]()
    
    var r = a
    for( i <- 0 until 4 ){
      r = rotate(r)
      all += combine(r)
    }

    var h = hFlip(a)
    for( i <- 0 until 4 ){
      h = rotate(h)
      all += combine(h)
    }
    
    
    var v = hFlip(a)
    for( i <- 0 until 4 ){
      v = rotate(v)
      all += combine(v)
    }
    
    all.toList
    
  }

  def testCombos() : Unit = {
    val a = List( ".#.", "..#", "###" )
    Console.println( makeCombos(a) )
  }
  
}