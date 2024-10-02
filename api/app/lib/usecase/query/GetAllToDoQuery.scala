package lib.usecase.query

import javax.inject.{Inject, Named, Singleton}
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.ToDo.Status
import lib.model.ToDoCategory
import lib.persistence.table.{ToDoCategoryTable, ToDoTable}
import lib.usecase.query.GetAllToDoQuery._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetAllToDoQuery @Inject() (
  @Named("slave") slave: Database,
)(
  implicit val ec:       ExecutionContext
) {
  def run(): Future[Output] = {
    slave.run(dbio).map { records =>
      Output(
        entries = records.map { record =>
          Entry(
            title    = record._1,
            body     = record._2,
            state    = record._3,
            category = record._4,
            color    = record._5
          )
        },
        categories = Seq.empty
      )
    }
  }
}

object GetAllToDoQuery {
  case class Output(
    entries:         Seq[Entry],
    categories: Seq[Category]
  )

  case class Entry(
    title:    String,
    body:     String,
    state:    Status,
    category: String,
    color:    ToDoCategory.Color
  )

  case class Category(
    id:   ToDoCategory.Id,
    name: String,
  )

  private val query =
    for {
      toDo         <- ToDoTable.query
      toDoCategory <- ToDoCategoryTable.query if toDo.categoryId === toDoCategory.id
    } yield {
      (toDo.title, toDo.body, toDo.state, toDoCategory.name, toDoCategory.color)
    }
  private val dbio = query.result
}
