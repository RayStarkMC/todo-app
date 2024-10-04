package model

import model.common.ViewValueCommon
import play.api.data.Form
import play.api.data.Forms._

case class ViewValueToDo(
  vvc:                  ViewValueCommon = ViewValueCommon(
    title  = "ToDo",
    cssSrc = Seq("main.css", "todo.css"),
    jsSrc  = Seq("main.js")
  ),
  items:                Seq[ViewValueToDoItem],
  categoryOptions:      Seq[(String, String)],
  createToDoForm:       Form[CreateToDoForm],
  showCreateToDoDialog: Boolean         = false
)

case class ViewValueToDoItem(
  title:    String,
  body:     String,
  state:    ViewValueState,
  category: String,
  color:    String
)

sealed trait ViewValueState
object ViewValueState {
  case object ToDo       extends ViewValueState
  case object InProgress extends ViewValueState
  case object Done       extends ViewValueState
}

case class CreateToDoForm(title: String, body: String, category: Long)
object CreateToDoForm {
  val form: Form[CreateToDoForm] = Form(
    mapping(
      "title"    -> nonEmptyText,
      "body"     -> text,
      "category" -> longNumber(min = 1)
    )(CreateToDoForm.apply)(CreateToDoForm.unapply)
  )
}
