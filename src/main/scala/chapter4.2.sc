// 4.2.2

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  val perimeter = (2 * width) + (2 * height)
  val area = width * height
}

final case class Circle(radius: Float) extends Shape {
  val sides = 1
  val perimeter: Double = 2 * math.Pi * radius
  val area: Double = math.Pi * math.pow(radius, 2)
}

final case class Rectangle(width: Double, height: Double) extends Rectangular

final case class Square(length: Double) extends Rectangular {
  val width = length
  val height = length
}

object Draw {
  def apply(shape: Shape): String = shape match {
    case Circle(radius) => s"A circle of radius $radius"
    case Rectangle(width, height) => s"A rectangle of width $width, and height $height"
    case Square(length) => s"A square of length $length"
  }
}

Draw(Square(10))

// 4.2.2.2.2 in separate file

// 4.2.2.3 A Short Division Exercise

sealed trait DivisionResult
final case class Finite(value: Int) extends DivisionResult
final case object Infinite extends DivisionResult

object divide {
  def apply(i1: Int, i2: Int): DivisionResult = if (i2 == 0) Infinite else Finite (i1 / i2)
}

divide(1, 0) match {
  case Finite(value) => s"Finite: $value"
  case Infinite => s"Infinite"
}

