package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SubtractionTest extends AnyFlatSpec with Matchers {
  it should "return subtraction of two numbers" in {
    val args = List[Double](3, 2)

    Subtraction(priority = 1).execute(args) should be (-1)
  }
}
