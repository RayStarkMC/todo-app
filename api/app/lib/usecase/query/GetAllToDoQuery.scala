package lib.usecase.query

import javax.inject.{ Inject, Named, Singleton }
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.{ ToDo, ToDoCategory }
import lib.persistence.table.{ ToDoCategoryTable, ToDoTable }
import lib.usecase.query.GetAllToDoQuery._

import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class GetAllToDoQuery @Inject() (
  @Named("slave") slave: Database,
)(
  implicit val ec:       ExecutionContext
) {
  def run(): Future[Output] = {
    val dbio = for {
      entries    <- selectAllToDo.result
      categories <- selectAllCategory.result
    } yield {
      Output(
        entries    = entries.map { record =>
          Entry(
            id       = record._1,
            title    = record._2,
            body     = record._3,
            state    = record._4,
            category = record._5,
            color    = record._6,
          )
        },
        categories = categories.map { record =>
          Category.apply(
            id   = record._1,
            name = record._2,
          )
        }
      )
    }

    slave.run(dbio.transactionally)
  }
}

object GetAllToDoQuery {
  case class Output(
    entries:    Seq[Entry],
    categories: Seq[Category]
  )

  case class Entry(
    id:       ToDo.Id,
    title:    String,
    body:     String,
    state:    ToDo.Status,
    category: String,
    color:    ToDoCategory.Color
  )

  case class Category(
    id:   ToDoCategory.Id,
    name: String,
  )

  private val selectAllToDo     =
    for {
      toDo         <- ToDoTable.query
      toDoCategory <- ToDoCategoryTable.query if toDo.categoryId === toDoCategory.id
    } yield {
      (toDo.id, toDo.title, toDo.body, toDo.state, toDoCategory.name, toDoCategory.color)
    }
  private val selectAllCategory = ToDoCategoryTable.query.map { table =>
    (table.id, table.name)
  }
}
