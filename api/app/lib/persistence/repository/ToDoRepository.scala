package lib.persistence.repository

import ixias.slick.SlickRepository

import javax.inject.{ Inject, Named, Singleton }
import ixias.slick.jdbc.MySQLProfile.api._
import lib.model.ToDo
import lib.persistence.table.ToDoTable

import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class ToDoRepository @Inject() (
  @Named("master") master: Database,
  @Named("slave") slave:   Database,
)(implicit val ec:         ExecutionContext) extends SlickRepository[ToDo.Id, ToDo] {
  def getById(id: ToDo.Id): Future[Option[ToDo]] = {
    val query = ToDoTable.query.filter(_.id === id)
    val dbio = query.result.headOption

    slave.run(dbio)
  }

  def add(user: ToDo#WithNoId): Future[ToDo.Id] = {
    val dbio = ToDoTable.query returning ToDoTable.query.map(_.id) += user.v
    master.run(dbio)
  }

  def update(entity: ToDo#EmbeddedId): Future[Unit] = {
    val query = ToDoTable.query.filter(_.id === entity.id)
    val dbio = query.update(entity.v)

    for {
      _ <- master.run(dbio)
    } yield {
      ()
    }
  }

  def delete(entity: ToDo#EmbeddedId): Future[Unit] = {
    val query = ToDoTable.query.filter(_.id === entity.id)
    val dbio = query.delete.map { _ => () }

    master.run(dbio)
  }
}
