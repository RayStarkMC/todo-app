package model

import model.common.ViewValueCommon

case class ViewValueToDo(
  vvc:   ViewValueCommon,
  items: Seq[ViewValueToDoItem]
)

case class ViewValueToDoItem(
  title:    String,
  body:     String,
  state:    ViewValueState,
  category: String
)

sealed trait ViewValueState
object ViewValueState {
  case object ToDo       extends ViewValueState
  case object InProgress extends ViewValueState
  case object Done       extends ViewValueState
}
