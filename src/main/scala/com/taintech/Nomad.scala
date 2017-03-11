package com.taintech

/**
  * Author: Rinat Tainov 
  * Date: 02/02/2017
  */
object Nomad {
  def main(args: Array[String]): Unit = {
    println("hello world")
  }

}

trait M[A] {
  def flatMap[B](f: A => M[B]): M[A]
}

//def unit[A](x: A): M[A]

trait User {
  val child: Option[User]
}

object UserService {
  def loadUser(name: String): Option[User] = None
}

object Utilities {
  def getChild: (User) => Option[User] = (user: User) => user.child

  val result: Option[User] = UserService.loadUser("hello").flatMap(getChild)
}