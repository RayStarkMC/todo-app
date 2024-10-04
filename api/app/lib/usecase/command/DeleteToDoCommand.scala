package lib.usecase.command

import cats.data.OptionT
import lib.model.ToDo
import lib.persistence.repository.ToDoRepository
import lib.usecase.command.DeleteToDoCommand.{ Input, Output }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class DeleteToDoCommand @Inject() (
  repository:  ToDoRepository
)(
  implicit ec: ExecutionContext
) {
  def run(input: Input): Future[Option[Output]] = {
    val optionT =
      for {
        toDo <- OptionT(repository.getById(input.id))
        _    <- OptionT.liftF(repository.delete(toDo.toEmbeddedId))
      } yield {
        Output()
      }

    optionT.value
  }
}

object DeleteToDoCommand {
  case class Input(id: ToDo.Id)
  case class Output()
}
