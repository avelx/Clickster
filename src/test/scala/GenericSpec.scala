import Routes.{Book, BookJsonProtocol}
import akka.http.scaladsl.model.{StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}


class GenericSpec extends WordSpec with Matchers with ScalatestRouteTest with BookJsonProtocol {

  import Routes.Generic._

  "A digital library backend" should {
    "return all the books in the library" in {
      // send an HTTP request through an endpoint that you want to test
      // inspect the response
      Get("/api/book") ~> libraryRoute ~> check {
        // assertions
        status shouldBe StatusCodes.OK
        entityAs[List[Book]] shouldBe books
      }
    }

  }

}
