val list: List[String] = ("Hello" :: Nil) ::: (List("World") ::: "!" :: Nil)
list(2)
list.count(s => s.length == 1)
list.drop(1)
list.dropRight(1)
list.exists(_.length == 1)
list.filter(_.length == 1)
list.forall(_.length == 1)
list.foreach(print)
list.head
list.init
list.isEmpty
list.last
list.length
list.map(_.length)
list.mkString(" ")
list.filterNot(_.contains("!"))
list.reverse
list.sortBy(_.length)
list.tail
list.inits.toList
list.tails.toList
list.permutations.toList
list.combinations(2).toList
list.equals(List("1"))
list.stringPrefix
