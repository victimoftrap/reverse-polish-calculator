package com.github.victimoftrap.rpc.generator.elements

trait RpnElement {
  def action(calcStack: List[Double]): List[Double]
}
