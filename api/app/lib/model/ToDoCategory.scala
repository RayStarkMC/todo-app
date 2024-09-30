package lib.model

import ixias.model._
import ixias.util.EnumStatus
import lib.model.ToDoCategory.Color

import java.time.LocalDateTime

case class ToDoCategory(
  id:        Option[ToDoCategory.Id],
  updatedAt: LocalDateTime = NOW,
  createdAt: LocalDateTime = NOW,
  name:      String,
  slug:      String,
  color:     Color
) extends EntityModel[ToDoCategory.Id]

object ToDoCategory {
  type Id = Long @@ ToDoCategory
  val Id = the[Identity[Id]]

  abstract class Color(val code: Short, val rgb: String) extends EnumStatus
  object Color extends EnumStatus.Of[Color] {
    case object Type1 extends Color(code = 1, rgb = "#ffe4b5")
    case object Type2 extends Color(code = 2, rgb = "#00ffff")
    case object Type3 extends Color(code = 3, rgb = "#7fffd4")
  }
}
