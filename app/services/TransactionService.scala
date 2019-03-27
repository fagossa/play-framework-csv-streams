package services

import scala.concurrent.{ExecutionContext, Future}

class TransactionService() {
  def transactions()(implicit ec: ExecutionContext): Future[List[String]] =
    Future(
      List("hello", "world")
    )
}
