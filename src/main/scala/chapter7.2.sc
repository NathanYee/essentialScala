final case class Rational(numerator: Int, denominator: Int)

object Rational {
  implicit val ordering = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}

object Example {
  implicit val higherPriorityImplicit = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) >
      (y.numerator.toDouble / y.denominator.toDouble)
  )

  def example =
    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
      List(Rational(3, 4), Rational(1, 2), Rational(1, 3)))
}

Example.example

//7.2.5 Exercises
//7.2.5.1 Ordering Orders

final case class Order(units: Int, unitPrice: Double) {
  val totalPrice: Double = units * unitPrice
}

object Order {
  implicit val ordering = Ordering.fromLessThan[Order]( (x, y) =>
    x.totalPrice > y.totalPrice)
}

object NumberUnitsOrdering {
  implicit val ordering = Ordering.fromLessThan[Order]( (x, y) =>
    x.units > y.units)
}

object UnitPriceOrdering {
  implicit val ordering = Ordering.fromLessThan[Order]( (x, y) =>
    x.totalPrice > y.totalPrice)
}