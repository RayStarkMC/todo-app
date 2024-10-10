package lib

import play.api.mvc.InjectedController

import scala.concurrent.ExecutionContext

trait AsyncBaseController extends InjectedController {
  protected implicit lazy val ec: ExecutionContext = controllerComponents.executionContext
}
