// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

// Problem:
//
// Implement test with PeekPokeTester
//
class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {

    // Implement below ----------

    val a = rnd.nextInt(100)
    val b = rnd.nextInt(100)
    val m = a.max(b)
    poke(c.io.in0, a)
    poke(c.io.in1, b)
    step(1)
    expect(c.io.out, m)

    // Implement above ----------
  }
}
