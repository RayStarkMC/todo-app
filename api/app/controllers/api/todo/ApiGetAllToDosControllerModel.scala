package controllers.api.todo

import ixias.util.json.JsonEnvWrites
import lib.model.ToDo
import play.api.libs.json.{ Json, Writes }

object ApiGetAllToDosControllerModel extends JsonEnvWrites {
  implicit lazy val responseWrites: Writes[JsResponse] = Json.writes
  implicit lazy val toDoWrites:     Writes[JsToDo]     = Json.writes
  implicit lazy val categoryWrites: Writes[JsCategory] = Json.writes
  implicit lazy val toDoIdWrites: Writes[ToDo.Id] = implicitly[Writes[Long]].contramap(_.toLong)

  case class JsResponse(
    list: Seq[JsToDo]
  ) {}

  case class JsToDo(
    id:       ToDo.Id,
    title:    String,
    body:     String,
    status:   ToDo.Status,
    category: JsCategory,
  )

  case class JsCategory(
    name:  String,
    color: String,
  )
}
