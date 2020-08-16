package com.github.victimoftrap.rpc.calculator.converter.nodes

class NumericRpnNode(val value: Double) extends RpnNode {
  override def action(calcStack: List[Double]): List[Double] = value :: calcStack
}
