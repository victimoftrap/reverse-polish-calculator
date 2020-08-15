package com.github.victimoftrap.rpc.operators

import com.github.victimoftrap.rpc.operators.Associativity.Associativity

case class Power(override val priority: Int) extends Operator {
  override def arity(): Int = 2

  override def associativity(): Associativity = Associativity.LEFT

  override def execute(opArgs: List[Double]): Double = Math.pow(opArgs(1), opArgs.head)
}
