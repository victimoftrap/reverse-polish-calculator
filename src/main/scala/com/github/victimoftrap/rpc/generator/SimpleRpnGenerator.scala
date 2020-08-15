package com.github.victimoftrap.rpc.generator

import com.github.victimoftrap.rpc.generator.elements.{NumericRpnElement, OperatorRpnElement, RpnElement}
import com.github.victimoftrap.rpc.operators.{Associativity, Operator}

import scala.collection.mutable.ListBuffer

class SimpleRpnGenerator(operatorMap: Map[String, Operator]) extends RpnGenerator {
  override def generate(tokens: List[String]): List[RpnElement] = {
    val resultRpn = ListBuffer[RpnElement]()
    var operatorStack = List[OperatorRpnElement]()

    for (token <- tokens) {
      val numericOption = token.toDoubleOption
      if (numericOption.isDefined) {
        resultRpn.addOne(new NumericRpnElement(numericOption.get))
      }

      val operatorOption = operatorMap.get(token)
      if (operatorOption.isDefined) {
        val elem = new OperatorRpnElement(operatorOption.get)

        if (operatorOption.get.arity() == 1) {
          operatorStack = elem :: operatorStack
        }
        if (operatorOption.get.arity() == 2) {
          val (removedOperators, updatedStack) = removeOperatorsFromStack(operatorStack, elem)
          resultRpn.addAll(removedOperators)
          operatorStack = elem :: updatedStack
        }
      }
    }

    if (operatorStack.nonEmpty) {
      resultRpn.addAll(operatorStack)
    }
    resultRpn.toList
  }

  private def removeOperatorsFromStack(operatorStack: List[OperatorRpnElement],
                                       elem: OperatorRpnElement
                                      ): (List[OperatorRpnElement], List[OperatorRpnElement]) = {
    if (operatorStack.isEmpty) {
      return (List.empty, operatorStack)
    }

    var cleanedStack = operatorStack
    val removedOperators = ListBuffer[OperatorRpnElement]()

    while (cleanedStack.nonEmpty) {
      val stackTop = cleanedStack.head

      if (highPrecedence(stackTop, elem) || samePrecedence(stackTop, elem)) {
        removedOperators.addOne(stackTop)
        cleanedStack = cleanedStack.splitAt(1)._2
      } else {
        return (removedOperators.toList, cleanedStack)
      }
    }
    (removedOperators.toList, cleanedStack)
  }

  private def highPrecedence(stackTop: OperatorRpnElement, elem: OperatorRpnElement): Boolean =
    stackTop.operator.priority > elem.operator.priority

  private def samePrecedence(stackTop: OperatorRpnElement, elem: OperatorRpnElement): Boolean =
    stackTop.operator.associativity() == Associativity.LEFT &&
      stackTop.operator.priority == elem.operator.priority
}
