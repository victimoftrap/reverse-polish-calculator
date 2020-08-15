package com.github.victimoftrap.rpc

import com.github.victimoftrap.rpc.calculator.SimpleCalculator
import com.github.victimoftrap.rpc.generator.SimpleRpnGenerator
import com.github.victimoftrap.rpc.operators._
import com.github.victimoftrap.rpc.tokenizer.SimpleTokenizer

object Main {
  def main(args: Array[String]): Unit = {
    val operatorMap = Map(
      "+" -> Addition(6),
      "-" -> Subtraction(6),
      "*" -> Multiplication(7),
      "/" -> Division(7),
      "^" -> Power(8),
      "floor" -> Floor(9),
      "ceil" -> Ceil(9),
      "round" -> Round(9)
    )

    val tokenizer = new SimpleTokenizer
    val generator = new SimpleRpnGenerator(operatorMap)
    val calc = new SimpleCalculator(tokenizer, generator)

    val result = calc.calculate("2 ^ 3 + 0.3")
    println(result)
  }
}
