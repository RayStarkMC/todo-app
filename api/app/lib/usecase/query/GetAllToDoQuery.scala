package lib.usecase.query

import javax.inject.{Inject, Named, Singleton}
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.ToDo.Status
import lib.persistence.table.ToDoTable
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
      todo <- ToDoTable.query
    } yield {
      (todo.title, todo.body, todo.state)
    }

  private val dbio = query.result

  def all(): Future[Seq[Entry]] = {
    slave.run(dbio).map(
      _.map { record =>
        Entry(
          title = record._1,
          body  = record._2,
          state = record._3
        )
      }
    )
  }
}

object GetAllToDoQuery {
  case class Entry(
    title: String,
    body:  String,
    state: Status
  )
}
