package com.github.victimoftrap.rpc.generator.elements

import com.github.victimoftrap.rpc.operators.Operator

class OperatorRpnElement(val operator: Operator) extends RpnElement {

  override def action(calcStack: List[Double]): List[Double] = {
    val (operands, rest) = calcStack.splitAt(operator.arity())
    val result = operator.execute(operands)
    result :: rest
  }
}
