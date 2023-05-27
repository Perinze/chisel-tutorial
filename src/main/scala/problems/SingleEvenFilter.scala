// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util._

// Problem:
//
// Create a composition (chain) of two filters:
//
// SingleFilter - indicates that input is single decimal digit
// (i.e. is less or equal to 9)
//
// EvenFilter   - indicates that input is even number
//
abstract class Filter[T <: Data](dtype: T) extends Module {
  val io = IO(new Bundle {
    val in = Input(Valid(dtype))
    val out = Output(Valid(dtype))
  })
}

class PredicateFilter[T <: Data](dtype: T, f: T => Bool) extends Filter(dtype) {
  io.out.valid := io.in.valid && f(io.in.bits)
  io.out.bits  := io.in.bits
}

object SingleFilter {
  def apply[T <: UInt](dtype: T) = 
    // Change function argument of Predicate filter below ----------
    Module(new PredicateFilter(dtype, (x: T) => x <= 9.U))
    // Change function argument of Predicate filter above ----------
}

object EvenFilter {
  def apply[T <: UInt](dtype: T) = 
    // Change function argument of Predicate filter below ----------
    Module(new PredicateFilter(dtype, (x: T) => x(0) === 0.U))
    // Change function argument of Predicate filter above ----------
}

class SingleEvenFilter[T <: UInt](dtype: T) extends Filter(dtype) {
  // Implement composition below ----------

  val singleFilter = SingleFilter.apply(dtype)
  val evenFilter = EvenFilter.apply(dtype)
  singleFilter.io.in.valid := io.in.valid
  evenFilter.io.in.valid := io.in.valid
  singleFilter.io.in.bits := io.in.bits
  evenFilter.io.in.bits := io.in.bits
  io.out.valid := singleFilter.io.out.valid & evenFilter.io.out.valid
  io.out.bits := io.in.bits

  // Implement composition above ----------
}
