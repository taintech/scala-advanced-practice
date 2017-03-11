package com.taintech

/**
  * Author: Rinat Tainov 
  * Date: 03/03/2017
  */
object Operator {

  var x = 0

  def apply(x: Int): Int = x + 1
  def update(y: Int, z: Int) = x = y + z
  def hello_:(s: String): String = "hello " + s
  val rightOp: List[Int] = 1 :: 2 :: 3 :: Nil
  val rightOpExt: List[Int] = Nil.::(3).::(2).::(1)

  def main(args: Array[String]): Unit = {
    Console println Operator(41)
    Console println {Operator(1) = 3; x}
    Console println ("man" hello_: Operator)
    Console println rightOp.equals(rightOpExt)
  }
}
