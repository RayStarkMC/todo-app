package controllers.todo

import lib.AsyncMessagesInjectedController
import lib.model.{ ToDo, ToDoCategory }
import lib.usecase.command.CreateToDoCommand
import lib.usecase.query.ViewAllToDoPageQuery
import model.{ CreateToDoForm, ToDoItem, ToDoStatus, ViewValueToDo }
import play.api.mvc.{ Action, AnyContent }

import javax.inject.{ Inject, Singleton }

@Singleton
class CreateToDoController @Inject() (
  command: CreateToDoCommand,
  query:   ViewAllToDoPageQuery,
) extends AsyncMessagesInjectedController {
  def action(): Action[AnyContent] =
    Action.async { implicit req =>
      CreateToDoForm.form.bindFromRequest().fold(
        rawForm => {
          for {
            output <- query.run()
          } yield {
            val vv = ViewValueToDo(
              items                = output.entries.map { entry =>
                ToDoItem(
                  id       = entry.id,
                  title    = entry.title,
                  body     = entry.body,
                  state    = entry.state match {
                    case ToDo.Status.TO_DO       => ToDoStatus.ToDo
                    case ToDo.Status.IN_PROGRESS => ToDoStatus.InProgress
                    case ToDo.Status.DONE        => ToDoStatus.Done
                  },
                  category = entry.category,
                  color    = entry.color.rgb
                )
              },
              categoryOptions      = output.categories.map { category =>
                category.id.toString -> category.name
              },
              createToDoForm       = rawForm,
              showCreateToDoDialog = true,
            )
            BadRequest(views.html.ViewAllToDo(vv))
          }
        },
        createToDoForm => {
          val input = CreateToDoCommand.Input(
            title      = createToDoForm.title,
            body       = createToDoForm.body,
            categoryId = ToDoCategory.Id(createToDoForm.category)
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
