package controllers.api.todo

import controllers.api.todo.ApiGetAllToDosController._
import lib.AsyncBaseController
import play.api.libs.json._
import play.api.mvc.{ Action, AnyContent }

import javax.inject.Singleton
import scala.concurrent.Future

@Singleton
class ApiGetAllToDosController extends AsyncBaseController {
  def action(): Action[AnyContent] = Action.async { implicit req =>
    val response = Response(
      list = Seq(
        ToDo(
          id       = 1,
          title    = "todo1",
          body     = "body1",
          status   = State.TODO,
          category = Category(
            name  = "category1",
            color = "#ffe4b5"
          )
        ),
        ToDo(
          id       = 2,
          title    = "todo2",
          body     = "body3",
          status   = State.IN_PROGRESS,
          category = Category(
            name  = "category1",
            color = "#00ffff"
          )
        ),
        ToDo(
          id       = 3,
          title    = "todo2",
          body     = "body3",
          status   = State.DONE,
          category = Category(
            name  = "category1",
            color = "#7fffd4"
          )
        )
      )
    )
    val json     = Json.toJson(response)

    Future.successful(
      Ok(json)
    )
  }
}

object ApiGetAllToDosController {
  implicit lazy val responseWrites: Writes[Response] = Json.writes
  implicit lazy val toDoWrites:     Writes[ToDo]     = Json.writes
  implicit lazy val stateWrites:    Writes[State]    = Writes.apply {
    case State.TODO        => JsString("TODO")
    case State.IN_PROGRESS => JsString("IN_PROGRESS")
    case State.DONE        => JsString("DONE")
  }
  implicit lazy val categoryWrites: Writes[Category] = Json.writes

  case class Response(
    list: Seq[ToDo]
  ) {}

  case class ToDo(
    id:       Long,
    title:    String,
    body:     String,
    status:   State,
    category: Category,
  )
  sealed trait State
  object State {
    case object TODO     extends State
    case object IN_PROGRESS extends State
    case object DONE     extends State
  }

  case class Category(
    name:  String,
    color: String,
  )
}
