package lib.usecase.query;

import javax.inject._
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.ToDoCategory
import lib.persistence.table.ToDoCategoryTable
import lib.usecase.query.GetAllCategoryOptionsQuery.{CategoryOption, Output, query}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetAllCategoryOptionsQuery @Inject() (
  @Named("slave") slave: Database,
)(implicit ec: ExecutionContext) {
  def run(): Future[Output] = {
    val dbio = query.result.map { records =>
      Output(
        records.map { record =>
          CategoryOption(
            id = record._1,
            name = record._2
          )
        }
      )
    }

    slave.run(dbio)
  }
}
object GetAllCategoryOptionsQuery {
  val query = for {
    categoryTable <- ToDoCategoryTable.query
  } yield {
    (categoryTable.id, categoryTable.name)
  }

  case class Output(options: Seq[CategoryOption])

  case class CategoryOption(id: ToDoCategory.Id, name: String)
}
