package repositories

import java.nio.file.Paths
import scala.concurrent.Future

import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Framing, Source}
import akka.util.ByteString

import models.Transaction

class TransactionRepository {

  def readFile(): Source[Transaction, Future[IOResult]] =
    FileIO.fromPath(Paths.get("conf/transactions-dump.csv"))
    .drop(1)
    .via(Framing.delimiter(
      ByteString("\n"),
      maximumFrameLength = 256,
      allowTruncation = true))
    .map(_.utf8String)
    .map(line => Transaction.fromString(line))
    .collect {
      case Some(transaction) => transaction
    }

}
