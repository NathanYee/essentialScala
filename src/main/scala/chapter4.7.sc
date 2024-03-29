// 4.7.0.1 A Calculator

sealed trait Expression {
  def eval: Double = this match {
    case Addition(left, right) => left.eval + right.eval
    case Subtraction(left, right) => left.eval - right.eval
    case Number(value) => value
  }
}

final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Number(value: Double) extends Expression

// 4.7.0.2 JSON
sealed trait Json {
  def print: String = {

    def quote(s: String): String =
    '"'.toString ++ s ++ '"'.toString

    def seqToJson(seq: SeqCell): String = seq match {
      case SeqCell(h, t @ SeqCell(_,_)) =>
        s"${h.print}, ${seqToJson(t)}"
      case SeqCell(h, SeqEnd) => h.print
    }

    def objectToJson(obj: ObjectCell): String = obj match {
      case ObjectCell(k, v, t @ ObjectCell(_, _, _)) =>
        s"${quote(k)}: ${v.print}, ${objectToJson(t)}"
      case ObjectCell(k, v, ObjectEnd) =>
        s"${quote(k)}: ${v.print}"
    }

    this match {
      case JsNumber(v) => v.toString
      case JsString(v) => v
      case JsBoolean(v) => v.toString
      case JsNull => "null"
      case s @ SeqCell(_, _) => "[" ++ seqToJson(s) ++ "]"
      case SeqEnd => "[]"
      case o @ ObjectCell(_, _, _) => "{" ++ objectToJson(o) ++ "}"
      case ObjectEnd => "{}"
    }

  }
}

final case class JsNumber(value: Double) extends Json
final case class JsString(value: String) extends Json
final case class JsBoolean(value: Boolean) extends Json
final case object JsNull extends Json

sealed trait JsSequence extends Json
final case class SeqCell(head: Json, tail: JsSequence) extends JsSequence
final case object SeqEnd extends JsSequence

sealed trait JsObject extends Json
final case class ObjectCell(key: String, value: Json, tail: JsObject) extends JsObject
final case object ObjectEnd extends JsObject

SeqCell(JsString("a string"), SeqCell(JsNumber(1.0), SeqCell(JsBoolean(true), SeqEnd))).print
// res: String = ["a string", 1.0, true]

ObjectCell(
  "a", SeqCell(JsNumber(1.0), SeqCell(JsNumber(2.0), SeqCell(JsNumber(3.0), SeqEnd))),
  ObjectCell(
    "b", SeqCell(JsString("a"), SeqCell(JsString("b"), SeqCell(JsString("c"), SeqEnd))),
    ObjectCell(
      "c", ObjectCell("doh", JsBoolean(true),
        ObjectCell("ray", JsBoolean(false),
          ObjectCell("me", JsNumber(1.0), ObjectEnd))),
      ObjectEnd
    )
  )
).print
// res: String = {"a": [1.0, 2.0, 3.0], "b": ["a", "b", "c"], "c": {"doh": true, "ray": false, "me": 1.0}}