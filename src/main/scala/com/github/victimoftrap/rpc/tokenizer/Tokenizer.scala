package com.github.victimoftrap.rpc.tokenizer

trait Tokenizer {
  def tokens(string: String): List[String]
}
