package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DivisionTest extends AnyFlatSpec with Matchers {
  it should "divide two numbers" in {
    val args = List[Double](2, 5)

    Division(priority = 1).execute(args) should be (2.5)
  }
}
