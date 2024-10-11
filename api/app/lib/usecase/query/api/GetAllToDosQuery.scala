package lib.usecase.query.api

import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.ToDo.Status
import lib.persistence.table.{ToDoCategoryTable, ToDoTable}
import lib.usecase.query.api.GetAllToDosQuery._

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetAllToDosQuery @Inject() (
  @Named("slave") slave: Database
)(
  implicit ec:           ExecutionContext
) {
  def run(input: Input): Future[Output] = {
    val selectAllToDo =
      for {
        toDo         <- ToDoTable.query
        toDoCategory <- ToDoCategoryTable.query if toDo.categoryId === toDoCategory.id
      } yield {
        (toDo.id, toDo.title, toDo.body, toDo.state, toDoCategory.name, toDoCategory.color)
      }

    val dbio = selectAllToDo.result

    slave.run(dbio).map { records =>
      Output(
        list = records.map { record =>
          ToDo(
            id       = record._1,
            title    = record._2,
            body     = record._3,
            status   = record._4 match {
              case Status.TO_DO       => State.TODO
              case Status.IN_PROGRESS => State.IN_PROGRESS
              case Status.DONE        => State.DONE
            },
            category = Category(
              name  = record._5,
              color = record._6.rgb
            )
          )
        }
      )
    }
  }
}

object GetAllToDosQuery {
  case class Input()
  case class Output(
    list: Seq[ToDo],
  )

  case class ToDo(
    id:       Long,
    title:    String,
    body:     String,
    status:   State,
    category: Category,
  )
  sealed trait State
  object State {
    case object TODO        extends State
    case object IN_PROGRESS extends State
    case object DONE        extends State
  }

  case class Category(
    name:  String,
    color: String,
  )
}
