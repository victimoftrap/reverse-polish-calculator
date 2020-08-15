package com.github.victimoftrap.rpc.operators

import com.github.victimoftrap.rpc.operators.Associativity.Associativity

case class Round(override val priority: Int) extends Operator {
  override def arity(): Int = 1

  override def associativity(): Associativity = Associativity.RIGHT

  override def execute(opArgs: List[Double]): Double = Math.round(opArgs.head)
}
