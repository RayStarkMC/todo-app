package controllers.todo

import cats.data.OptionT
import lib.AsyncMessagesInjectedController
import lib.model.ToDo
import lib.usecase.query.UpdateToDoPageQuery
import lib.usecase.query.UpdateToDoPageQuery.Input
import model.ViewValueUpdateToDo
import model.form.todo.UpdateToDoForm
import play.api.mvc.{Action, AnyContent, Result}

import javax.inject.{Inject, Singleton}

@Singleton
class UpdateToDoPageController @Inject() (
  query: UpdateToDoPageQuery
) extends AsyncMessagesInjectedController {
  def action(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val input = Input(id = ToDo.Id(id))

    val optionT =
      for {
        output <- OptionT(query.run(input))
        toDo    = output.toDo
        options = output.options
        mapping = UpdateToDoForm.form.mapping
      } yield {
        ViewValueUpdateToDo(
          id              = toDo.id,
          updateToDoForm  = UpdateToDoForm.form.bind(
            mapping.unbind(
              UpdateToDoForm(
                title    = toDo.title,
                body     = toDo.body,
                category = toDo.categoryId,
                status   = toDo.state.code
              )
            )
          ),
          categoryOptions = options.map { option =>
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
      }
    optionT.fold[Result](
      NotFound
    ) { vv =>
      Ok(views.html.UpdateToDo(vv))
    }
  }
}
