package com.github.victimoftrap.rpc.token

object TokenType extends Enumeration {
  type TokenType = Value
  val NUMBER, BOOLEAN, OPERATOR, OPEN_BRACKET, CLOSE_BRACKET = Value
}
