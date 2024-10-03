package model

import model.common.ViewValueCommon
import play.api.data.Form
import play.api.data.Forms._

case class ViewValueToDo(
  vvc:                  ViewValueCommon      = ViewValueCommon(
    title  = "ToDo",
    cssSrc = Seq("main.css", "todo.css"),
    jsSrc  = Seq("main.js")
  ),
  items:                Seq[ToDoItem],
  categoryOptions:      Seq[(String, String)],
  createToDoForm:       Form[CreateToDoForm] = CreateToDoForm.form,
  showCreateToDoDialog: Boolean              = false
)

case class ToDoItem(
  title:    String,
  body:     String,
  state:    ToDoStatus,
  category: String,
  color:    String
)

sealed trait ToDoStatus
object ToDoStatus {
  case object ToDo       extends ToDoStatus
  case object InProgress extends ToDoStatus
  case object Done       extends ToDoStatus
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
