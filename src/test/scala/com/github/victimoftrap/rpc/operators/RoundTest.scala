package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RoundTest extends AnyFlatSpec with Matchers {
  it should "get round of number" in {
    val args = List[Double](4.2)

    Ceil(priority = 1).execute(args) should be(5)
  }
}
