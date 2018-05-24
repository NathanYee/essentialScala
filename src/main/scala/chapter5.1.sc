// 5.1
// TODO: Make this file work with example assertions

sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

sealed trait LinkedList[A] {
  def apply(index: Int): Result[A] = this match {
      case Pair(hd, tl) =>
        if (index == 0) Success(hd) else tl(index - 1)
      case End() =>
        Failure("Index out of bounds")
    }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

//assert(example(0) == Success(1))
//assert(example(1) == Success(2))
//assert(example(2) == Success(3))
//assert(example(3) == Failure("Index out of bounds"))
