package controllers.todo

import lib.model.ToDoCategory
import lib.usecase.command.CreateToDoCommand
import model.AddForm
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CreateToDoController @Inject() (
  controllerComponents: ControllerComponents,
  command:              CreateToDoCommand,
)(implicit ec:          ExecutionContext) extends AbstractController(controllerComponents) {
  def action(): Action[AnyContent] =
    Action.async { implicit req =>
      AddForm.form.bindFromRequest().fold(
        rawForm => Future.successful(BadRequest),
        addForm => {
          val input = CreateToDoCommand.Input(
            title      = addForm.title,
            body       = addForm.body,
            categoryId = ToDoCategory.Id(addForm.category)
          )
          for {
            _ <- command.run(input)
          } yield {
            Redirect(routes.ViewAllTodosController.action())
          }
        }
      )
    }
}
