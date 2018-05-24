// 5.2 Functions

// 5.2.2 Function literals
val hi = () => "Hi!"
hi()

val sum = (a: Int, b: Int) => a + b: Int
sum(10, 20)

// 5.2.3.1 A Better Abstraction


sealed trait IntList {
  def fold(end: Int, f: (Int , Int) => Int): Int =
    this match {
      case End => end
      case Pair(hd, tl) => f(hd, tl.fold(end,f))
    }
  def sum: Int = fold(0, (h, t) => h + t)
  def product: Int = fold(1, (h, t) => h * t)
  def length: Int = fold(0, (_, t) => 1 + t)

  def fold2[A](end: A, f: (Int, A) => A): A =
    this match {
      case End => end
      case Pair(h, t) => f(h, t.fold2(end, f))
    }
  def double: IntList =
    fold2[IntList](End, (h, t) => Pair(h * 2, t))
}

final case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

val tester = Pair(1, Pair(2, Pair(3, End)))
tester.sum
tester.product
tester.length
tester.double


