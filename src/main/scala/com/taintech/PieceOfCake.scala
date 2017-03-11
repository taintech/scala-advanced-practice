package com.taintech

/**
  * Author: Rinat Tainov 
  * Date: 02/02/2017
  */
object PieceOfCake {
  def main(args: Array[String]): Unit ={
    Console println "starting"

    val myCalc = new MyCalc with HelloProvider()
    println(myCalc.calculate())
     }
}

trait DataProvider {
  def importantData(): String
}

trait CalculationEngine {
  dp: DataProvider =>

  def calculate(): String = veryInterestingCalculation(dp.importantData())

  protected def veryInterestingCalculation(s: String): String
}

trait MyCalc extends CalculationEngine {
  self: DataProvider =>
  override def veryInterestingCalculation(s: String): String = s.toUpperCase
}

trait HelloProvider extends DataProvider {
  override def importantData() = "hello world"
}
