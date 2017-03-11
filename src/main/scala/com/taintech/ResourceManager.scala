package com.taintech

/**
  * Author: Rinat Tainov 
  * Date: 02/02/2017
  */
trait ResourceManager {
  type Resource <: BasicResource
  trait BasicResource {
    def hash : String
    def duplicates(r : Resource) : Boolean
  }
  def create : Resource

  // Test methods: exercise is to move them outside ResourceManager
  def testHash()(r : Resource): Unit = assert(r.hash == "9e47088d")
  def testDuplicates()(r : Resource): Unit = assert(r.duplicates(r))
}

trait FileManager extends ResourceManager {
  type Resource <: File
  trait File extends BasicResource {
    def local : Boolean
  }
  override def create : Resource
}

class NetworkFileManager extends FileManager {
  type Resource = RemoteFile
  class RemoteFile extends File {
    def local = false
    def hash = "9e47088d"
    def duplicates(r : Resource): Boolean = (local == r.local) && (hash == r.hash)
  }
  override def create : Resource = new RemoteFile
}
