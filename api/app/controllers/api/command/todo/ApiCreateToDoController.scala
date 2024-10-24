package controllers.api.command.todo

import controllers.api.command.todo.ApiCreateToDoModel._
import lib.AsyncBaseController
import lib.usecase.command.CreateToDoCommand
import lib.usecase.command.CreateToDoCommand.Input
import play.api.libs.json._
import play.api.mvc.Action

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class ApiCreateToDoController @Inject() (
  command: CreateToDoCommand
) extends AsyncBaseController {
  def action(): Action[JsValue] = Action.async(parse.json) { implicit req =>
    req.body.validate[JsRequest]
      .fold(
        ifInvalid => Future.successful(BadRequest),
        ifValid => {
          val input = Input(
            title      = ifValid.title,
            body       = ifValid.body,
            categoryId = ifValid.category,
          )
          for {
            _ <- command.run(input)
          } yield {
            Ok
          }
        }
      )
  }
}
