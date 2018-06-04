// 6.1 Sequences
val sequence = Seq(1, 2, 3)
sequence.apply(0)
sequence(0)

sequence.head
sequence.tail
sequence.tail.tail.head

sequence.headOption
Seq().headOption

sequence.length

sequence.contains(2)

sequence.find(x => x == 3)
sequence.find(_ > 3)
sequence.find(_ < 5)

sequence.filter(_ > 1)
sequence.sortWith(_ > _)

sequence.:+(4)
sequence :+ 4
sequence.+:(0)
0 +: sequence

sequence ++ Seq(4, 5, 6)

// 6.6.1 Lists
Nil
val list = 1 :: 2 :: 3 :: Nil
4 :: 5 :: list
List(1, 2, 3)
List(1, 2, 3) ::: List(4, 5, 6)

// 6.1.7 Importing Collections and Other Libraries
import scala.collection.immutable.Vector
Vector(1, 2, 3)

def test: Vector[Int] = {
  import scala.collection.immutable.Vector.apply
  apply(1, 2, 3, 4)
}
test

import scala.collection.immutable._
val q = Queue(1, 2, 3)
q.enqueue(4)

// 6.1.9 Exercises
sequence.size
list.head
list.headOption
sequence.mkString("[", ", ", "]")
Some(0).isDefined
Some(0).isEmpty
None.isDefined
None.isEmpty

// 6.1.9.2 Animals
val animals = Seq("cat", "dog", "penguin")
"mouse" +: animals :+ "tyrannosaurus"
2 +: animals

// 6.1.9.3 Intranet Movie Database
case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)

case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])

val memento           = new Film("Memento", 2000, 8.5)
val darkKnight        = new Film("Dark Knight", 2008, 9.0)
val inception         = new Film("Inception", 2010, 8.8)

val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven        = new Film("Unforgiven", 1992, 8.3)
val granTorino        = new Film("Gran Torino", 2008, 8.2)
val invictus          = new Film("Invictus", 2009, 7.4)

val predator          = new Film("Predator", 1987, 7.9)
val dieHard           = new Film("Die Hard", 1988, 8.3)
val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)

val eastwood = new Director("Clint", "Eastwood", 1930,
  Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

val mcTiernan = new Director("John", "McTiernan", 1951,
  Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

val nolan = new Director("Christopher", "Nolan", 1970,
  Seq(memento, darkKnight, inception))

val someGuy = new Director("Just", "Some Guy", 1990,
  Seq())

val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

def minFilms(numberOfFilms: Int): Seq[Director] =
  directors.filter(_.films.size > numberOfFilms)

minFilms(3)

def bornBefore(year: Int): Option[Director] =
  directors.find(_.yearOfBirth < year)

def minFilmsBornBefore(numberOfFilms: Int, year: Int): Seq[Director] = {
  val byAge = directors.filter(_.yearOfBirth < year)
  val byFilm = minFilms(numberOfFilms)
  byAge.filter(byFilm.contains)
}

def sorted(ascending: Boolean = true): Seq[Director] = {
  if (ascending) directors.sortWith(_.yearOfBirth < _.yearOfBirth)
  else directors.sortWith(_.yearOfBirth > _.yearOfBirth)
}

sorted(true)
sorted(false)
