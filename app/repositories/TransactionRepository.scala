package repositories

import java.nio.file.Paths
import scala.concurrent.Future

import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Source}

class TransactionRepository {

  def readFile(): Source[String, Future[IOResult]] =
    FileIO.fromPath(Paths.get("transactions-dump.csv"))
    .drop(1)
    .map(_.utf8String)

}
