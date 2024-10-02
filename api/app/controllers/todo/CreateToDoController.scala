package controllers.todo

import lib.model.{ToDo, ToDoCategory}
import lib.usecase.command.CreateToDoCommand
import lib.usecase.query.GetAllToDoQuery
import model.{AddForm, ViewValueState, ViewValueToDo, ViewValueToDoItem}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class CreateToDoController @Inject() (
  controllerComponents: MessagesControllerComponents,
  command:              CreateToDoCommand,
  query:                GetAllToDoQuery,
) extends MessagesAbstractController(controllerComponents) {
  def action(): Action[AnyContent] =
    Action.async { implicit req =>
      AddForm.form.bindFromRequest().fold(
        rawForm => {
          for {
            output <- query.run()
          } yield {
            val vv = ViewValueToDo(
              items = output.entries.map { entry =>
                ViewValueToDoItem(
                  title = entry.title,
                  body = entry.body,
                  state    = entry.state match {
                    case ToDo.Status.TO_DO       => ViewValueState.ToDo
                    case ToDo.Status.IN_PROGRESS => ViewValueState.InProgress
                    case ToDo.Status.DONE        => ViewValueState.Done
                  },
                  category = entry.category,
                  color = entry.color.rgb
                )
              },
              categoryOptions = output.categories.map { category =>
                category.id.toString -> category.name
              },
              addForm = rawForm,
              showCreateToDoDialog = true,
            )
            BadRequest(views.html.ToDo(vv))
          }
        }, createToDoForm => {
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
