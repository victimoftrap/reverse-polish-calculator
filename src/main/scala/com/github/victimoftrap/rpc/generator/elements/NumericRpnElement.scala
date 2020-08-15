package com.github.victimoftrap.rpc.generator.elements

class NumericRpnElement(val value: Double) extends RpnElement {
  override def action(calcStack: List[Double]): List[Double] = value :: calcStack
}
