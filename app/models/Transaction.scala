package models

import play.api.libs.json.{Json, Writes}

case class Transaction(ibanSource: String, money: BigDecimal, `type`: String, company: String)

object Transaction {

  implicit val format = Json.writes[Transaction]

  def fromString(line: String): Option[Transaction] =
    line.split(",").toList match {
      case iban :: rawoney :: kind :: company :: Nil => Some(Transaction(iban, BigDecimal(rawoney), kind, company))
      case _ => None
    }
}
