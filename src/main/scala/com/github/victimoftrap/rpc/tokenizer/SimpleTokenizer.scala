package com.github.victimoftrap.rpc.tokenizer

class SimpleTokenizer extends Tokenizer {
  override def tokens(string: String): List[String] = {
    if (string == "") {
      return List.empty
    }

    string
      .split(" ")
      .toList
  }
}
