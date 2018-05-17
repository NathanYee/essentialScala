// 3.4 Case Classes

case class Person(firstName: String, lastName: String) {
  def name = s"$firstName $lastName"
}

val dave = new Person("Dave", "Gurnell")
dave.name
dave.firstName
dave

new Person("Happy", "Cat").equals(new Person("Happy", "Cat"))

dave eq dave.copy()
dave.equals(dave.copy())

dave.copy(lastName = "llnerug")


// case classes also have a companion object that contains an apply method
// == defaults to equals
Person("Happy", "Cat") == Person("Happy", "Cat")

// 3.4.3 Case Objects
// no constructor arguments
// same default methods as case class
 case object Cato {
  def animalType = "cat"
  def animalName = "Pumpkin"
  def animalMood = "happy"
  def name = s"$animalName, the $animalMood $animalType"
}

Cato.name

// 3.4.5 Exercises

// 3.4.5.1
case class Cat(colour: String, food: String)

// 3.4.5.2
//def isDirectedBy(d: Director) = director.name == d.name
//class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
//  def directorsAge = yearOfRelease - director.yearOfBirth

case class Director(firstName: String, lastName: String, yearOfBirth: Int) {
  def name: String = s"$firstName $lastName"
}

object Director {
  def older(d1: Director, d2: Director): Director = if (d1.yearOfBirth < d2.yearOfBirth) d1 else d2
}

case class Film(name: String, yearOfRelease: Int, imdbRating: Double, director: Director) {
  def directorsAge = yearOfRelease - director.yearOfBirth
  def isDirectedBy(director: Director) = this.director == director
}

object Film {
  def highestRating(f1: Film, f2: Film): Film = if (f1.imdbRating > f2.imdbRating) f1 else f2
  def oldestDirectorAtTheTime(f1: Film, f2: Film): Director =
    if (f1.directorsAge > f2.directorsAge) f1.director else f2.director
}

// 3.4.5.3 Case Class Counter
case class Counter(count: Int = 0) {
  def inc: Counter = copy(count = count + 1)
  def dec: Counter = copy(count = count - 1)
}

Counter(0) // construct objects without `new`
// res: Counter = Counter(0)

Counter().inc // printout shows the value of `count`
// res: Counter = Counter(1)

Counter().inc.dec == Counter().dec.inc // semantic equality check
// res: Boolean = true

// 3.4.5.4 Person
case class Person2(firstName: String, lastName: String) {
  def name = s"$firstName $lastName"
}

object Person2 {
  def apply(name: String): Person2 = {
    val parts = name.split(" ")
    apply(parts(0), parts(1))
  }
}