package com.github.victimoftrap.rpc.calculator.converter

import com.github.victimoftrap.rpc.calculator.converter.nodes.{NumericRpnNode, OperatorRpnNode, RpnNode}
import com.github.victimoftrap.rpc.operators.Operator
import com.github.victimoftrap.rpc.token.{Token, TokenType}

class DefaultTokenConverter(operatorMap: Map[String, Operator]) extends TokenConverter {
  def toComputableNodes(postfix: List[Token]): List[RpnNode] = {
    postfix
      .map(token => {
        token.tokenType match {
          case TokenType.NUMBER => new NumericRpnNode(token.value.toDouble)
          case TokenType.OPERATOR => new OperatorRpnNode(operatorMap(token.value))
        }
      })
  }
}
