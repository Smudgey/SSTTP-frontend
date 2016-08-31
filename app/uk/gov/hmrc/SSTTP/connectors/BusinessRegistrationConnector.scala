
/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.SSTTP.connectors

import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.SSTTP.models.Response
import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.play.config.ServicesConfig
import uk.gov.hmrc.play.http._
import uk.gov.hmrc.SSTTP.models._
import uk.gov.hmrc.SSTTP.config.WSHttp

import scala.concurrent.Future


object BusinessRegistrationConnector extends BusinessRegistrationConnector with ServicesConfig {
  val businessRegUrl = baseUrl("SSTTP")
  val http = WSHttp
}

trait BusinessRegistrationConnector {

  val businessRegUrl: String
  val http: HttpGet with HttpPost

  def submitUserDetails(userInformation : BetaUserInformationSubmit)(implicit hc: HeaderCarrier) : Future[BetaUserInformationSubmit] = {
    val userJson = Json.toJson[BetaUserInformationSubmit](userInformation)
    http.POST[JsValue, BetaUserInformationSubmit](s"$businessRegUrl/SSTTP/InterestRateCalculator", userJson)
  }

 /* def getUserDetailsForTests(queryParameter : String)
                            (implicit hc : HeaderCarrier, rds : HttpReads[BetaUserInformationSubmit])
  : Future[Option[BetaUserInformationSubmit]] = {
    http.GET[Option[BetaUserInformationSubmit]](s"$businessRegUrl/business-registration/test-only/get-beta-user/$queryParameter")
  }

  def clearUsersForTests()(implicit hc : HeaderCarrier) : Future[Option[Response]] = {
    http.GET[Option[Response]](s"$businessRegUrl/business-registration/test-only/clear-beta-users")
  }*/
}
