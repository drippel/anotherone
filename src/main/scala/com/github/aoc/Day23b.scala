package com.github.aoc

object Day23b {

  def main(args: Array[String]): Unit = {
    Console.println("day 23b...")
    run()
  }

  def run(): Unit = {

    var a: Long = 0
    var b: Long = 0
    var c: Long = 0
    var d: Long = 0
    var e: Long = 0
    var f: Long = 0
    var g: Long = 0
    var h: Long = 0

    a = 1
    Console.println("00   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

    // set b 57
    b = 57
    Console.println("01   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

    // set c b
    c = b
    Console.println("02   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

    // mul b 100               
    b = b * 100
    Console.println("05   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)
    // sub b -100000              
    b = b - (-100000)
    Console.println("06   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

    // set c b                    
    c = b
    Console.println("07   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)
    // sub c -17000               
    c = c - (-17000)
    Console.println("08   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

    var stop = false
    do {
      Console.println("outer")
      // set f 1    <-----------| 
      f = 1
      Console.println("09   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

      // set d 2                |
      d = 2
      Console.println("10   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

      do {
        // set e 2    <-------|   |
        e = 2
        Console.println("11   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

        do {

          // set g d     <---|  |   |
          g = d
          Console.println("12   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

          // mul g e         |  |   |
          g = g * e
          Console.println("13   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

          // sub g b         |  |   |
          g = g - b
          Console.println("14   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

          // jnz g 2     --| |  |   |
          if (g == 0) {
            // set f 0       | |  |   |
            f = 0
            Console.println("16   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)
          }
          // sub e -1    <-| |  |   |
          e = e - (-1)
          Console.println("17   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

          // set g e         |  |   |
          g = e
          Console.println("18   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

          // sub g b         |  |   |
          g = g - b
          Console.println("19   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

          // jnz g -8    ----|  |   |

        } while (g != 0)

        // sub d -1           |   | 
        d = d - (-1)
        Console.println("21   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

        // set g d            |   |
        g = d
        Console.println("22   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

        // sub g b            |   |
        g = g - b
        Console.println("23   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

        // jnz g -13  --------|   |

      } while (g != 0)

      // jnz f 2    --|         |
      if (f == 0) {
        // sub h -1     |         |
        h = h - (-1)
        Console.println("26   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)
      }
      // set g b    <-|         |
      g = b
      Console.println("27   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

      // sub g c                |
      g = g - c
      Console.println("28   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

      // jnz g 2   -------|     |
      if (g == 0) {
        // jnz 1 3   ---|   |     |
        stop = true
        Console.println("30   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)
      }
      // sub b -17    | <-|     |
      b = b - (-17)
      Console.println("31   a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)

      // jnz 1 -23 ---|---------|

    } while (!stop)
    //              |
    //              V
    Console.println("exit:  a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f + " g:" + g + " h:" + h)
  }
}