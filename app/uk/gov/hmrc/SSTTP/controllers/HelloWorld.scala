package uk.gov.hmrc.SSTTP.controllers

import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

import scala.concurrent.Future
import models.DueFormInformation
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc.Action
import uk.gov.hmrc.SSTTP.DueForm.dueform
import uk.gov.hmrc.SSTTP.models.models.DueFormInformation
import views.html.beta.{Confirmation, EnterYourDetails}

import scala.concurrent.Future


object HelloWorld extends HelloWorld

trait HelloWorld extends FrontendController {
  val helloWorld = Action.async { implicit request =>
		Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world()))
  }

  val show = Action.async { implicit request =>
    val detailsForm = dueform.form.fill(new DueFormInformation("",""))
    Future.successful(Ok(EnterYourDetails(detailsForm)))
  }
}
