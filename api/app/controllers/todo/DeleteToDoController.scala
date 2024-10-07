package controllers.todo

import cats.data.OptionT
import lib.AsyncMessagesInjectedController
import lib.model.ToDo
import lib.usecase.command.DeleteToDoCommand
import lib.usecase.command.DeleteToDoCommand.Input
import play.api.mvc.{Action, AnyContent, Result}

import javax.inject.{Inject, Singleton}

@Singleton
class DeleteToDoController @Inject() (
  command: DeleteToDoCommand,
) extends AsyncMessagesInjectedController {
  def action(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val input = Input(ToDo.Id(id))

    val optionT = for {
      _ <- OptionT(command.run(input))
    } yield {
      ()
    }

    optionT.fold[Result](NotFound) { _ =>
      Redirect(controllers.todo.routes.ViewAllTodosController.action())
    }
  }
}
