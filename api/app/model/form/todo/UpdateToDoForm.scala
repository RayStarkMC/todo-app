package model.form.todo

import play.api.data.Form
import play.api.data.Forms._

case class UpdateToDoForm(title: String, body: String, category: Long, status: Short)

object UpdateToDoForm {
  val form: Form[UpdateToDoForm] = Form(
    mapping(
      "title"    -> nonEmptyText,
      "body"     -> text,
      "category" -> longNumber(min = 1),
      "status"   -> shortNumber(min = 0)
    )(UpdateToDoForm.apply)(UpdateToDoForm.unapply)
  )
}
