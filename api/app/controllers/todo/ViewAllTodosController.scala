package controllers.todo

import lib.AsyncMessagesInjectedController
import lib.model.ToDo
import lib.usecase.query.ViewAllToDoPageQuery
import model._
import play.api.mvc.{ Action, AnyContent }

import javax.inject.{ Inject, Singleton }

@Singleton
class ViewAllTodosController @Inject() (
  query: ViewAllToDoPageQuery,
) extends AsyncMessagesInjectedController {
  def action(): Action[AnyContent] =
    Action.async { implicit req =>
      for {
        result <- query.run()
        vvToDo  = ViewValueToDo(
          items           = result.entries.map { entry =>
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
          categoryOptions = result.categories.map { category =>
            category.id.toString -> category.name
          }
        )
      } yield {
        Ok(views.html.ViewAllToDo(vvToDo))
      }
    }
}
