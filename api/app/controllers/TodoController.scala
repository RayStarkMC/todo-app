package controllers

import lib.model.ToDo
import lib.usecase.query.GetAllToDoQuery
import model._
import model.common.ViewValueCommon
import play.api.mvc.{ Action, AnyContent, BaseController, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.ExecutionContext

@Singleton
class TodoController @Inject() (
  val controllerComponents: ControllerComponents,
  val query:                GetAllToDoQuery,
)(
  implicit val ec:          ExecutionContext
) extends BaseController {
  private val vvc = ViewValueCommon(
    title  = "ToDo",
    cssSrc = Seq("main.css", "todo.css"),
    jsSrc  = Seq("main.js")
  )

  def todo(): Action[AnyContent] =
    Action.async { implicit req =>
      for {
        result <- query.run()
        vvToDo  = ViewValueToDo(
          vvc   = vvc,
          items = result.map { entry =>
            ViewValueToDoItem(
              title    = entry.title,
              body     = entry.body,
              state    = entry.state match {
                case ToDo.Status.TO_DO       => ViewValueState.ToDo
                case ToDo.Status.IN_PROGRESS => ViewValueState.InProgress
                case ToDo.Status.DONE        => ViewValueState.Done
              },
              category = "category1", //TODO カテゴリ関連の定義を行ったら修正
              color = "#ffe4b5",
              //    #00ffff
              //    #7fffd4
            )
          }
        )
      } yield {
        Ok(views.html.ToDo(vvToDo))
      }
    }
}
