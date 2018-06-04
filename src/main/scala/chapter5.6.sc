
// 5.6 Variance

sealed trait Maybe[+A]
final case class Full[A](value: A) extends Maybe[A]
final case object Empty extends Maybe[Nothing]

val perhaps: Maybe[Int] = Empty

// 5.6.4 Contravariant Position
