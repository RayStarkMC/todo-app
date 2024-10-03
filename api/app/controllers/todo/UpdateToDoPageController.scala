package controllers.todo

import lib.AsyncMessagesInjectedController
import lib.model.ToDo
import model.ViewValueUpdateToDo
import play.api.mvc.{ Action, AnyContent }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class UpdateToDoPageController @Inject() extends AsyncMessagesInjectedController {
  def action(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val vv = ViewValueUpdateToDo(
      id              = id,
      categoryOptions = Seq( // TODO カテゴリ一覧取得実装後に修正
        "1" -> "category1",
        "2" -> "category2",
        "3" -> "category3",
      ),
      statusOptions   = ToDo.Status.values.map { status =>
        status.code.toString -> (status match {
          case ToDo.Status.TO_DO       => "ToDo"
          case ToDo.Status.IN_PROGRESS => "InProgress"
          case ToDo.Status.DONE        => "Done"
        })
      }
    )
    Future.successful(Ok(views.html.UpdateToDo(vv)))
  }
}
