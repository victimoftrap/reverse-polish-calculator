package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MultiplicationTest extends AnyFlatSpec with Matchers {
  it should "return multiplication of two numbers" in {
    val args = List[Double](6, 7)

    Multiplication(priority = 1).execute(args) should be (42)
  }
}
