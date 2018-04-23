// 3.1.1
class Cat {
  val name = "Pumpkin"
  val breed = "Orange & White Tabby"
  def cato = name + ", " + breed
}

val happyCat = new Cat
happyCat.cato

val otherHappyCat = new Cat

object alien {
  def greet(c: Cat) =
    "greetings " + c.cato
}

alien.greet(happyCat)

// 3.1.2
class Cato(arg1: String, arg2: String) {
  val name = arg1
  val breed = arg2
  def cato = "My naem is " + name + ". I am " + breed
}

val caty = new Cato("Pumpkin", "Orange & White Tabby")
caty.cato

class Cat2(val name: String, val breed: String) {
  def cato = "My naem is " + name + ". I am " + breed
}

val happyCat2 = new Cat2("Pumpkin", "Orange & White Tabby")

// 3.1.3
new Cat2(name = "Pumpkin", breed = "Tabby")

// 3.1.4 scala type hierarchy
def badness = throw new Exception("error")

null

val bar = if(true) 123 else badness

val baz = if (false) "it worked" else null

// 3.1.5

// 3.1.6
// 3.1.6.1
class CatFinal(val name: String, val colour: String, val food: String)

val oswald = new CatFinal("Oswald", "Black", "Milk")
val henderson = new CatFinal("Genderson", "Ginger", "Chips")

// 3.1.6.2
object MilkShop {
  def willServe(c: CatFinal) = c.food == "Milk"
}
MilkShop.willServe(oswald)
MilkShop.willServe(henderson)

//3.1.6.3
class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def name = firstName + " " + lastName
}

class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
  def directorsAge = yearOfRelease - director.yearOfBirth
  def isDirectedBy(d: Director) = director.name == d.name
}
val eastwood          = new Director("Clint", "Eastwood", 1930)
val mcTiernan         = new Director("John", "McTiernan", 1951)
val nolan             = new Director("Christopher", "Nolan", 1970)
val someBody          = new Director("Just", "Some Body", 1990)

val memento           = new Film("Memento", 2000, 8.5, nolan)
val darkKnight        = new Film("Dark Knight", 2008, 9.0, nolan)
val inception         = new Film("Inception", 2010, 8.8, nolan)

val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
val unforgiven        = new Film("Unforgiven", 1992, 8.3, eastwood)
val granTorino        = new Film("Gran Torino", 2008, 8.2, eastwood)
val invictus          = new Film("Invictus", 2009, 7.4, eastwood)

val predator          = new Film("Predator", 1987, 7.9, mcTiernan)
val dieHard           = new Film("Die Hard", 1988, 8.3, mcTiernan)
val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

eastwood.yearOfBirth         // should be 1930
dieHard.director.name        // should be "John McTiernan"
invictus.isDirectedBy(nolan) // should be false