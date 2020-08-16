package com.github.victimoftrap.rpc.tokenizer

import com.github.victimoftrap.rpc.token.{Token, TokenType}

class SimpleTokenizer extends Tokenizer {
  override def tokens(string: String): List[Token] = {
    if (string == "") {
      return List.empty
    }

    string
      .split(" ")
      .map(token => resolveType(token))
      .toList
  }

  private def resolveType(token: String): Token = {
    val numericOption = token.toDoubleOption
    if (numericOption.isDefined) {
      return new Token(TokenType.NUMBER, token)
    }
    if (token == "(") {
      return new Token(TokenType.OPEN_BRACKET, token)
    }
    if (token == ")") {
      return new Token(TokenType.CLOSE_BRACKET, token)
    }
    new Token(TokenType.OPERATOR, token)
  }
}
