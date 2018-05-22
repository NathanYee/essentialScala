// 4.5.6 Exercises

// 4.5.6.1
sealed trait TrafficLight {
  def next: TrafficLight = this match {
    case Red => Green
    case Green => Yellow
    case Yellow => Red
  }
}
final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Yellow extends TrafficLight

// 4.5.6.2
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

final case object Calculator{
  def +(calculation: Calculation, int: Int): Calculation = calculation match {
    case Success(result) => Success(result + int)
    case Failure(reason) => Failure(reason)
  }

  def -(calculation: Calculation, int: Int): Calculation = calculation match {
    case Success(result) => Success(result - int)
    case Failure(reason) => Failure(reason)
  }

  def /(calculation: Calculation, int: Int): Calculation = calculation match {
    case Success(result) => int match {
      case 0 => Failure("Division by zero")
      case _ => Success(result / int)
    }
    case Failure(reason) => Failure(reason)
  }
}

assert(Calculator.+(Success(1), 1) == Success(2))
assert(Calculator.-(Success(1), 1) == Success(0))
assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))
assert(Calculator./(Success(4), 2) == Success(2))
assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))