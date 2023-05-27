// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a loadable shift register with four 4-bit stages using Vec
// Shift occurs if 'shift' is asserted
// Load  occurs if 'load'  is asserted
// Whole state should be replaced with 'ins' when loaded
//
class VecShiftRegister extends Module {
  val io = IO(new Bundle {
    val ins   = Input(Vec(4, UInt(4.W)))
    val load  = Input(Bool())
    val shift = Input(Bool())
    val out   = Output(UInt(4.W))
  })
  // Implement below ----------

  val reg = Reg(Vec(4, UInt(4.W)))
  when (io.load) {
    reg := io.ins
  } .elsewhen (io.shift) {
    for (i <- 3 to 1 by -1)
      reg(i) := reg(i-1)
    reg(0) := io.ins(0)
  }
  io.out := reg(3)

  // Implement above ----------
}
