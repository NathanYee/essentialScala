// 7.6 Combining Type Classes and Type Enrichment

// 7.6.2 Exercises
// 7.6.2.1 Drinking the Kool Aid
object IntImplicits {
  implicit class IntOps(n: Int) {
    def times(f: Int => Unit) = for{x <- 0 until n} f(x)
    def yeah = times(_ => println("Oh yeah!"))
  }

  2.times(x => println(s"Look - it's the number $x!"))
  3.yeah
}

// 7.6.3 Easy Equality
trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] =
    instance

  implicit class ToEqual[A](in: A) {
    def ===(other: A)(implicit equal: Equal[A]): Boolean =
      equal.equal(in, other)
  }
}

object runner {
  import Equal._

  implicit val caseInsensitiveEquals = new Equal[String] {
    def equal(s1: String, s2: String) =
      s1.toLowerCase == s2.toLowerCase
  }

  println("cake".===("CAKE"))
}

runner