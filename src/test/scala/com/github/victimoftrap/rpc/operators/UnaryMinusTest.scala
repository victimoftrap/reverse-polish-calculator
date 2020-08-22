package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class UnaryMinusTest extends AnyFlatSpec with Matchers {
  it should "return negative number" in {
    val args = List[Double](42)

    UnaryMinus(priority = 1).execute(args) should be (-42)
  }
}
