package com.github.victimoftrap.rpc.calculator.converter.nodes

trait RpnNode {
  def action(calcStack: List[Double]): List[Double]
}
