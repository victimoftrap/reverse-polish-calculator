package com.github.victimoftrap.rpc.calculator

import com.github.victimoftrap.rpc.generator.RpnGenerator
import com.github.victimoftrap.rpc.tokenizer.Tokenizer

class SimpleCalculator(private val tokenizer: Tokenizer,
                       private val generator: RpnGenerator) extends Calculator {
  override def calculate(string: String): Double = {
    val tokens = tokenizer.tokens(string)
    val rpn = generator.generate(tokens)

    var calculationStack = List[Double]()
    rpn.foreach(elem => {
      calculationStack = elem.action(calculationStack)
    })
    calculationStack.head
  }
}
