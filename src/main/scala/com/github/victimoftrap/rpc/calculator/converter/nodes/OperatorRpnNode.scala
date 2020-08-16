package com.github.victimoftrap.rpc.calculator.converter.nodes

import com.github.victimoftrap.rpc.operators.Operator

class OperatorRpnNode(val operator: Operator) extends RpnNode {
  override def action(calcStack: List[Double]): List[Double] = {
    val (operands, rest) = calcStack.splitAt(operator.arity())
    val result = operator.execute(operands)
    result :: rest
  }
}
