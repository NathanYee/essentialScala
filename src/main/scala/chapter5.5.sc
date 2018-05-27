// 5.5 Sequencing Computation

sealed trait LinkedList[A] {
  def map[B](fn: A => B): LinkedList[B] =
    this match {
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
      case End() => End[B]()
    }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

// 5.5.4 Exercises

// 5.5.4.1 Mapping Lists
val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))
list.map(_ * 2)
list.map(_ + 1)
list.map(_ / 3)

// 5.5.4.2 Mapping Maybe
sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }

  def map[B](fn: A => B): Maybe[B] =
    this match {
      case Full(v) => Full(fn(v))
      case Empty() => Empty[B]()
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

// 5.5.4.3 Sequencing Computations
val list2 = List(1, 2, 3)
list2.flatMap(x => List(x, -x))

val list3 = List(Full(3), Full(2), Full(1))
list3.map(x => if (x.value % 2 == 0) Full(x.value) else Empty)

// 5.5.4.4 Sum
sealed trait Sum[A, B] {
  def fold[C](left: A => C, right: B => C): C =
    this match {
      case Failure(a) => left(a)
      case Success(b) => right(b)
    }

  def map[C](f: B => C): Sum[A, C] =
    this match {
      case Failure(v) => Failure(v)
      case Success(v) => Success(f(v))
    }

  def flatMap[C](f: B => Sum[A, C]): Sum[A, C] =
    this match {
      case Failure(v) => Failure(v)
      case Success(v) => f(v)
    }
}
final case class Failure[A, B](value: A) extends Sum[A, B]
final case class Success[A, B](value: B) extends Sum[A, B]

