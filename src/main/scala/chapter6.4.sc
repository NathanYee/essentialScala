// 6.4 Options

// 6.4.1 Option, Some, and None
def readInt(str: String): Option[Int] =
  if (str matches "\\d+") Some(str.toInt) else None

readInt("123")
readInt("abc")

// 6.4.2 Extracting Values from Options
readInt("123") match {
  case Some(number) => number + 1
  case None => 0
}

readInt("abc").getOrElse(0)

// 6.4.3 Options as Sequences
def sum(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  optionA.flatMap(a => optionB.map(b => a + b))

sum(readInt("1"), readInt("2"))
sum(readInt("1"), readInt("b"))
sum(readInt("a"), readInt("2"))
