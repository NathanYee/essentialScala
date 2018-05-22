// 4.3 Modelling Data with Traits

// 4.4 The Sum Type Pattern

// A traffic light is red, green, or yellow. Translate this description into Scala code.
sealed trait TrafficLight
final case object Red extends TrafficLight
final case object Yellow extends TrafficLight
final case object Green extends TrafficLight

// A calculation may succeed (with an Int result) or fail (with a String message). Implement this.
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(message: String) extends Calculation

// Bottled water has a size (an Int), a source (which is a well, spring, or tap), and a Boolean carbonated.
final case class BottledWater(size: Int, source: Source, carbonated: Boolean)
sealed trait Source
final case object Well extends Source
final case object Spring extends Source
final case object Tap extends Source

