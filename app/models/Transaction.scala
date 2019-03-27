package models

case class Transaction(ibanSource: String, money: BigDecimal, `type`: String, company: String)

object Transaction {
  def fromString(line: String): Option[Transaction] =
    line.split(",").toList match {
      case iban :: rawoney :: kind :: company :: Nil => Some(Transaction(iban, BigDecimal(rawoney), kind, company))
      case _ => None
    }
}
