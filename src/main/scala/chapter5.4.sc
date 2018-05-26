//5.4 Modelling Data with Generic Types

// 5.4.1
case class Pair[A, B](one: A, two: B)
val pair = Pair[String, Int]("hi", 2)
val pair2 = Pair("hi", 2)

def tuplized[A, B](in: (A, B)) = in._1
tuplized(("a", 1))

(1, 1) match {
  case (a, b) => a + b
}

//5.4.3 Generic Sum Types
def intOrString(input: Boolean): Sum[Int, String] =
if(input == true) {
  Left[Int, String](123)
} else {
  Right[Int, String]("abc")
}

sealed trait Sum[A, B] {
  def fold[C](left: A => C, right: B => C): C =
    this match {
      case Left(a) => left(a)
      case Right(b) => right(b)
    }
}
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]

// 5.4.4 Generic Optional Values
sealed trait Maybe[A] {
  def fold[B](full: A => B, empty: B): B =
    this match {
      case Full(v) => full(v)
      case Empty() => empty
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

val perhaps: Maybe[Int] = Empty[Int]
// perhaps: Maybe[Int] = Empty()

val perhaps2: Maybe[Int] = Full(1)
// perhaps: Maybe[Int] = Full(1)

// 5.4.6 Exercises
