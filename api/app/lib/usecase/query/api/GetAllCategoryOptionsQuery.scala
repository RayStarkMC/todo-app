package lib.usecase.query.api

import controllers.api.query.category.ApiGetAllCategoryOptionsModel._
import lib.persistence.table.ToDoCategoryTable
import slick.jdbc.JdbcBackend.Database
import ixias.slick.jdbc.MySQLProfile.api._

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetAllCategoryOptionsQuery @Inject() (
  @Named("slave") slave: Database
)(
  implicit ec:           ExecutionContext
) {
  def run(): Future[JsResponse] = {
    val query = for {
      toDoCategory <- ToDoCategoryTable.query
    } yield {
      (toDoCategory.id, toDoCategory.name)
    }

    val dbio = query.result

    slave.run(dbio).map { records =>
      JsResponse(
        list = records.map { record =>
           JsCategoryOption(
             id = record._1,
             name = record._2
           )
        }
      )
    }
  }
}
