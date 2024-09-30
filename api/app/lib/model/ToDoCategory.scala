package lib.model

import ixias.model._

import java.time.LocalDateTime

case class ToDoCategory(
  id:        Option[ToDoCategory.Id],
  updatedAt: LocalDateTime = NOW,
  createdAt: LocalDateTime = NOW,
  name:      String,
  slug:      String,
  color:     Short
) extends EntityModel[ToDoCategory.Id]

object ToDoCategory {
  type Id = Long @@ ToDoCategory
  val Id = the[Identity[Id]]
}
