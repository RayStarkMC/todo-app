package model.form.todo

import play.api.data.Form
import play.api.data.Forms._

case class UpdateToDoForm(title: String, body: String, category: Long, status: Short)

object UpdateToDoForm {
  val form: Form[UpdateToDoForm] = Form(
    mapping(
      //TODO 使用可能な文字の制限を追加
      // https://github.com/RayStarkMC/todo-app/pull/3#discussion_r1787746750
      "title"    -> nonEmptyText,
      "body"     -> text,
      "category" -> longNumber(min = 1),
      "status"   -> shortNumber(min = 0)
    )(UpdateToDoForm.apply)(UpdateToDoForm.unapply)
  )
}
