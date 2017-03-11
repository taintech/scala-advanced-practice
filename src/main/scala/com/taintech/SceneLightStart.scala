package com.taintech

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorLogging, DeadLetter, Props}

/**
  * Author: Rinat Tainov 
  * Date: 02/02/2017
  */
object SceneLightStart {
  def main(args: Array[String]): Unit = {
    println("hello world")
    akka.Main.main(Array(classOf[Master].getName))
  }
}

class Master extends Actor {
  override def preStart(): Unit = {
    val slave = context.actorOf(Props[Slave], "slave")
    slave ! Work
    val noone = context.actorOf(Props[DeadLetterReader], "noone")
    noone ! Unknown
  }
  def receive = {
    case Done => context.stop(self)
  }
}

class Slave extends Actor with ActorLogging {
  override def receive = {
    case Work =>
      log.info("I hate my Master!")
      sender() ! Done
  }
}

case object Work

case object Done

case object Unknown

class DeadLetterReader extends Actor with ActorLogging{
  override def preStart(): Unit = context.system.eventStream.subscribe(self, classOf[DeadLetter])
  override def receive: Receive = {
    case Unknown => log.info("Received unknown message")
  }
}