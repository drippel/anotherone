package com.github.aoc

import scala.collection.mutable.ListBuffer

case class Component( val left : Int, val right : Int )


object Day24 {

  val ex = """0/2
2/2
2/3
3/4
3/5
0/1
10/1
9/10"""
  
  val data = """24/14
30/24
29/44
47/37
6/14
20/37
14/45
5/5
26/44
2/31
19/40
47/11
0/45
36/31
3/32
30/35
32/41
39/30
46/50
33/33
0/39
44/30
49/4
41/50
50/36
5/31
49/41
20/24
38/23
4/30
40/44
44/5
0/43
38/20
20/16
34/38
5/37
40/24
22/17
17/3
9/11
41/35
42/7
22/48
47/45
6/28
23/40
15/15
29/12
45/11
21/31
27/8
18/44
2/17
46/17
29/29
45/50"""
  
  
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 24..." )
    
    val lines = Common.toLines(data)
    val cs = lines.map( toComponent( _ ) )
    Console.println( cs )
    
    val allBridges = buildBridges( cs )
    allBridges.foreach( Console.println( _ ) ) 
    
    val lengths = allBridges.map( _.size )
    val mx = lengths.sorted.last 

    Console.println( mx ) 
    
    val bs = allBridges.filter( _.size == mx )
    
    val ss = bs.map( strength( _ ) ).sorted
    Console.println( ss.last ) 
  }
  
  def strength( cs : List[Component] ) : Int = {
    
    cs.foldLeft(0)( (a:Int,b:Component) => {
      a + b.left + b.right
    })
    
  }
  
  // helper so tha we are matching left to right
  def orient( c : Component, i : Int ) : Component = {
    if( c.left == i ){ c } 
    else { Component( c.right, c.left ) }
  }
  
  def buildBridges( all : List[Component] ) : List[List[Component]] = {
    
    val bridges = ListBuffer[List[Component]]()

    val starts = findStarts( all )
    
    for( s <- starts ){
    
      // whats the other end of start?
      val newStart = orient( s, 0 )
      val remainder = remove( all, s )
      
      inner( List(s), remainder )
    
    }
    
    // if matches isEmpty - we are done
    
    // for each match tack on to current list and recurse
    
    def inner( curr : List[Component], rest : List[Component] ) : Unit = {
      
      if( rest.isEmpty ){
        // we have reached the end
        bridges += curr
      }
      else {
        
        // take the end of curr
        val ms = findMatches( rest, curr.last.right )
        if( ms.isEmpty ){
          bridges += curr
        }
        else {
          
          // for each mach
          for( m <- ms ){
            // orient
            val o = orient(m, curr.last.right )
            val nc = curr :+ o

            // remove
            val nr = remove( rest, m )

            // recurse
            inner( nc, nr )
          }
          
        }
      }
      
    }
    
    
    
    bridges.toList
  }
  
  def findMatches( all : List[Component], i : Int ) : List[Component] = {
    all.filter( ( c : Component ) => { c.left == i || c.right == i } ) 
  }
  
  def toComponent( line : String ) : Component = {
    val ps = line.split("/")
    Component( ps(0).toInt, ps(1).toInt )
  }
  
  def findStarts( cs : List[Component] ) : List[Component] = {
    cs.filter( (c:Component) => { c.left == 0 || c.right == 0 } )
  }
  
  def equals( a : Component, b : Component ) : Boolean = {
    ( a.left == b.left && a.right == b.right ) || ( a.left == b.right && a.right == b.left ) 
  }
  
  def remove( cs : List[Component], target : Component ) : List[Component] = {
    
    val tmp = ListBuffer[Component]()
    tmp ++= cs
    
    val idx = cs.indexWhere( (c:Component ) => { equals( c, target ) } )
    if( idx > -1 ){
      tmp.remove(idx)
    }
    
    tmp.toList
    
  }
    
}