package model

import model.common.ViewValueCommon
import model.form.todo.UpdateToDoForm
import play.api.data.Form

case class ViewValueUpdateToDo(
  vvc:             ViewValueCommon      = ViewValueCommon(
    title  = "ToDo編集",
    cssSrc = Seq("main.css"),
    jsSrc  = Seq("main.js")
  ),
  updateToDoForm:  Form[UpdateToDoForm] = UpdateToDoForm.form,
  id:              Long,
  categoryOptions: Seq[(String, String)],
  statusOptions:   Seq[(String, String)]
)
