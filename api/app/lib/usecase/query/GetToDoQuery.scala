package lib.usecase.query

import cats.data.OptionT

import javax.inject._
import scala.concurrent.{ ExecutionContext, Future }
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.{ ToDo, ToDoCategory }
import lib.persistence.table.ToDoTable
import lib.usecase.query.GetToDoQuery.{ Input, Output }

@Singleton
class GetToDoQuery @Inject() (
  @Named("slave") slave: Database,
)(
  implicit val ec:       ExecutionContext
) {
  def run(input: Input): Future[Option[Output]] = {
    val selectToDoById = ToDoTable.query
      .filter(_.id === input.id)
      .map(t => (t.id, t.title, t.body, t.state, t.categoryId))

    val dbio = selectToDoById.result.headOption

    val optionT = for {
      record <- OptionT(slave.run(dbio))
    } yield {
      Output(
        id         = record._1,
        title      = record._2,
        body       = record._3,
        state      = record._4,
        categoryId = record._5,
      )
    }

    optionT.value
  }
}

object GetToDoQuery {
  case class Input(id: ToDo.Id)
  case class Output(
    id:         ToDo.Id,
    title:      String,
    body:       String,
    state:      ToDo.Status,
    categoryId: ToDoCategory.Id
  )
}
