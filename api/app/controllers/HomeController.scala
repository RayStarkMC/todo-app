/**
  * to do sample project
  */

package controllers

import model.ViewValueHome
import model.common.ViewValueCommon
import play.api.mvc._

import javax.inject._

@Singleton
class HomeController @Inject() (
  val controllerComponents: ControllerComponents,
) extends BaseController {

  def index(): Action[AnyContent] = Action {
    val vv = ViewValueHome(
      vvc = ViewValueCommon(
        title  = "Home",
        cssSrc = Seq("main.css"),
        jsSrc  = Seq("main.js")
      )
    )
    Ok(views.html.Home(vv))
  }
}
