//sealed trait Shape {
//  def sides: Int
//  def perimeter: Double
//  def area: Double
//}
//
//trait Rectangular extends Shape {
//  def width: Double
//  def height: Double
//  val sides = 4
//  val perimeter = (2 * width) + (2 * height)
//  val area = width * height
//}
//
//case class Circle(radius: Float) extends Shape {
//  val sides = 1
//  val perimeter: Double = 2 * math.Pi * radius
//  val area: Double = math.Pi * math.pow(radius, 2)
//}
//
//case class Rectangle(width: Double, height: Double) extends Rectangular
//
//case class Square(length: Double) extends Rectangular {
//  val width = length
//  val height = length
//}
//
//object Draw {
//  def apply(shape: Shape): String = shape match {
//    case Circle(radius) => s"A circle of radius $radius"
//    case Rectangle(width, height) => s"A rectangle of width $width, and height $height"
//    case Square(length) => s"A square of length $length"
//  }
//}
//
//Draw(Square(10))

sealed trait Color {
  def R: Int
  def G: Int
  def B: Int
  def light: String = if ((R + G + B) / 256 > .5) "light" else "dark"
}

final case object Red extends Color {
  val R = 256
  val G = 0
  val B = 0
}

final case object Yellow extends Color {
  val R = 256
  val G = 256
  val B = 0
}

final case object Pink extends Color {
  val R = 256
  val G = 0
  val B = 256
}

final case class CustomColor(R: Int, G: Int, B: Int) extends Color

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Color
}

trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  val perimeter = (2 * width) + (2 * height)
  val area = width * height
}

case class Circle(radius: Float, color: Color) extends Shape {
  val sides = 1
  val perimeter: Double = 2 * math.Pi * radius
  val area: Double = math.Pi * math.pow(radius, 2)
}

case class Rectangle(width: Double, height: Double, color: Color) extends Rectangular

case class Square(length: Double, color: Color) extends Rectangular {
  val width = length
  val height = length
}

object Draw {
  def apply(shape: Shape): String = shape match {
    case Circle(radius, color) => s"A ${Draw(color)} circle of radius $radius"
    case Rectangle(width, height, color) => s"A ${Draw(color)} rectangle of width $width, and height $height"
    case Square(length, color) => s"A ${Draw(color)} square of length $length"
  }

  def apply(color: Color): String = color match {
    case Red => "red"
    case Yellow => "yellow"
    case Pink => "pink"
    case color => color.light
  }
}

Draw(Square(10, Yellow))
Draw(Square(10, CustomColor(0,0,0)))
Draw(Square(10, CustomColor(256,128,128)))