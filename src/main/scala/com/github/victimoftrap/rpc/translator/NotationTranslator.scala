package com.github.victimoftrap.rpc.translator

import com.github.victimoftrap.rpc.token.Token

trait NotationTranslator {
  def toPostfixNotation(tokens: List[Token]): List[Token]
}
