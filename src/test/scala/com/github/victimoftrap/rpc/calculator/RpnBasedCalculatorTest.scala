package com.github.victimoftrap.rpc.calculator

import com.github.victimoftrap.rpc.calculator.converter.TokenConverter
import com.github.victimoftrap.rpc.calculator.converter.nodes.{NumericRpnNode, OperatorRpnNode}
import com.github.victimoftrap.rpc.operators.{Addition, Floor, Multiplication}
import com.github.victimoftrap.rpc.token.{Token, TokenType}

import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RpnBasedCalculatorTest extends AnyFlatSpec with MockFactory with Matchers {
  val converter: TokenConverter = mock[TokenConverter]
  val calculator = new RpnBasedCalculator(converter)

  "A Calculator" should "compute expression in postfix notation" in {
    val postfixTokens = List(
      Token(TokenType.NUMBER, "2"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.NUMBER, "4"),
      Token(TokenType.OPERATOR, "*"),
      Token(TokenType.OPERATOR, "+"),
    )
    val postfixRpnNodes = List(
      new NumericRpnNode(2),
      new NumericRpnNode(3),
      new NumericRpnNode(4),
      new OperatorRpnNode(Multiplication(2)),
      new OperatorRpnNode(Addition(1)),
    )

    (converter.toComputableNodes _).expects(postfixTokens).returning(postfixRpnNodes)

    calculator.calculate(postfixTokens) should be(14)
  }

  "A Calculator" should "compute expression with brackets in postfix notation" in {
    val postfixTokens = List(
      Token(TokenType.NUMBER, "2"),
      Token(TokenType.NUMBER, "3"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.NUMBER, "4"),
      Token(TokenType.OPERATOR, "*"),
    )
    val postfixRpnNodes = List(
      new NumericRpnNode(2),
      new NumericRpnNode(3),
      new OperatorRpnNode(Addition(1)),
      new NumericRpnNode(4),
      new OperatorRpnNode(Multiplication(2)),
    )

    (converter.toComputableNodes _).expects(postfixTokens).returning(postfixRpnNodes)

    calculator.calculate(postfixTokens) should be(20)
  }

  "A Calculator" should "compute expression with floor in postfix notation" in {
    val postfixTokens = List(
      Token(TokenType.NUMBER, "3.2"),
      Token(TokenType.NUMBER, "1"),
      Token(TokenType.OPERATOR, "+"),
      Token(TokenType.OPERATOR, "floor"),
    )
    val postfixRpnNodes = List(
      new NumericRpnNode(3.2),
      new NumericRpnNode(1),
      new OperatorRpnNode(Addition(1)),
      new OperatorRpnNode(Floor(2)),
    )

    (converter.toComputableNodes _).expects(postfixTokens).returning(postfixRpnNodes)

    calculator.calculate(postfixTokens) should be(4)
  }
}
