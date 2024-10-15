package lib.usecase.query.api

import controllers.api.todo.ApiGetAllToDosController._
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.ToDo.Status
import lib.persistence.table.{ ToDoCategoryTable, ToDoTable }

import javax.inject.{ Inject, Named, Singleton }
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class GetAllToDosQuery @Inject() (
  @Named("slave") slave: Database
)(
  implicit ec:           ExecutionContext
) {
  def run(): Future[JsResponse] = {
    val selectAllToDo =
      for {
        toDo         <- ToDoTable.query
        toDoCategory <- ToDoCategoryTable.query if toDo.categoryId === toDoCategory.id
      } yield {
        (toDo.id, toDo.title, toDo.body, toDo.state, toDoCategory.name, toDoCategory.color)
      }

    val dbio = selectAllToDo.result

    slave.run(dbio).map { records =>
      JsResponse(
        list = records.map { record =>
          JsToDo(
            id       = record._1,
            title    = record._2,
            body     = record._3,
            status   = record._4 match {
              case Status.TO_DO       => JsState.TODO
              case Status.IN_PROGRESS => JsState.IN_PROGRESS
              case Status.DONE        => JsState.DONE
            },
            category = JsCategory(
              name  = record._5,
              color = record._6.rgb
            )
          )
        }
      )
    }
  }
}
