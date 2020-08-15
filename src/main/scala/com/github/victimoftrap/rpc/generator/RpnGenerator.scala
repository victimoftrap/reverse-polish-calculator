package com.github.victimoftrap.rpc.generator

import com.github.victimoftrap.rpc.generator.elements.RpnElement

trait RpnGenerator {
  def generate(tokens: List[String]): List[RpnElement]
}
