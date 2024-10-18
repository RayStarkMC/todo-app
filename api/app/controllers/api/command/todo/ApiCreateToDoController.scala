package controllers.api.command.todo

import controllers.api.command.todo.ApiCreateToDoModel._
import lib.AsyncBaseController
import play.api.libs.json.JsValue
import play.api.mvc.Action

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class ApiCreateToDoController @Inject() (
) extends AsyncBaseController {
  def action(): Action[JsValue] = Action.async(parse.json) { implicit req =>
    req.body.validate[JsRequest]
      .fold(
        ifInvalid => Future.successful(BadRequest),
        ifValid => {
          println(ifValid)
          Future.successful(Ok)
        }
      )
  }
}
