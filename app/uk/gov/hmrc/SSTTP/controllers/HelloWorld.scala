package uk.gov.hmrc.SSTTP.controllers

import play.api.libs.ws.{WS, WSResponse}
import uk.gov.hmrc.SSTTP.DueForm._

import scala.concurrent.Future

import play.api.mvc.Action
import uk.gov.hmrc.SSTTP.connectors.BusinessRegistrationConnector
import uk.gov.hmrc.SSTTP.helloworld.html.hello_world
//import uk.gov.hmrc.SSTTP.html.helloworld.hello_world
import uk.gov.hmrc.SSTTP.models._
import uk.gov.hmrc.play.health.routes

import scala.concurrent.Future
import play.api.libs.json._
import play.api.libs.functional.syntax._


import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import org.joda.time.{DateTime, Days, LocalDate}

import uk.gov.hmrc.time.DateTimeUtils

import scala.concurrent.Future

object HelloWorld extends HelloWorld {
  val businessRegConnector = BusinessRegistrationConnector
}

trait HelloWorld extends FrontendController with Controller{
  /*val helloWorld = Action.async { implicit request =>
		Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world()))
  }*/

  val businessRegConnector : BusinessRegistrationConnector



  val show = Action.async { implicit request =>

    val detailForm = dueform.form.fill(new DueFormInformation("","",""))
    Future.successful(Ok(hello_world(detailForm)))//hhh
  }

  def submit = Action.async{ implicit request =>

    dueform.form.bindFromRequest.fold(
        success = {
          Calculate =>
            BusinessRegistrationConnector.submitUserDetails(Calculate).map {
              resultFromBackEnd =>
               Ok("it worked, value from backe ned was: " + resultFromBackEnd)
            }
        },
      hasErrors = {
        formWithErrors =>
          Future.successful(BadRequest(hello_world(formWithErrors)))
        }
    )
  }

  def submit2 = Action.async { implicit request =>
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
