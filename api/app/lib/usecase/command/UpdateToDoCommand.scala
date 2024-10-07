package lib.usecase.command

import cats.data.OptionT
import lib.model.{ ToDo, ToDoCategory }
import lib.persistence.repository.ToDoRepository
import lib.usecase.command.UpdateToDoCommand.{ Input, Output }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class UpdateToDoCommand @Inject() (
  repository:  ToDoRepository
)(
  implicit ec: ExecutionContext
) {
  def run(input: Input): Future[Option[Output]] = {
    val optionT = for {
      todo        <- OptionT(repository.getById(input.id))
      modifiedToDo = todo.copy(
        categoryId = input.categoryId,
        title      = input.title,
        body       = input.body,
        state      = input.state
      )
      _ <- OptionT.liftF(repository.update(modifiedToDo.toEmbeddedId))
    } yield {
      Output()
    }
    optionT.value
  }
}

object UpdateToDoCommand {
  case class Input(
    id:         ToDo.Id,
    title:      String,
    body:       String,
    categoryId: ToDoCategory.Id,
    state:      ToDo.Status
  )
  case class Output()
}
