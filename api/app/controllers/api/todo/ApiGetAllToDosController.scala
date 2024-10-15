package controllers.api.todo

import controllers.api.todo.ApiGetAllToDosControllerModel._
import lib.AsyncBaseController
import lib.usecase.query.api.GetAllToDosQuery
import play.api.libs.json._
import play.api.mvc.{ Action, AnyContent }

import javax.inject.{ Inject, Singleton }

@Singleton
class ApiGetAllToDosController @Inject() (
  query: GetAllToDosQuery,
) extends AsyncBaseController {

  def action(): Action[AnyContent] = Action.async { implicit req =>
    query
      .run()
      .map(Json.toJson(_))
      .map(Ok(_))
  }
}
