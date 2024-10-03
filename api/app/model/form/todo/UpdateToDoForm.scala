package model.form.todo

import play.api.data.Form
import play.api.data.Forms.{ignored, longNumber, mapping, nonEmptyText, text}

case class UpdateToDoForm(title: String, body: String, category: Long, status: Long)

object UpdateToDoForm {
  val form: Form[UpdateToDoForm] = Form(
    mapping(
      "title"    -> nonEmptyText,
      "body"     -> text,
      "category" -> longNumber(min = 1),
      "status"   -> longNumber(min = 1)
    )(UpdateToDoForm.apply)(UpdateToDoForm.unapply)
  )
}
