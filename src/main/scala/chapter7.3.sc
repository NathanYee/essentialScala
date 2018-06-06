// 7.3 Creating Type Classes

// 7.3.4 Exercises
// 7.3.4.1 Equality
trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

case class Person(name: String, email:String)

object EmailEqual extends Equal[Person] {
  override def equal(v1: Person, v2: Person): Boolean = {
    v1.email == v2.email
  }
}

object NameEmailEqual extends Equal[Person] {
  override def equal(v1: Person, v2: Person): Boolean = {
    v1.email == v2.email && v1.name == v2.name
  }
}