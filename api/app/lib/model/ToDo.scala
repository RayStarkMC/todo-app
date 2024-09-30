package lib.model

import ixias.model.{ @@, EntityModel, Identity, NOW, the }
import ixias.util.EnumStatus

import java.time.LocalDateTime

case class ToDo(
  id:         Option[ToDo.Id],
  updatedAt:  LocalDateTime = NOW,
  createdAt:  LocalDateTime = NOW,
  categoryId: ToDoCategory.Id,
  title:      String,
  body:       String,
  state:      ToDo.Status
) extends EntityModel[ToDo.Id]

object ToDo {
  type Id = Long @@ ToDo
  val Id = the[Identity[Id]]

  sealed abstract class Status(val code: Short) extends EnumStatus
  object Status extends EnumStatus.Of[Status] {
    case object TO_DO       extends Status(code = 0)
    case object IN_PROGRESS extends Status(code = 1)
    case object DONE        extends Status(code = 2)
  }
}
