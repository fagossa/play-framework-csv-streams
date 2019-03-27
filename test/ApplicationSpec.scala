import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import repositories.TransactionRepository
import play.api.test.Helpers._
import play.api.test._

import models.Transaction

/**
 * Runs an integration test with an application
 */
class ApplicationSpec extends PlaySpec
  with BaseOneAppPerTest
  with MyApplicationFactory
  with ScalaFutures {

  "Routes" should {
    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }
  }

  "HomeController" should {
    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include("Your new application is ready.")
    }
  }

  "Transaction" should {
    "be created from valid lines" in {
      // given
      val line = "TR98627893W269Z7C501533583,299.95,deposit,Hoppe and Sons"
      // when
      val maybeTransaction: Option[Transaction] = Transaction.fromString(line)
      maybeTransaction must be (Option(Transaction(
        ibanSource="TR98627893W269Z7C501533583",
        money = BigDecimal("299.95"),
        `type`="deposit",
        company = "Hoppe and Sons"
      )))
    }

    "ignored when invalid lines" in {
      // given
      val line = "TR98627893W269Z7C501533583"
      // when
      val maybeTransaction: Option[Transaction] = Transaction.fromString(line)
      maybeTransaction must be (None)
    }
  }

  "TransactionRespository" should {
    val repo = new TransactionRepository
    "render the index page" in {

     /* // when
      repo.readFile()

      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include("Your new application is ready.")*/
    }
  }
}
