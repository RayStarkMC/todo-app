package lib.persistence.table

import ixias.slick.jdbc.MySQLProfile.api.{ localDateTimeColumnType => _, _ }
import lib.model.ToDoCategory
import slick.lifted.ProvenShape

import java.time.LocalDateTime

class ToDoCategoryTable(tag: Tag) extends Table[ToDoCategory](tag, "to_do_category") {
  def id        = column[ToDoCategory.Id]("id", UInt64, O.PrimaryKey, O.AutoInc)
  def updatedAt = column[LocalDateTime]("updated_at", TsCurrent)
  def createdAt = column[LocalDateTime]("created_at", Ts)
  def name      = column[String]("name", Utf8Char255)
  def slug      = column[String]("slug", Utf8Char64)
  def color     = column[ToDoCategory.Color]("color", UInt8)

  override def * : ProvenShape[ToDoCategory] = {
    (
      id.?,
      updatedAt,
      createdAt,
      name,
      slug,
      color
    ) <> (
      (ToDoCategory.apply _).tupled,
      (ToDoCategory.unapply _).andThen(_.map(_.copy(_2 = LocalDateTime.now())))
    )
  }
}

object ToDoCategoryTable {
  val query = TableQuery[ToDoCategoryTable]
}
