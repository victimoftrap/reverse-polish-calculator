package com.github.victimoftrap.rpc.tokenizer

import com.github.victimoftrap.rpc.token.TokenType.TokenType
import com.github.victimoftrap.rpc.token.{Token, TokenType}

import scala.collection.mutable.ListBuffer

class AdvancedTokenizer extends Tokenizer {
  private def releaseTokenIfReady(actual: TokenType,
                                  expected: TokenType,
                                  tokenBuilder: StringBuilder,
                                  tokens: ListBuffer[Token]): Unit = {
    if (actual != expected && tokenBuilder.nonEmpty) {
      tokens.addOne(new Token(actual, tokenBuilder.toString()))
      tokenBuilder.clear()
    }
  }

  override def tokens(string: String): List[Token] = {
    val tokens = new ListBuffer[Token]
    val tokenBuilder = new StringBuilder()
    var tokenType: TokenType = null

    string.foreach {
      case char if char.isWhitespace =>
        if (tokenBuilder.nonEmpty) {
          tokens.addOne(new Token(tokenType, tokenBuilder.toString))
          tokenBuilder.clear()
        }

      case char@open if char == '(' =>
        releaseTokenIfReady(tokenType, TokenType.OPEN_BRACKET, tokenBuilder, tokens)

        tokenType = TokenType.OPEN_BRACKET
        tokens.addOne(new Token(tokenType, open.toString))
        tokenBuilder.clear()

      case char@close if char == ')' =>
        releaseTokenIfReady(tokenType, TokenType.CLOSE_BRACKET, tokenBuilder, tokens)

        tokenType = TokenType.CLOSE_BRACKET
        tokens.addOne(new Token(tokenType, close.toString))
        tokenBuilder.clear()

      case char@digitChar if char.isDigit || char == '.' =>
        releaseTokenIfReady(tokenType, TokenType.NUMBER, tokenBuilder, tokens)

        tokenType = TokenType.NUMBER
        tokenBuilder.addOne(digitChar)

      case char@letter if char.isLetter =>
        releaseTokenIfReady(tokenType, TokenType.OPERATOR, tokenBuilder, tokens)

        tokenType = TokenType.OPERATOR
        tokenBuilder.addOne(letter)

      case symbolOperator =>
        releaseTokenIfReady(tokenType, TokenType.OPEN_BRACKET, tokenBuilder, tokens)

        tokenType = TokenType.OPERATOR
        tokens.addOne(new Token(tokenType, symbolOperator.toString))
        tokenBuilder.clear()
    }

    if (tokenBuilder.nonEmpty) {
      tokens.addOne(new Token(tokenType, tokenBuilder.toString()))
    }
    tokens.toList
  }
}
