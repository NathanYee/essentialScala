// chapter 2.4

// object literals
object Test {}

Test

// methods
object Test2 {
  def name: String = "The best object"
}

Test2.name

object Test3 {
  def hello(name: String) =
    "Hello " + name
}

Test3.hello("Me")
// res2: String = Hello Me


// fields
object Test4 {
  // val is immutable field
  // var is mutable field
  val name = "burrito"
  def hello(other: String) =
    name + " says hi to " + other
}
Test4.hello("taco")

// methods vs fields
object Test7 {
  val simpleField = {
    println("Evaluating simpleField")
    42
  }
  def noParamMethod = {
    println("Evaluating noParamMethod")
    42
  }
}

Test7

Test7.simpleField
Test7.simpleField

Test7.noParamMethod
Test7.noParamMethod

// exercises
// cat-o-matique
object Oswald {
  val colour = "Black"
  val food = "Milk"
}

object Henderson {
  val colour = "Ginger"
  val food = "Chips"
}

object Pumpkin {
  val colour = "Orange & White Tabby"
  val food = "Frisky Bitz"
}

// square
object calc {
  def square(x: Double) = x * x
  def cube(x: Double) = x * square(x)
}
calc.cube(5.0)
calc.cube(5)

object calc2 {
  def square(x: Double) = x * x
  def cube(x: Double) = x * square(x)

  def square(x: Int) = x * x
  def cube(x: Int) = x * square(x)
}
calc2.cube(5.0)
calc2.cube(5)

// order of evaluation
object argh {
  def a = {
    println("a")
    1
  }

  val b = {
    println("b")
    a + 2
  }

  def c = {
    println("c")
    a
    b + "c"
  }
}
argh.c + argh.b + argh.a
//b
//a
//c
//a
//a
//res13: String = 3c31

// greetings, human
object person {
  val firstName = "Jimmy"
  val lastName = "Little"
}

object alient {
  def greet(p: person.type) =
    "Greetings, " + p.firstName + " " + p.lastName
}

alient.greet(person)
