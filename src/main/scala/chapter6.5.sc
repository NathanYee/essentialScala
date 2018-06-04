import com.sun.xml.internal.bind.v2.model.core.NonElementRef

// 6.5 Options as Flow Control
val optionA = Option(123)
val optionB = Option(234)

for {
  a <- optionA
  b <- optionB
} yield a + b

optionA.flatMap(a => Option(a))
optionA.map(a => Option(a))
optionA.map(a => a)

optionA.flatMap(a => optionB.map(b => a + b))

// 6.5.1 Exercises
def addOptions(optionA: Option[Int])(optionB: Option[Int]): Option[Int] = {
  for {
    a <- optionA
    b <- optionB
  } yield a + b
}

def addOptions2(optA: Option[Int])(optB: Option[Int]): Option[Int] = {
  optA.flatMap(a => optB.map(b => a + b))
}

// 6.5.1.2 Adding All of the Things
def addOptions3(optA: Option[Int], optB: Option[Int], optC: Option[Int]): Option[Int] = {
  for {
    a <- optA
    b <- optB
    c <- optC
  } yield a + b + c
}

// 6.5.1.3 A(nother) Short Division Exercise
def divide(numerator: Int, denominator: Int) =
  if (denominator == 0) None else Some(numerator / denominator)

def divideOptions(numerator: Option[Int], denominator: Option[Int]): Option[Int] = {
  for {
    a <- numerator
    b <- denominator
    c <- divide(a, b)
  } yield c
}


def readInt(str: String): Option[Int] =
  if (str matches "\\d+") Some(str.toInt) else None

def calculator(operand1: String, operator: String, operand2: String): Unit = {
  val result = for {
    a <- readInt(operand1)
    b <- readInt(operand2)
    ans <- operator match {
      case "+" => Some(a + b)
      case "-" => Some(a - b)
      case "*" => Some(a * b)
      case "/" => divide(a, b)
      case _ => None
    }
  } yield ans

  result match {
    case Some(number) => println(s"$operand1 $operator $operand2 = $number")
    case None => println(s"Error calculating $operand1 $operator $operand2")
  }
}

calculator("1", "+", "2")
calculator("1", "/", "0")
