package controllers.api.todo

import controllers.api.todo.ApiGetAllToDosController._
import lib.AsyncBaseController
import lib.usecase.query.api.GetAllToDosQuery
import lib.usecase.query.api.GetAllToDosQuery.{ Input, State }
import play.api.libs.json._
import play.api.mvc.{ Action, AnyContent }

import javax.inject.{ Inject, Singleton }

@Singleton
class ApiGetAllToDosController @Inject() (
  query: GetAllToDosQuery,
) extends AsyncBaseController {

  def action(): Action[AnyContent] = Action.async { implicit req =>
    val input = Input()
    for {
      output <- query.run(input)
    } yield {
      val response = JsResponse(
        list = output.list.map { todo =>
          JsToDo(
            id       = todo.id,
            title    = todo.title,
            body     = todo.body,
            status   = todo.status match {
              case State.TODO        => JsState.TODO
              case State.IN_PROGRESS => JsState.IN_PROGRESS
              case State.DONE        => JsState.DONE
            },
            category = JsCategory(
              name  = todo.category.name,
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
  implicit lazy val responseWrites: Writes[JsResponse]        = Json.writes
  implicit lazy val toDoWrites:     Writes[JsToDo]     = Json.writes
  implicit lazy val stateWrites:    Writes[JsState]    = Writes {
    case JsState.TODO        => JsString("TODO")
    case JsState.IN_PROGRESS => JsString("IN_PROGRESS")
    case JsState.DONE        => JsString("DONE")
  }
  implicit lazy val categoryWrites: Writes[JsCategory] = Json.writes

  case class JsResponse(
    list: Seq[JsToDo]
  ) {}

  case class JsToDo(
                          id:       Long,
                          title:    String,
                          body:     String,
                          status:   JsState,
                          category: JsCategory,
  )
  sealed trait JsState
  object JsState {
    case object TODO        extends JsState
    case object IN_PROGRESS extends JsState
    case object DONE        extends JsState
  }

  case class JsCategory(
    name:  String,
    color: String,
  )
}
