// 3.5 Pattern Matching
case class Person(firstName: String, lastName: String)

object Stormtrooper {
  def rebelScum: String = "Stop, rebel scum!"
  def inspect(person: Person): String =
    person match {
      case Person("Luke", "Skywalker") => rebelScum
      case Person("Han", "Solo") => rebelScum
      case Person(first, last) => s"Move along, $first"
    }
}

Stormtrooper.inspect(Person("Noel", "Welsh"))
// res: String = Move along, Noel

Stormtrooper.inspect(Person("Han", "Solo"))
// res: String = Stop, rebel scum!

// 3.5.3
case class Cat(colour: String, food: String)

object ChipShop {
  def willServe(cat: Cat): Boolean = cat match {
    case Cat(_, "chips") => true
    case Cat(_, _) => false
  }
}

