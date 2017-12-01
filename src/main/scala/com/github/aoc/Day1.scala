package com.github.aoc

import scala.collection.mutable.ListBuffer

object Day1 {
  
  val example1 = "1122" //  produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
  val example2 = "1111" //  produces 4 because each digit (all 1) matches the next.
  val example3 = "1234" //  produces 0 because no digit matches the next.
  val example4 = "91212129" // 9 last and first
  
  val input = "36743676522426214741687639282183216978128565594112364817283598621384839756628424146779311928318383597235968644687665159591573413233616717112157752469191845757712928347624726438516211153946892241449523148419426259291788938621886334734497823163281389389853675932246734153563861233894952657625868415432316155487242813798425779743561987563734944962846865263722712768674838244444385768568489842989878163655771847362656153372265945464128668412439248966939398765446171855144544285463517258749813731314365947372548811434646381595273172982466142248474238762554858654679415418693478512641864168398722199638775667744977941183772494538685398862344164521446115925528534491788728448668455349588972443295391385389551783289417349823383324748411689198219329996666752251815562522759374542652969147696419669914534586732436912798519697722586795746371697338416716842214313393228587413399534716394984183943123375517819622837972796431166264646432893478557659387795573234889141897313158457637142238315327877493994933514112645586351127139429281675912366669475931711974332271368287413985682374943195886455927839573986464555141679291998645936683639162588375974549467767623463935561847869527383395278248952314792112113126231246742753119748113828843917812547224498319849947517745625844819175973986843636628414965664466582172419197227695368492433353199233558872319529626825788288176275546566474824257336863977574347328469153319428883748696399544974133392589823343773897313173336568883385364166336362398636684459886283964242249228938383219255513996468586953519638111599935229115228837559242752925943653623682985576323929415445443378189472782454958232341986626791182861644112974418239286486722654442144851173538756859647218768134572858331849543266169672745221391659363674921469481143686952478771714585793322926824623482923579986434741714167134346384551362664177865452895348948953472328966995731169672573555621939584872187999325322327893336736611929752613241935211664248961527687778371971259654541239471766714469122213793348414477789271187324629397292446879752673" 
  
  val example5 = "1212"
  val example6 = "1221"
  val example7 = "123425"
  val example8 = "123123"
  val example9 = "12131415"
  
  def main( args : Array[String] ) : Unit = {
    Console.println( "day 1" )
    
    // Console.println( scan( example1.toList ) + " "+ sum( scan( example1.toList ) ) )
    // Console.println( scan( example2.toList ) + " "+ sum( scan( example2.toList ) ) )
    // Console.println( scan( example3.toList ) + " "+ sum( scan( example3.toList ) ) )
    // Console.println( scan( example4.toList ) + " "+ sum( scan( example4.toList ) ) )
    // Console.println( scan( input.toList )    + " "+ sum( scan( input.toList ) ) )
    
    Console.println( scan2( example5.toList ) + " "+ sum( scan2( example5.toList ) ) )
    Console.println( scan2( example6.toList ) + " "+ sum( scan2( example6.toList ) ) )
    Console.println( scan2( example7.toList ) + " "+ sum( scan2( example7.toList ) ) )
    Console.println( scan2( example8.toList ) + " "+ sum( scan2( example8.toList ) ) )
    Console.println( scan2( example9.toList ) + " "+ sum( scan2( example9.toList ) ) )
    Console.println( scan2( input.toList )    + " "+ sum( scan2( input.toList ) ) )
  }
  
  def sum( src : List[Char] ) : Int = {
    val is = src.map( convert( _ ) )
    
    is.foldLeft(0)( (a:Int, b:Int) => { a + b })
  }
  
  def scan( src : List[Char] ) : List[Char] = {
    
    val res = ListBuffer[Char]()
    
    val trick = src :+ src(0) 
    
    for( i <- 0 until src.size ){
      
      if( trick(i) == trick(i+1) ){
        res += trick(i)
      }
      
    }
    
    res.toList
    
    
  }
  
  def scan2( src : List[Char] ) : List[Char] = {
    
    val res = ListBuffer[Char]()
    
    val trick = src ++ src 
    // Console.println( trick )
   
    val dist = src.length / 2
    
    for( i <- 0 until src.size ){
      
      // Console.println( trick(i) +" "+ trick( i + dist ) )
      
      if( trick(i) == trick(i+dist) ){
        res += trick(i)
      }
      
    }
    
    res.toList
    
  }
  
  def convert( c : Char ) : Int = {
    
    c match {
      case '1' => 1
      case '2' => 2
      case '3' => 3
      case '4' => 4
      case '5' => 5
      case '6' => 6
      case '7' => 7
      case '8' => 8
      case '9' => 9
      case _ => 0
    }
    
  }
  
}