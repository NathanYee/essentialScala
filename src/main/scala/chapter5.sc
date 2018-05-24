import java.util
// 5

// 5.1.2 Generic Algebraic Data Types

// can be improved using variance
sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

// 5.1.3 Exercises
sealed trait LinkedList[A] {
  def length: Int = this match {
    case Pair(head, tail) => 1 + tail.length
    case End() => 0
  }

  def contains[A](v: A): Boolean = this match {
    case Pair(h, t) => if (h == v) true else t.contains(v)
    case End() => false
  }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]
val example = Pair(1, Pair(2, Pair(3, End())))

assert(example.length == 3)
assert(example.tail.length == 2)
assert(End().length == 0)

assert(example.contains(3) == true)
assert(example.contains(4) == false)
