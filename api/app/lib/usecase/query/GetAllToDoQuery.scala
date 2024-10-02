package lib.usecase.query

import javax.inject.{Inject, Named, Singleton}
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.ToDo.Status
import lib.model.ToDoCategory
import lib.persistence.table.{ToDoCategoryTable, ToDoTable}
import lib.usecase.query.GetAllToDoQuery.Entry

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetAllToDoQuery @Inject() (
  @Named("slave") slave: Database,
)(
  implicit val ec:       ExecutionContext
) {
  private val query =
    for {
      toDo <- ToDoTable.query
      toDoCategory <- ToDoCategoryTable.query if toDo.categoryId === toDoCategory.id
    } yield {
      (toDo.title, toDo.body, toDo.state, toDoCategory.name, toDoCategory.color)
    }

  private val dbio = query.result

  def run(): Future[Seq[Entry]] = {
    slave.run(dbio).map(
      _.map { record =>
        Entry(
          title = record._1,
          body  = record._2,
          state = record._3,
          category = record._4,
          color = record._5
        )
      }
    )
  }
}

object GetAllToDoQuery {
  case class Entry(
    title: String,
    body:  String,
    state: Status,
    category: String,
    color: ToDoCategory.Color
  )
}
