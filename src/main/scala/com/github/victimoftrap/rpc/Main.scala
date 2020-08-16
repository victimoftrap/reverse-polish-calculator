package com.github.victimoftrap.rpc

import com.github.victimoftrap.rpc.calculator.RpnBasedCalculator
import com.github.victimoftrap.rpc.calculator.converter.DefaultTokenConverter
import com.github.victimoftrap.rpc.operators._
import com.github.victimoftrap.rpc.tokenizer.SimpleTokenizer
import com.github.victimoftrap.rpc.translator.SimpleNotationTranslator

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
      "round" -> Round(9),
      "(" -> OpenBracket(),
    )

    val tokenizer = new SimpleTokenizer
    val translator = new SimpleNotationTranslator(operatorMap)
    val converter = new DefaultTokenConverter(operatorMap)
    val calculator = new RpnBasedCalculator(converter)

    val postfixTokens = translator.toPostfixNotation(tokenizer.tokens("2 * ( 1 + 2 ) - 3 + 2 * 1"))
    println(calculator.calculate(postfixTokens))
  }
}
