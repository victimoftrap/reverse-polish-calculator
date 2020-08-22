package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CeilTest extends AnyFlatSpec with Matchers {
  it should "get ceil of number" in {
    val args = List[Double](4.2)

    Ceil(priority = 1).execute(args) should be (5)
  }
}
