package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PowerTest extends AnyFlatSpec with Matchers {
  it should "raise a number to a power" in {
    val args = List[Double](2, 5)

    Power(priority = 1).execute(args) should be (25)
  }
}
