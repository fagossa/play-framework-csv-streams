package models

import play.api.libs.json.Json

case class Transaction(ibanSource: String, money: BigDecimal, `type`: String, company: String) {
  def matchQuery(query: Option[String]): Boolean = {
    query match {
      case None => true
      case Some(q) => ibanSource.contains(q)
    }
  }
}

object Transaction {

  implicit val format = Json.writes[Transaction]

  def fromString(line: String): Option[Transaction] =
    line.split(",").toList match {
      case iban :: rawoney :: kind :: company :: Nil => Some(Transaction(iban, BigDecimal(rawoney), kind, company))
      case _ => None
    }
}
