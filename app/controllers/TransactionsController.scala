package controllers

import scala.concurrent.Future

import akka.stream.IOResult
import akka.stream.scaladsl.Source
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import services.TransactionService

class TransactionsController(
  transactionService: TransactionService,
  cc: ControllerComponents
) extends AbstractController(cc) {


  implicit val ec = scala.concurrent.ExecutionContext.global

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def transactions(query: Option[String]) = Action.async {
    val value: Source[JsValue, Future[IOResult]] = transactionService.transactions(query)
      .map { t => Json.toJson(t)}

    Future(Ok.chunked(value).as("application/json"))
  }

}
