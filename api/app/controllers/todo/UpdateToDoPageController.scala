package controllers.todo

import lib.AsyncMessagesInjectedController
import lib.model.ToDo
import lib.usecase.query.GetAllCategoryOptionsQuery
import model.ViewValueUpdateToDo
import play.api.mvc.{ Action, AnyContent }

import javax.inject.{ Inject, Singleton }

@Singleton
class UpdateToDoPageController @Inject() (
  query: GetAllCategoryOptionsQuery
) extends AsyncMessagesInjectedController {
  def action(id: Long): Action[AnyContent] = Action.async { implicit request =>
    for {
      output <- query.run()
    } yield {
      val vv = ViewValueUpdateToDo(
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
      Ok(views.html.UpdateToDo(vv))
    }
  }
}
