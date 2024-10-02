package model

import model.common.ViewValueCommon
import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, shortNumber, text}

case class ViewValueToDo(
  vvc:   ViewValueCommon,
  items: Seq[ViewValueToDoItem],
  categoryOptions: Seq[(String, String)],
  addForm: Form[AddForm]
)

case class ViewValueToDoItem(
  title:    String,
  body:     String,
  state:    ViewValueState,
  category: String,
  color: String
)

sealed trait ViewValueState
object ViewValueState {
  case object ToDo       extends ViewValueState
  case object InProgress extends ViewValueState
  case object Done       extends ViewValueState
}

case class AddForm(title: String, body: String, category: Long)
object AddForm {
  val form: Form[AddForm] = Form(
    mapping(
      "title"    -> text,
      "body"     -> text,
      "category" -> longNumber
    )(AddForm.apply)(AddForm.unapply)
  )
}