package com.github.victimoftrap.rpc.operators

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FloorTest extends AnyFlatSpec with Matchers {
  it should "get floor of number" in {
    val args = List[Double](4.2)

    Floor(priority = 1).execute(args) should be (4)
  }
}
