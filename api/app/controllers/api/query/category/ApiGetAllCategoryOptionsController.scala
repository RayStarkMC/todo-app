package controllers.api.query.category

import jakarta.inject.Singleton
import lib.AsyncBaseController
import lib.usecase.query.api.GetAllCategoryOptionsQuery
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent}

import javax.inject.Inject

@Singleton
class ApiGetAllCategoryOptionsController @Inject() (
  query: GetAllCategoryOptionsQuery,
) extends AsyncBaseController {
  def action(): Action[AnyContent] = Action.async { implicit req =>
    query
      .run()
      .map(Json.toJson(_))
      .map(Ok(_))
  }
}
