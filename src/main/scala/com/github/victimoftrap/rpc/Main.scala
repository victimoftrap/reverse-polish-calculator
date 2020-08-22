package com.github.victimoftrap.rpc

import com.github.victimoftrap.rpc.calculator.RpnBasedCalculator
import com.github.victimoftrap.rpc.calculator.converter.DefaultTokenConverter
import com.github.victimoftrap.rpc.tokenizer.AdvancedTokenizer
import com.github.victimoftrap.rpc.translator.{SimpleNotationTranslator, TranslatorConfiguration}

object Main {
  def main(args: Array[String]): Unit = {
    val operatorMap = TranslatorConfiguration.defaultMap

    val tokenizer = new AdvancedTokenizer
    val translator = new SimpleNotationTranslator(operatorMap)
    val converter = new DefaultTokenConverter(operatorMap)
    val calculator = new RpnBasedCalculator(converter)

    val tokens = tokenizer.tokens("1+5*(2+5.3)")
    val postfixTokens = translator.toPostfixNotation(tokens)
    println(calculator.calculate(postfixTokens))
  }
}
