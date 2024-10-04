package lib.usecase.command

import javax.inject.{ Inject, Singleton }
import lib.model.{ ToDo, ToDoCategory }
import lib.model.ToDo.Status.TO_DO
import lib.persistence.repository.ToDoRepository
import lib.usecase.command.CreateToDoCommand.Input

import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class CreateToDoCommand @Inject() (
  repository:  ToDoRepository
)(
  implicit ec: ExecutionContext
) {
  def run(input: Input): Future[Unit] = {
    val newToDo = ToDo(
      id         = None,
      title      = input.title,
      body       = input.body,
      categoryId = input.categoryId,
      state      = TO_DO
    ).toWithNoId

    for {
      _ <- repository.add(newToDo)
    } yield {
      ()
    }
  }
}

object CreateToDoCommand {
  case class Input(
    title:      String,
    body:       String,
    categoryId: ToDoCategory.Id,
  )
}
