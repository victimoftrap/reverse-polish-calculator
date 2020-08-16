package com.github.victimoftrap.rpc.operators

import com.github.victimoftrap.rpc.operators.Associativity.Associativity

case class OpenBracket() extends Operator {
  override val priority: Int = 0

  override def arity(): Int = 1

  override def associativity(): Associativity = Associativity.LEFT

  override def execute(opArgs: List[Double]): Double = opArgs.head
}
