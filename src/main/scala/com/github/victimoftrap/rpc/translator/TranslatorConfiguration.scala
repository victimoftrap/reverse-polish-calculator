package com.github.victimoftrap.rpc.translator

import com.github.victimoftrap.rpc.operators._

object TranslatorConfiguration {
  val defaultMap = Map(
    "+" -> Addition(6),
    "-" -> Subtraction(6),
    "*" -> Multiplication(7),
    "/" -> Division(7),
    "^" -> Power(8),
    "floor" -> Floor(9),
    "ceil" -> Ceil(9),
    "round" -> Round(9),
    "m" -> UnaryMinus(9),
    "(" -> OpenBracket(),
  )
}
