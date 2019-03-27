package controllers

import play.api.libs.json.{ Json }
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
  def index = Action.async {
    transactionService.transactions()
      .map(Json.toJson(_))
        .map(js => Ok(js))
  }

}
