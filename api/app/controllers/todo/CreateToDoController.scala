package controllers.todo

import model.AddForm
import play.api.mvc.{ AbstractController, Action, AnyContent, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class CreateToDoController @Inject() (
  controllerComponents: ControllerComponents
)(implicit ec:          ExecutionContext) extends AbstractController(controllerComponents) {
  def action(): Action[AnyContent] =
    Action.async { implicit req =>
      AddForm.form.bindFromRequest().fold(
        hasErrors = rawForm => Future.successful(BadRequest),
        success   = addForm => {
          println(addForm) //TODO ToDo追加コマンド実装後に修正
          for {
            a <- Future.successful(Redirect(routes.ViewAllTodosController.action()))
          } yield {
            a
          }
        }
      )
    }
}
