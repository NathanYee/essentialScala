// 4.6 Recursive Data
sealed trait IntList {
  def length: Int = this match {
    case Pair(head, tail) => 1 + tail.length
    case End => 0
  }

  def product: Int = {
    def _product(res: Int, rem: IntList): Int = rem match {
      case End => res
      case Pair(head, tail) => _product(res * head, tail)
    }
    _product(1, this)
  }
}

final case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList


val example = Pair(1, Pair(2, Pair(3, End)))
assert(example.length == 3)
assert(example.tail.length == 2)
assert(End.length == 0)

assert(example.product == 6)
assert(example.tail.product == 6)
assert(End.product == 1)

// 4.6.3.2 The Forest of Trees
sealed trait Tree
final case class Node(val l: Tree, val r: Tree) extends Tree
final case class Leaf(val elt: Int) extends Tree

object TreeOps {
  def sum(tree: Tree): Int = tree match {
    case Node(l, r) => sum(l) + sum(r)
    case Leaf(elt) => elt
  }

  def double(tree: Tree): Tree = tree match {
    case Node(l, r) => Node(double(l) , double(r))
    case Leaf(elt) => Leaf(elt * 2)
  }
}