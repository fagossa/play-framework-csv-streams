import repositories.TransactionRepository
import play.api._
import play.api.routing.Router

import services.TransactionService

class MyApplicationLoader extends ApplicationLoader {
  private var components: MyComponents = _

  def load(context: ApplicationLoader.Context): Application = {
    components = new MyComponents(context)
    components.application
  }
}

class MyComponents(context: ApplicationLoader.Context) 
  extends BuiltInComponentsFromContext(context)
  with play.filters.HttpFiltersComponents
  with _root_.controllers.AssetsComponents {

  lazy val repo = new TransactionRepository

  lazy val transactionService = new TransactionService(repo)

  lazy val homeController = new _root_.controllers.TransactionsController(transactionService, controllerComponents)

  lazy val router: Router = new _root_.router.Routes(httpErrorHandler, homeController, assets)
}
