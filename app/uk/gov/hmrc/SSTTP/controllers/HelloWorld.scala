package uk.gov.hmrc.SSTTP.controllers

import uk.gov.hmrc.SSTTP.DueForm._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

import scala.concurrent.Future
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc.Action
import uk.gov.hmrc.SSTTP.connectors.BusinessRegistrationConnector
import uk.gov.hmrc.SSTTP.helloworld.html.hello_world
import uk.gov.hmrc.SSTTP.models.DueFormInformation
import uk.gov.hmrc.play.health.routes

import scala.concurrent.Future



object HelloWorld extends HelloWorld {
  val businessRegConnector = BusinessRegistrationConnector
}

trait HelloWorld extends FrontendController {
  /*val helloWorld = Action.async { implicit request =>
		Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world()))
  }*/
  val businessRegConnector : BusinessRegistrationConnector

  val show = Action.async { implicit request =>
    val detailForm = dueform.form.fill(new DueFormInformation("",""))
    Future.successful(Ok(hello_world(detailForm)))
  }

  val submit = Action.async { implicit request =>
    dueform.form.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(BadRequest(EnterYourDetails(formWithErrors)))
      },
      userDetails => {
        BusinessRegistrationConnector.submitUserDetails(userDetails).flatMap {
          _ => Future.successful(Redirect(routes.HelloWorld.showConfirmation()))
        }
      }
    )
  }

  val showConfirmation = Action.async { implicit request =>
    Future.successful(Ok(Confirmation()))
  }
}
