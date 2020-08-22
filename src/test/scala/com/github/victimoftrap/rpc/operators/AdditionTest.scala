package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AdditionTest extends AnyFlatSpec with Matchers {
  it should "return sum of two numbers" in {
    val args = List[Double](2, 3)

    Addition(priority = 1).execute(args) should be (5)
  }
}
