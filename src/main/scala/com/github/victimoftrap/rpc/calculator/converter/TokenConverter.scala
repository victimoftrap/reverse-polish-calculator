package com.github.victimoftrap.rpc.calculator.converter

import com.github.victimoftrap.rpc.calculator.converter.nodes.RpnNode
import com.github.victimoftrap.rpc.token.Token

trait TokenConverter {
  def toComputableNodes(postfix: List[Token]): List[RpnNode]
}
