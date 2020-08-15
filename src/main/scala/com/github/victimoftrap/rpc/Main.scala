package com.github.victimoftrap.rpc

import com.github.victimoftrap.rpc.calculator.SimpleCalculator
import com.github.victimoftrap.rpc.generator.SimpleRpnGenerator
import com.github.victimoftrap.rpc.operators.{Addition, Division, Multiplication, Subtraction}
import com.github.victimoftrap.rpc.tokenizer.SimpleTokenizer

object Main {
  def main(args: Array[String]): Unit = {
    val operatorMap = Map(
      "+" -> new Addition(1),
      "-" -> new Subtraction(1),
      "*" -> new Multiplication(2),
      "/" -> new Division(2)
    )

    val tokenizer = new SimpleTokenizer
    val generator = new SimpleRpnGenerator(operatorMap)
    val calc = new SimpleCalculator(tokenizer, generator)

    val result = calc.calculate("3 * 4 / 3 + 1 * 2 - 1")
    println(result)
  }
}
