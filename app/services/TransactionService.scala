package services

import scala.concurrent.{ExecutionContext, Future}

import akka.stream.IOResult
import akka.stream.scaladsl.Source
import repositories.TransactionRepository

import models.Transaction

class TransactionService(repo: TransactionRepository) {

  def transactions(query: Option[String])(implicit ec: ExecutionContext): Source[Transaction, Future[IOResult]] =
    repo.readFile()
      .filter(t => t.matchQuery(query))

}
