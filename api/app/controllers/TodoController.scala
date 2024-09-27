package controllers

import model._
import model.common.ViewValueCommon
import play.api.mvc.{ Action, AnyContent, BaseController, ControllerComponents }

import javax.inject.{ Inject, Singleton }

@Singleton
class TodoController @Inject() (
  val controllerComponents: ControllerComponents,
) extends BaseController {
  def todo(): Action[AnyContent] = Action { implicit req =>
    val vv = ViewValueToDo(
      vvc   = ViewValueCommon(
        title  = "ToDo",
        cssSrc = Seq("main.css", "todo.css"),
        jsSrc  = Seq("main.js")
      ),
      items = Seq(
        ViewValueToDoItem(
          title         = "task todo",
          body          = "body",
          state         = ViewValueState.ToDo,
          category      = "category1",
        ),
        ViewValueToDoItem(
          title         = "task in progress",
          body          = "body",
          state         = ViewValueState.InProgress,
          category      = "category2",
        ),
        ViewValueToDoItem(
          title         = "task done",
          body          = "body",
          state         = ViewValueState.Done,
          category      = "category3",
        ),
      )
    )
    Ok(views.html.ToDo(vv))
  }
}
