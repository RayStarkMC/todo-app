package controllers.api.query.category

import lib.model.ToDoCategory
import play.api.libs.json.{Json, Writes}

object ApIGetAllCategoryOptionsModel {
  case class JsResponse(list: Seq[JsCategoryOption])
  case class JsCategoryOption(id: ToDoCategory.Id, name: String)

  implicit lazy val toDoCategoryIdWrites: Writes[ToDoCategory.Id] = implicitly[Writes[Long]].contramap(_.toLong)
  implicit lazy val jsCategoryOptionWrites: Writes[JsCategoryOption] = Json.writes
  implicit lazy val jsResponseWrites: Writes[JsResponse] = Json.writes
}
