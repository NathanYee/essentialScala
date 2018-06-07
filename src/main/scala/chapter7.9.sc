// 7.9 JSON Serialisation
sealed trait JsValue {
  def stringify: String
}

final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  def stringify = values
    .map { case (name, value) => "\"" + name + "\":" + value.stringify }
    .mkString("{", ",", "}")
}

final case class JsString(value: String) extends JsValue {
  def stringify = "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
}

JsObject(Map("foo" -> JsString("a"), "bar" -> JsString("b"), "baz" -> JsString("c"))).stringify

// 7.9.1 Convert X to JSON
sealed trait JsWriter[A] {
  def write(value: A): JsValue
}

implicit class JsUtil[A](value: A){
  def toJson[A](implicit writer: JsWriter[A]) =
    writer write value
}

import java.util.Date

implicit object StringWriter extends JsWriter[String] {
  def write(value: String) = JsString(value)
}

implicit object DateWriter extends JsWriter[Date] {
  def write(value: Date) = JsString(value.toString)
}

sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime() - createdAt.getTime()
}

final case class Anonymous(
                            val id: String,
                            val createdAt: Date = new Date()
                          ) extends Visitor

final case class User(
                       val id: String,
                       val email: String,
                       val createdAt: Date = new Date()
                     ) extends Visitor


implicit object AnonymousWriter extends JsWriter[Anonymous] {
  def write(value: Anonymous) = JsObject(Map(
    "id"        -> value.id.toJson,
    "createdAt" -> value.createdAt.toJson
  ))
}

implicit object UserWriter extends JsWriter[User] {
  def write(value: User) = JsObject(Map(
    "id"           -> value.id.toJson,
    "email"        -> value.email.toJson,
    "createdAt"    -> value.createdAt.toJson
  ))
}

implicit object VisitorWriter extends JsWriter[Visitor] {
  def write(value: Visitor) = value match {
    case anon: Anonymous => anon.toJson
    case user: User      => user.toJson
  }
}

val visitors: Seq[Visitor] = Seq(Anonymous("001", new Date), User("003", "dave@xample.com", new Date))

visitors.map(visitor => visitor.toJson)