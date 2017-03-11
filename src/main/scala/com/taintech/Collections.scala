package com.taintech

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Author: Rinat Tainov 
  * Date: 02/02/2017
  */
object Collections {
  def main(args: Array[String]): Unit = {
    println("Collect")

    val is1: mutable.IndexedSeq[String] = ArrayBuffer.empty[String]
    val is2: Traversable[String] = List.empty[String]
  }
}
