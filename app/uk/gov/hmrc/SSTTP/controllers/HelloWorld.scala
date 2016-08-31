package uk.gov.hmrc.SSTTP.controllers

import play.api.libs.ws.{WS, WSResponse}
import uk.gov.hmrc.SSTTP.DueForm._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import play.api.data.format.Formats._
import scala.concurrent.Future
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc.Action
import uk.gov.hmrc.SSTTP.connectors.BusinessRegistrationConnector
import uk.gov.hmrc.SSTTP.helloworld.html.hello_world
//import uk.gov.hmrc.SSTTP.html.helloworld.hello_world
import uk.gov.hmrc.SSTTP.models._
import uk.gov.hmrc.play.health.routes

import scala.concurrent.Future
import play.api.libs.json._


object HelloWorld extends HelloWorld {
 // val businessRegConnector = BusinessRegistrationConnector
}

trait HelloWorld extends FrontendController {
  /*val helloWorld = Action.async { implicit request =>
		Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world()))
  }*/

  //val businessRegConnector : BusinessRegistrationConnector

  val show = Action.async { implicit request =>
    val detailForm = dueform.form.fill(new DueFormInformation(0.0,0,0.0))
    Future.successful(Ok(hello_world(detailForm)))//hhh
  }

  val submit = Action.async { implicit request =>
    dueform.form.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(BadRequest(hello_world(formWithErrors)))
      },
      userDetails => {

        val data = Json.obj(
          "key1" -> "value1",
          "key2" -> "value2",
          "key3" -> "value3"
        )
        //val futureResponse: Future[WSResponse] = WS.url("http://localhost:9000/hello-world/enter-your-details").post(data)

        BusinessRegistrationConnector.submitUserDetails(userDetails).flatMap {
          _ => Future.successful(Redirect(routes.HelloWorld.show()))
        }
      }
    )
  }
/*
  val showConfirmation = Action.async { implicit request =>
    Future.successful(Ok(Confirmation()))
  }*/
}
