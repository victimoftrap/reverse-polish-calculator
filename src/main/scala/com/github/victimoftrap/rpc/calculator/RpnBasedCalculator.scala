package com.github.victimoftrap.rpc.calculator

import com.github.victimoftrap.rpc.calculator.converter.TokenConverter
import com.github.victimoftrap.rpc.token.Token

class RpnBasedCalculator(private val converter: TokenConverter) extends Calculator {
  override def calculate(tokens: List[Token]): Double = {
    val computableRpnNodes = converter.toComputableNodes(tokens)

    var calculationStack = List[Double]()
    computableRpnNodes.foreach(node => {
      calculationStack = node.action(calculationStack)
    })
    calculationStack.head
  }
}
