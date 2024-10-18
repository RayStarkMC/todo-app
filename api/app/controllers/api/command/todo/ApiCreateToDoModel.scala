package controllers.api.command.todo

import lib.model.ToDoCategory
import lib.model.ToDoCategory.Id
import play.api.libs.json.{Json, Reads}

object ApiCreateToDoModel {
  implicit lazy val idReads: Reads[Id] = implicitly[Reads[Long]].map(ToDoCategory.Id(_))
  implicit lazy val JsRequestReads: Reads[JsRequest] = Json.reads

  case class JsRequest(
    title:    String,
    body:     String,
    category: ToDoCategory.Id
  )
}
