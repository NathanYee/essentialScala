// 6.7 For Comprehensions Redux

for (x <- Seq(-2, -1, 0, 1, 2) if x > 0) yield x

for {
  x <- Seq(1, 2, 3)
  y <- Seq(10, 11, 12)
} yield x + y

val pairs = Seq(1, 2, 3).zip(Seq(10, 11, 12))

for (x <- pairs) yield {val (a, b) = x; a + b}

pairs.map(x => x._1 + x._2)

for (x <- Seq(1, 2, 3).zipWithIndex) yield x

for ((a, b) <- pairs) yield a + b

for {
  x <- Seq(1, 2, 3)
  squareX = x * x
  y <- Seq(4, 5, 6)
  squareY = y * y
} yield squareX + squareY

