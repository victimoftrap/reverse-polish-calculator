package com.github.victimoftrap.rpc.calculator

import com.github.victimoftrap.rpc.token.Token

trait Calculator {
  def calculate(tokens: List[Token]): Double
}
