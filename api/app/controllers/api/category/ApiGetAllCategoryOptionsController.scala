package controllers.api.category

import controllers.api.category.ApIGetAllCategoryOptionsModel.{JsCategoryOption, JsResponse}
import lib.AsyncMessagesInjectedController
import lib.model.ToDoCategory
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent}

import scala.concurrent.Future

class ApiGetAllCategoryOptionsController extends AsyncMessagesInjectedController {
  def action(): Action[AnyContent] = Action.async { implicit req =>
    val model = JsResponse(
      list = Seq(
        JsCategoryOption(
          id = ToDoCategory.Id(11),
          name = "category1",
        ),
        JsCategoryOption(
          id = ToDoCategory.Id(22),
          name = "category2",
        ),
        JsCategoryOption(
          id = ToDoCategory.Id(33),
          name = "category3",
        )
      )
    )
    val json = Json.toJson(model)
    Future.successful(Ok(json))
  }
}
