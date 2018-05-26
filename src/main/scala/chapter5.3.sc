sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case End() => end
      case Pair(h, t) => f(h, t.fold(end, f))
    }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

// 5.3.3
object Sum {
  def sum(x: Int, y: Int) = x + y
}

(Sum.sum _)(_, _)

def twoParams(x: Int)(y: Int) = x + y

twoParams(1)(3)

// 5.3.4 Exercises
sealed trait Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B
}
final case class Node[A](l: Tree[A], r: Tree[A]) extends Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B =
    node(l.fold(node, leaf), r.fold(node, leaf))
}
final case class Leaf[A](v: A) extends Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B =
    leaf(v)
}

val tree: Tree[String] =
  Node(Node(Leaf("To"), Leaf("iterate")),
    Node(Node(Leaf("is"), Leaf("human,")),
      Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

tree.fold[String]((a, b) => s"$a $b", str => str)