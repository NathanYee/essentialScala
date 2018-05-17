// 3.3
// companion objects

class Timestamp(val seconds: Long)

// auxiliary constructor
object Timestamp {
  def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
    new Timestamp(hours*60*60 + minutes*60 + seconds)
}

Timestamp(1,1,1).seconds

// 3.2.2 Friendly Person Factory
class Person(val firstName: String, val lastName: String) {
  def name = firstName + " " + lastName
}

object Person {
  def apply(name: String): Person = {
    val parts = name.split(" ")
    new Person(parts(0), parts(1))
  }
}

Person("N Y").firstName

// 3.3.2.2 Extended Body of Work

class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def name = s"$firstName $lastName"
}

object Director {
  def apply(firstName: String, lastName: String, yearOfBirth: Int): Director =
    new Director(firstName, lastName, yearOfBirth)

  def older(d1: Director, d2: Director): Director = {
    if (d1.yearOfBirth < d2.yearOfBirth) d1 else d2
  }
}

val eastwood  = Director("Clint", "Eastwood", 1930)
val mcTiernan = Director("John", "McTiernan", 1951)
Director.older(eastwood, mcTiernan).name


class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
  def directorsAge = yearOfRelease - director.yearOfBirth
  def isDirectedBy(d: Director) = director.name == d.name
  def copy(name: String = this.name,
           yearOfRelease: Int = this.yearOfRelease,
           imdbRating: Double = this.imdbRating,
           director: Director = this.director): Film = new Film(name, yearOfRelease, imdbRating, director)
}

object Film {
  def apply(name: String,
            yearOfRelease: Int,
            imdbRating: Double,
            director: Director): Film =
    new Film(name, yearOfRelease, imdbRating, director)

  def highestRating(f1: Film, f2: Film): Film = if (f1.imdbRating > f2.imdbRating) f1 else f2

  def oldestDirectorAtTheTime(f1: Film, f2: Film): Director =
    if (f1.directorsAge > f2.directorsAge) f1.director else f2.director


}