package controllers.todo

import lib.AsyncMessagesInjectedController
import play.api.mvc.{Action, AnyContent}

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class UpdateToDoPageController @Inject() extends AsyncMessagesInjectedController {
  def action(id: Int): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok.apply(s"Hello! $id"))
  }
}
