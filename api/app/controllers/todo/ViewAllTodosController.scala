package controllers.todo

import lib.model.ToDo
import lib.usecase.query.GetAllToDoQuery
import model._
import play.api.mvc.{ Action, AnyContent, MessagesAbstractController, MessagesControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.ExecutionContext

@Singleton
class ViewAllTodosController @Inject() (
  components:      MessagesControllerComponents,
  query:           GetAllToDoQuery,
)(
  implicit val ec: ExecutionContext
) extends MessagesAbstractController(components) {
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
        Ok(views.html.ToDo(vvToDo))
      }
    }
}
