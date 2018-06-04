// 6.2 Working with Sequences

// map
val sequence = Seq(1, 2, 3)
sequence.map(_ * 2)

"dog".permutations.toList

Seq("a", "hat", "cat").map(_.permutations.toList)
Seq("a", "hat", "cat").flatMap(_.permutations.toList)

import scala.collection.immutable.{Seq, Vector}
Vector(1, 2, 3).flatMap(num => Seq(num, num * 10))

// fold
sequence.foldLeft(0)(_ + _)
sequence.foldRight(0)(_ + _)
sequence.foreach(num => println(s" and a $num..."))

// Exercises
case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)

case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film]) {
  val name: String = s"$firstName $lastName"
}

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

nolan.films.map(_.name)
directors.flatMap(_.films.map(_.name))

mcTiernan.films.foldLeft(Int.MaxValue)((current, film) => math.min(current, film.yearOfRelease))

directors.flatMap(director => director.films).sortWith((a, b) => a.imdbRating > b.imdbRating)

val filmRatings = directors.flatMap(_.films).map(_.imdbRating)
filmRatings.foldLeft(0.0)(_ + _) / filmRatings.length

directors.foreach(d => d.films.foreach(f => println(s"Tonight only! ${f.name} by ${d.name}")))

directors.flatMap(d => d.films.sortWith(_.yearOfRelease < _.yearOfRelease).headOption)

// 6.2.7.2 Do-It-Yourself
def minimum(seq: Seq[Int]): Int = seq.foldLeft(Int.MaxValue)(math.min)
minimum(Seq(1,2,3,4,0))

def insert(seq: Seq[Int], elt: Int): Seq[Int] = {
  if (seq.contains(elt)) seq else elt +: seq
}

def unique(seq: Seq[Int]): Seq[Int] = seq.foldLeft(Seq.empty[Int])(insert)

def reverse[A](seq: Seq[A]): Seq[A] = {
  seq.foldLeft(Seq.empty[A])( (seq, elt) => elt +: seq)
}

def map[A, B](seq: Seq[A])(f: A => B): Seq[B] = {
  seq.foldRight(Seq.empty[B])( (elt, seq) => f(elt) +: seq )
}

def foldLeft[A, B](seq: Seq[A], acc: B, f: (B, A) => B): B = {
  var res = acc
  seq.foreach { elt => res = f(res, elt)}
  res
}