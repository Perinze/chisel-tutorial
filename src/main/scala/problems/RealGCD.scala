// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.{Valid, DeqIO}

// Problem:
// Implement a GCD circuit (the greatest common divisor of two numbers).
// Input numbers are bundled as 'RealGCDInput' and communicated using the Decoupled handshake protocol
//
class RealGCDInput extends Bundle {
  val a = UInt(16.W)
  val b = UInt(16.W)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(16.W)))
  })

  // Implement below ----------
  val a = RegInit(0.U(16.W))
  val b = RegInit(0.U(16.W))

  io.out.valid := false.B
  io.in.ready := true.B
  when (b === 0.U) {
    io.out.valid := true.B

    io.in.ready := true.B
    a := io.in.bits.a
    b := io.in.bits.b
  }
  when (b =/= 0.U) {
    io.out.valid := false.B
    io.in.ready := false.B
    a := b
    b := a % b
  }
  io.out.bits := a
  // Implement above ----------

}
