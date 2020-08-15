package com.github.victimoftrap.rpc.operators

import com.github.victimoftrap.rpc.operators.Associativity.Associativity

abstract class Operator {
  val priority: Int

  def arity(): Int

  def associativity(): Associativity

  def execute(opArgs: List[Double]): Double
}
