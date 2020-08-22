package com.github.victimoftrap.rpc.translator

import com.github.victimoftrap.rpc.token.{Token, TokenType}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SimpleNotationTranslatorTest extends AnyFlatSpec with Matchers {
  val translator = new SimpleNotationTranslator(TranslatorConfiguration.defaultMap)

  "A Translator" should "translate input token list to postfix notation" in {
    val inputTokens = List[Token](
      Token(TokenType.NUMBER, "2"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.OPERATOR, "*"),
      Token(TokenType.NUMBER, "4"),
    )

    val expectedPostfixNotation = List[Token](
      Token(TokenType.NUMBER, "2"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.NUMBER, "4"),
      Token(TokenType.OPERATOR, "*"),
      Token(TokenType.OPERATOR, "+"),
    )

    translator.toPostfixNotation(inputTokens) should be(expectedPostfixNotation)
  }

  "A Translator" should "translate input tokens with brackets to postfix notation" in {
    val inputTokens = List[Token](
      Token(TokenType.OPERATOR, "-"),
      Token(TokenType.NUMBER, "6"),
      Token(TokenType.OPERATOR, "*"),
      Token(TokenType.OPEN_BRACKET, "("),
      Token(TokenType.NUMBER, "4"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.NUMBER, "5"),
      Token(TokenType.CLOSE_BRACKET, ")"),
      Token(TokenType.OPERATOR, "-"),
      Token(TokenType.NUMBER, "7"),
    )

    val expectedPostfixNotation = List[Token](
      Token(TokenType.NUMBER, "6"),
      Token(TokenType.OPERATOR, "m"),
      Token(TokenType.NUMBER, "4"),
      Token(TokenType.NUMBER, "5"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.OPERATOR, "*"),
      Token(TokenType.NUMBER, "7"),
      Token(TokenType.OPERATOR, "-"),
    )

    translator.toPostfixNotation(inputTokens) should be(expectedPostfixNotation)
  }

  it should "throw exception if bracket not closed" in {
    val inputTokens = List[Token](
      Token(TokenType.OPEN_BRACKET, "("),
      Token(TokenType.NUMBER, "2"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.NUMBER, "*"),
      Token(TokenType.NUMBER, "4"),
    )

    an[IncompleteBracketsException] should be thrownBy {
      translator.toPostfixNotation(inputTokens)
    }
  }

  it should "throw exception if input has only close bracket" in {
    val inputTokens = List[Token](
      Token(TokenType.NUMBER, "2"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.CLOSE_BRACKET, ")"),
      Token(TokenType.NUMBER, "*"),
      Token(TokenType.NUMBER, "4"),
    )

    an[IncompleteBracketsException] should be thrownBy {
      translator.toPostfixNotation(inputTokens)
    }
  }
}
