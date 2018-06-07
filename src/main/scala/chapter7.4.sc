// 7.4 Implicit Parameter and Interfaces

trait HtmlWriter[A] {
  def write(in: A): String
}

object HtmlUtil {
  def htmlify[A](data: A)(implicit writer: HtmlWriter[A]): String = {
    writer.write(data)
  }
}

implicit object ApproximationWriter extends HtmlWriter[Int] {
  def write(in: Int): String =
    s"It's definitely less than ${((in / 10) + 1) * 10}"
}

HtmlUtil.htmlify(2)

case class Person(name: String, email:String)

object HtmlWriter {
  def apply[A](implicit writer: HtmlWriter[A]): HtmlWriter[A] =
    writer
}

implicit object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) =
    s"${person.name} (${person.email})"
}

// HtmlWriter[Person] --> implicit object PersonWriter because of apply method
// implicit object PersonWriter has a write method
HtmlWriter[Person].write(Person("Noel", "noel@example.org"))


// 7.4.4 Exercises
trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

object EmailImplicit {
  implicit object EmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email
  }
}

object NameEmailImplicit {
  implicit object NameEmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email && v1.name == v2.name
  }
}

object Eq {
  def apply[A](a: A, b: A)(implicit eq: Equal[A]): Boolean = {
    eq.equal(a, b)
  }
}

def byEmail = {
  import NameEmailImplicit._
  Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
}


object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] =
    instance
}

object noArgs {
  import NameEmailImplicit._
  Equal[Person].equal(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
}