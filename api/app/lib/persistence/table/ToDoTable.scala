package lib.persistence.table

import ixias.slick.jdbc.MySQLProfile.api.{ localDateTimeColumnType => _, _ }
import lib.model.{ ToDo, ToDoCategory }
import slick.lifted.ProvenShape

import java.time.LocalDateTime

class ToDoTable(tag: Tag) extends Table[ToDo](tag, "to_do") {
  def id         = column[ToDo.Id]("id", UInt64, O.PrimaryKey, O.AutoInc)
  def updatedAt  = column[LocalDateTime]("updated_at", TsCurrent)
  def createdAt  = column[LocalDateTime]("created_at", Ts)
  def categoryId = column[ToDoCategory.Id]("category_id", UInt64)
  def title      = column[String]("title", Utf8Char255)
  def body       = column[String]("body", Text)
  def state      = column[ToDo.Status]("state", UInt8)

  override def * : ProvenShape[ToDo] = {
    (
      id.?,
      updatedAt,
      createdAt,
      categoryId,
      title,
      body,
      state
    ) <> (
      (ToDo.apply _).tupled,
      (ToDo.unapply _).andThen(_.map(_.copy(_2 = LocalDateTime.now())))
    )
  }
}
