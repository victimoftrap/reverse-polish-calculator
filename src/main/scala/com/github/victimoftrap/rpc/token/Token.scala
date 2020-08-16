package com.github.victimoftrap.rpc.token

import com.github.victimoftrap.rpc.token.TokenType.TokenType

class Token(val tokenType: TokenType, val value: String) {
  override def toString: String = s"Token[$tokenType, $value]"
}
