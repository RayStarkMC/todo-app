package lib

import play.api.mvc.MessagesInjectedController

import scala.concurrent.ExecutionContext

trait AsyncMessagesInjectedController extends MessagesInjectedController{
  implicit lazy val ec: ExecutionContext = controllerComponents.executionContext
}
