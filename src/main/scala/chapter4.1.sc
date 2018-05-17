// 4.1 Traits

import java.util.Date

trait Visitor {
  def id: String
  def createdAt: Date

  def age: Long = new Date().getTime - createdAt.getTime
}

case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

def older(v1: Visitor, v2: Visitor): Boolean = v1.createdAt.before(v2.createdAt)

older(Anonymous("1"), User("2", "test@example.me"))

// 4.1.4 Exercises

// 4.1.4.1 More Cats
trait Feline {
  def colour: String
  def sound: String
}

trait BigCat extends Feline {
  val sound = "roar"
}

case class Lion(colour: String, maneSize: Int) extends BigCat

case class Tiger(colour: String) extends BigCat

case class Panther(colour: String) extends BigCat

case class Cat(colour: String, food: String) extends Feline {
  val sound = "meow"
}

def colours(f1: Feline, f2: Feline): String = f1.colour + " " + f2.colour

colours(Tiger("Yellow"), Cat("Orange", "Chips"))

// 4.1.4.2 Shapes
trait Shape {
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

case class Circle(radius: Float) extends Shape {
  val sides = 1
  val perimeter: Double = 2 * math.Pi * radius
  val area: Double = math.Pi * math.pow(radius, 2)
}

case class Rectangle(width: Double, height: Double) extends Rectangular

case class Square(length: Double) extends Rectangular {
  val width = length
  val height = length
}
