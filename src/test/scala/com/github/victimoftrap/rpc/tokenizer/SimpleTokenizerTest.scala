package com.github.victimoftrap.rpc.tokenizer

import org.scalatest.FunSuite

class SimpleTokenizerTest extends FunSuite {
  val tokenizer: Tokenizer = new SimpleTokenizer

  test("SimpleTokenizer.tokens should return tokens") {
    val expected = List("2", "+", "3")
    val actual = tokenizer.tokens("2 + 3")
    assert(expected.equals(actual))
  }

  test("SimpleTokenizer.tokens should return empty list") {
    val expected = List.empty
    val actual = tokenizer.tokens("")
    assert(expected.equals(actual))
  }
}
