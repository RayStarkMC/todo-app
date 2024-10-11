package controllers.api.todo

import controllers.api.todo.ApiGetAllToDosController._
import lib.AsyncBaseController
import lib.usecase.query.api.GetAllToDosQuery
import lib.usecase.query.api.GetAllToDosQuery.{Input, State => QueryState}
import play.api.libs.json._
import play.api.mvc.{Action, AnyContent}

import javax.inject.{Inject, Singleton}

@Singleton
class ApiGetAllToDosController @Inject() (
  query: GetAllToDosQuery,
) extends AsyncBaseController {

  def action(): Action[AnyContent] = Action.async { implicit req =>
    val input = Input()
    for {
      output <- query.run(input)
    } yield {
      val response = Response(
        list = output.list.map { todo =>
          ToDo(
            id       = todo.id,
            title    = todo.title,
            body     = todo.body,
            status   = todo.status match {
              case QueryState.TODO        => State.TODO
              case QueryState.IN_PROGRESS => State.IN_PROGRESS
              case QueryState.DONE        => State.DONE
            },
            category = Category(
              name = todo.category.name,
              color = todo.category.color
            )
          )
        }
      )

      val json = Json.toJson(response)
      Ok(json)
    }
  }
}

object ApiGetAllToDosController {
  implicit lazy val responseWrites: Writes[Response] = Json.writes
  implicit lazy val toDoWrites:     Writes[ToDo]     = Json.writes
  implicit lazy val stateWrites:    Writes[State]    = Writes {
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
    case object TODO        extends State
    case object IN_PROGRESS extends State
    case object DONE        extends State
  }

  case class Category(
    name:  String,
    color: String,
  )
}
