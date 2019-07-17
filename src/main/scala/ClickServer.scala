import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer


object ClickServer  {

  def main(args: Array[String]) : Unit = {
    implicit val system = ActorSystem("DirectivesBreakdown")
    implicit val materializer = ActorMaterializer()
    import system.dispatcher
    import Routes.Generic._

    Http().bindAndHandle(libraryRoute, "localhost", 8081)
  }

}
