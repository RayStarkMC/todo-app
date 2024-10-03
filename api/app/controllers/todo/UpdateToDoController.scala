package controllers.todo

import lib.AsyncMessagesInjectedController
import lib.model.{ ToDo, ToDoCategory }
import lib.usecase.command.UpdateToDoCommand
import lib.usecase.command.UpdateToDoCommand.Input
import lib.usecase.query.GetAllCategoryOptionsQuery
import model.ViewValueUpdateToDo
import model.form.todo.UpdateToDoForm
import play.api.mvc.{ Action, AnyContent, Result }

import javax.inject.{ Inject, Singleton }

@Singleton
class UpdateToDoController @Inject() (
  command: UpdateToDoCommand,
  query:   GetAllCategoryOptionsQuery
) extends AsyncMessagesInjectedController {
  def action(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val form = UpdateToDoForm.form.bindFromRequest()
    form.fold(
      invalidForm => {
        for {
          output <- query.run()
        } yield {
          val vv = ViewValueUpdateToDo(
            updateToDoForm  = invalidForm,
            id              = id,
            categoryOptions = output.options.map { option =>
              option.id.toString -> option.name
            },
            statusOptions   = ToDo.Status.values.map { status =>
              status.code.toString -> (status match {
                case ToDo.Status.TO_DO       => "ToDo"
                case ToDo.Status.IN_PROGRESS => "InProgress"
                case ToDo.Status.DONE        => "Done"
              })
            }
          )

          BadRequest(views.html.UpdateToDo(vv))
        }
      },
      validForm => {
        val input = Input(
          id         = ToDo.Id(id),
          title      = validForm.title,
          body       = validForm.body,
          categoryId = ToDoCategory.Id(validForm.category),
          state      = ToDo.Status.apply(validForm.status)
        )
        for {
          output <- command.run(input)
        } yield {
          output.fold[Result](NotFound) { _ => //TODO エラーページの用意
            Redirect(controllers.todo.routes.ViewAllTodosController.action())
          }
        }
      }
    )
  }
}
