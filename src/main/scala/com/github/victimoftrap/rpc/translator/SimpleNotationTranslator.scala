package com.github.victimoftrap.rpc.translator

import com.github.victimoftrap.rpc.operators.{Associativity, Operator}
import com.github.victimoftrap.rpc.token.{Token, TokenType}

import scala.collection.mutable.ListBuffer

class SimpleNotationTranslator(val operatorMap: Map[String, Operator]) extends NotationTranslator {

  override def toPostfixNotation(tokens: List[Token]): List[Token] = {
    val outputRpn = ListBuffer[Token]()
    val operatorStack = ListBuffer[(Token, Operator)]()
    var prevToken: Token = null

    tokens.foreach(token => {
      token.tokenType match {
        case TokenType.NUMBER => outputRpn.addOne(token)

        case TokenType.OPEN_BRACKET => operatorStack.addOne(Tuple2(token, operatorMap(token.value)))

        case TokenType.CLOSE_BRACKET =>
          val removedOps = operatorStack
            .reverse
            .map(x => x._1)
            .takeWhile(_.tokenType != TokenType.OPEN_BRACKET)

          outputRpn.addAll(removedOps.reverse)
          if (removedOps.nonEmpty) {
            operatorStack.remove(operatorStack.length - removedOps.length - 1, removedOps.length + 1)
          }

        case TokenType.OPERATOR =>
          if (token.value == "-" && prevToken == null ||
            prevToken != null && prevToken.tokenType == TokenType.OPEN_BRACKET) {
            token.value = "m"
          }

          val operatorOption = operatorMap.get(token.value)
          if (operatorOption.isDefined) {
            val operator = operatorOption.get

            if (operator.arity() == 1) {
              operatorStack.addOne(Tuple2(token, operator))
            }
            if (operator.arity() == 2) {
              val removedOps = operatorStack
                .reverse
                .takeWhile(op => highPrecedence(op._2, operator) || samePrecedence(op._2, operator))
                .map(x => x._1)

              outputRpn.addAll(removedOps.reverse)
              if (operatorStack.nonEmpty && removedOps.nonEmpty) {
                operatorStack.remove(operatorStack.length - removedOps.length, removedOps.length)
              }
              operatorStack.addOne(Tuple2(token, operator))
            }
          }
      }
      prevToken = token
    })

    if (operatorStack.nonEmpty) {
      outputRpn.addAll(operatorStack.reverse.map(_._1))
    }
    outputRpn.toList
  }

  private def highPrecedence(stackTop: Operator, elem: Operator): Boolean =
    stackTop.priority > elem.priority

  private def samePrecedence(stackTop: Operator, elem: Operator): Boolean =
    stackTop.associativity() == Associativity.LEFT && stackTop.priority == elem.priority
}
