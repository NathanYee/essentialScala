// 7.1 Type Class Instances

import scala.math.Ordering

val minOrdering = Ordering.fromLessThan[Int](_ < _)
val maxOrdering = Ordering.fromLessThan[Int](_ > _)

List(3, 4, 2).sorted(minOrdering)
List(3, 4, 2).sorted(maxOrdering)

//implicit val ordering = Ordering.fromLessThan[Int](_ < _)
//List(1, 7, 3, 5, 6).sorted

val absOrdering = Ordering.fromLessThan[Int](math.abs(_) < math.abs(_))
assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))

implicit val ordering = Ordering.fromLessThan[Int](math.abs(_) < math.abs(_))
assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))


final case class Rational(numerator: Int, denominator: Int)
object Rational {
  implicit val rationalOrdering = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}

assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
  List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))

