// chapter 2.5
object calc {
  def square(x: Double) = x * x
  def cube(x: Double) = x * square(x)
}

assert(calc.square(2.0) == 4.0)
assert(calc.square(3.0) == 9.0)
assert(calc.square(-2.0) == 4.0)

// chapter 2.6
if(1 < 2) "Yes" else "No"

