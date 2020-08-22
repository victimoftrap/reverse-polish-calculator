package com.github.victimoftrap.rpc.tokenizer

import com.github.victimoftrap.rpc.token.{Token, TokenType}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AdvancedTokenizerTest extends AnyFlatSpec with Matchers {
  val tokenizer = new AdvancedTokenizer

  "An AdvancedTokenizer" should "return empty token list if empty string passed" in {
    tokenizer.tokens("") should be(List.empty)
  }

  it should "return list with single numeric token" in {
    val expectedTokens = List(Token(TokenType.NUMBER, "42"))
    val actualTokens = tokenizer.tokens("42")

    actualTokens.length should be(expectedTokens.length)
    actualTokens should be(expectedTokens)
  }

  it should "return token list with numbers and simple operators" in {
    val inputString = "2+3*4"
    val expectedTokens = List(
      Token(TokenType.NUMBER, "2"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.OPERATOR, "*"),
      Token(TokenType.NUMBER, "4"),
    )

    val actualTokens = tokenizer.tokens(inputString)
    actualTokens should be(expectedTokens)
  }

  it should "return token list with numbers, operators and brackets" in {
    val inputString = "float(4.2*3)"
    val expectedTokens = List(
      Token(TokenType.OPERATOR, "float"),
      Token(TokenType.OPEN_BRACKET, "("),
      Token(TokenType.NUMBER, "4.2"),
      Token(TokenType.OPERATOR, "*"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.CLOSE_BRACKET, ")"),
    )

    val actualTokens = tokenizer.tokens(inputString)
    actualTokens should be(expectedTokens)
  }
}
