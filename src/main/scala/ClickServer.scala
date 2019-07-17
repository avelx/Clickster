import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse, StatusCodes, Uri}
import akka.stream.ActorMaterializer

import scala.concurrent.Future


object ClickServer extends App {

  implicit val system = ActorSystem("RestSystem")
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val requestHandler: HttpRequest => Future[HttpResponse] = {

    case HttpRequest(HttpMethods.GET, uri@Uri.Path("/api/status"), _, _, _) =>
      Future {
        HttpResponse(
          StatusCodes.OK,
          entity = HttpEntity(
            ContentTypes.`text/html(UTF-8)`,
            """
              |<html>
              | <body>
              |   Hello from Akka HTTP!
              | </body>
              |</html>
            """.stripMargin
          )
        )
      }
  }

  Http().bindAndHandleAsync(requestHandler, "localhost", 8080)
}
