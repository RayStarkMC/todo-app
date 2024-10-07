package lib.usecase.query

import cats.data.OptionT

import javax.inject._
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.{ ToDo, ToDoCategory }
import lib.persistence.table.{ ToDoCategoryTable, ToDoTable }
import lib.usecase.query.UpdateToDoPageQuery.{ CategoryOptionDTO, Input, Output, ToDoDTO }

import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class UpdateToDoPageQuery @Inject() (
  @Named("slave") slave: Database,
)(implicit ec:           ExecutionContext) {
  def run(input: Input): Future[Option[Output]] = {
    val selectToDoById = ToDoTable.query
      .filter(_.id === input.id)
      .map(t => (t.id, t.title, t.body, t.state, t.categoryId))

    val selectCategoryOptionsQuery = {
      for {
        categoryTable <- ToDoCategoryTable.query
      } yield {
        (categoryTable.id, categoryTable.name)
      }
    }

    val dbio =
      for {
        optToDo                    <- selectToDoById.result.headOption
        optToDoWithCategoryOptions <- DBIO.sequenceOption(
          optToDo.map { c =>
            selectCategoryOptionsQuery.result.map((c, _))
          }
        )
      } yield {
        optToDoWithCategoryOptions
      }

    val optionT =
      for {
        records              <- OptionT.apply(slave.run(dbio.transactionally))
        todoRecord            = records._1
        categoryOptionRecords = records._2
      } yield {
        Output(
          toDo    = ToDoDTO(
            id         = todoRecord._1,
            title      = todoRecord._2,
            body       = todoRecord._3,
            state      = todoRecord._4,
            categoryId = todoRecord._5
          ),
          options = categoryOptionRecords.map { categoryRecord =>
            CategoryOptionDTO(
              id   = categoryRecord._1,
              name = categoryRecord._2
            )
          }
        )
      }

    optionT.value
  }
}
object UpdateToDoPageQuery {
  case class Input(id: ToDo.Id)
  case class Output(toDo: ToDoDTO, options: Seq[CategoryOptionDTO])

  case class CategoryOptionDTO(id: ToDoCategory.Id, name: String)

  case class ToDoDTO(
    id:         ToDo.Id,
    title:      String,
    body:       String,
    state:      ToDo.Status,
    categoryId: ToDoCategory.Id
  )
}
