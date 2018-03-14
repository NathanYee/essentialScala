// 3.1.1
class Cat {
  val name = "Pumpkin"
  val breed = "Orange & White Tabby"
  def cato = name + ", " + breed
}

val happyCat = new Cat
happyCat.cato

val otherHappyCat = new Cat

object alien {
  def greet(c: Cat) =
    "greetings " + c.cato
}

alien.greet(happyCat)

// 3.1.2
class Cato(arg1: String, arg2: String) {
  val name = arg1
  val breed = arg2
  def cato = "My naem is " + name + ". I am " + breed
}

val caty = new Cato("Pumpkin", "Orange & White Tabby")
caty.cato

class Cat2(val name: String, val breed: String) {
  def cato = "My naem is " + name + ". I am " + breed
}

val happyCat2 = new Cat2("Pumpkin", "Orange & White Tabby")

// 3.1.3
new Cat2(name = "Pumpkin", breed = "Tabby")
