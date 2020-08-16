package com.github.victimoftrap.rpc.tokenizer

import com.github.victimoftrap.rpc.token.Token

trait Tokenizer {
  def tokens(string: String): List[Token]
}
