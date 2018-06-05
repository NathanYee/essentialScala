// 6.8 Maps and Sets

val example = Map("a" -> 1, "b" -> 2, "c" -> 3)
example("a")
example.get("a")
example.getOrElse("d", -1)
example.contains("a")
example.size

example + ("c" -> 10, "d" -> 11, "e" -> 12)
example - ("b", "c")

val example2 = scala.collection.mutable.Map("x" -> 10, "y" -> 11, "z" -> 12)
example2 += ("x" -> 20)
example2 -= ("y", "z")
example2.update("w", 30)
example2

val example3 = scala.collection.immutable.ListMap("a" -> 1) + ("b" -> 2) + ("c" -> 3) + ("d" -> 4) + ("e" -> 5)
example3.keys.toList

example.map(pair => pair._1 -> pair._2 * 2)

// 6.8.2 Sets
val people = Set(
  "Alice",
  "Bob",
  "Charlie",
  "Derek",
  "Edith",
  "Fred")

val ages = Map(
  "Alice"   -> 20,
  "Bob"     -> 30,
  "Charlie" -> 50,
  "Derek"   -> 40,
  "Edith"   -> 10,
  "Fred"    -> 60)

val favoriteColors = Map(
  "Bob"     -> "green",
  "Derek"   -> "magenta",
  "Fred"    -> "yellow")

val favoriteLolcats = Map(
  "Alice"   -> "Long Cat",
  "Charlie" -> "Ceiling Cat",
  "Edith"   -> "Cloud Cat")

def favoriteColour(name: String): String = {
  favoriteColors.getOrElse(name, "beige")
}

def printColors(): Unit = {
  people.foreach(person => println(favoriteColour(person)))
}
printColors

def lookup[A](name: String, values: Map[String, A])= {
  values get name
}

val oldest: Option[String] =
  people.foldLeft(Option.empty[String]) { (older, person) =>
    if (ages.getOrElse(person, 0) > older.flatMap(ages.get).getOrElse(0)) {
      Some(person)
    } else {
      older
    }
  }

val favorite: Option[String] =
  for {
    oldest <- oldest
    color <- favoriteColors.get(oldest)
  } yield color

// 6.8.4 Do-It-Yourself Part 2

def unionSets[A](set1: Set[A], set2: Set[A]): Set[A] = {
  set1.foldLeft(set2){ (set, elt) => (set + elt) }
}

def unionMaps[A](map1: Map[A, Int], map2: Map[A, Int]): Map[A, Int] = {
  map1.foldLeft(map2){ (map, elt) =>
    val (k, v) = elt
    val newV: Int = map.getOrElse(k, 0) + v
    map + (k -> newV)
  }
}

val map1 = Map('a' -> 1, 'b' -> 2)
val map2 = Map('a' -> 2, 'b' -> 4)
unionMaps(map1, map2)

def union[A, B](map1: Map[A, B], map2: Map[A, B], add: (B, B) => B): Map[A, B] = {
  map1.foldLeft(map2){ (map, elt) =>
    val (k, v) = elt
    val newV = map.get(k).map(v2 => add(v, v2)).getOrElse(v)
    map + (k -> newV)
  }
}