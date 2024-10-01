package controllers.todo

import lib.model.ToDo
import lib.usecase.query.GetAllToDoQuery
import model._
import model.common.ViewValueCommon
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{ Action, AnyContent, MessagesAbstractController, MessagesControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.ExecutionContext

@Singleton
class ViewAllTodosController @Inject() (
  val components:  MessagesControllerComponents,
  val query:       GetAllToDoQuery,
)(
  implicit val ec: ExecutionContext
) extends MessagesAbstractController(components) {

  private val vvc = ViewValueCommon(
    title  = "ToDo",
    cssSrc = Seq("main.css", "todo.css"),
    jsSrc  = Seq("main.js")
  )

  def run(): Action[AnyContent] =
    Action.async { implicit req =>
      for {
        result <- query.run()
        vvToDo  = ViewValueToDo(
          vvc             = vvc,
          items           = result.map { entry =>
            ViewValueToDoItem(
              title    = entry.title,
              body     = entry.body,
              state    = entry.state match {
                case ToDo.Status.TO_DO       => ViewValueState.ToDo
                case ToDo.Status.IN_PROGRESS => ViewValueState.InProgress
                case ToDo.Status.DONE        => ViewValueState.Done
              },
              category = entry.category,
              color    = entry.color.rgb
            )
          },
          addForm         = Form(
            mapping(
              "title"    -> text,
              "body"     -> text,
              "category" -> shortNumber
            )(AddForm.apply)(AddForm.unapply)
          ),
          categoryOptions = Seq( // TODO クエリ結果から動的に生成する
            "1" -> "category1",
            "2" -> "category2",
            "3" -> "category3",
          )
        )
      } yield {
        Ok(views.html.ToDo(vvToDo))
      }
    }
}
