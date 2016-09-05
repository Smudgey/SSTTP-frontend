/*
package uk.gov.hmrc.SSTTP.controllers


import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, Result}
import play.api.mvc.BodyParsers.parse
import play.api.mvc.Results._
import uk.gov.hmrc.SSTTP.models.BetaUserInformationSubmit
import uk.gov.hmrc.play.microservice.controller.BaseController
import scala.concurrent.Future


object UserRegisterController extends UserRegisterController {

}

trait UserRegisterController {


  def RecievedFeedBack: Action[JsValue] = Action.async(parse.json) { implicit request =>
    withJsonBody[BetaUserInformationSubmit] {
      feedbackData =>
        recieve(feedbackData)
    }


    def recieve(details : BetaUserInformationSubmit) : Future[Result] = {
      .map(res => Created(Json.toJson(res)))
    }
  }
}
*/
