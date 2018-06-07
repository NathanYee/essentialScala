// 7.8 Implicit Conversions

// 7.8.1 Implicit conversions
class B {
  def bar = "the best method ever!"
}

class A

implicit def aToB(in: A) = new B()

new A().bar

// 7.8.2 Designing with Implicit Conversions
implicit def intToBoolean(int: Int) = int == 0
if(1) "yes" else "no"
if(0) "yes" else "no"

// 7.8.3 Exercises
// 7.8.3.1 Implicit Class Conversion

object IntImplicits {
  class IntOps(n: Int) {
    def yeah =
      times(_ => println("Oh yeah!"))

    def times(f: Int => Unit) =
      for(i <- 0 until n) f(i)
  }

  implicit def intToIntOps(value: Int) =
    new IntOps(value)
}

import IntImplicits._
5.yeah

