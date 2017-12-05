package com.github.aoc

object Common {
  
  
  def toLines( raw : String, sep : Char = '\n' ) : List[String] = {
    val ls = raw.split( sep )
    val ts = ls.map( (s : String) => { s.trim() } )
    ts.toList
  }
  
  def toWords( line : String, sep : Char = ' ' ) : List[String] = {
    val ls = line.split( sep )
    ls.toList
  }
  
}