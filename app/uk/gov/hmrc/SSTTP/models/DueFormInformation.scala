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

  package uk.gov.hmrc.SSTTP.models

  import java.time.temporal.TemporalAmount

  import org.joda.time.DateTime
  import play.api.libs.json.Json
  import uk.gov.hmrc.time.DateTimeUtils

  case class DueFormInformation( amount: Double,
                                 days : Int,
                                 interest : Double)//timeNeeded: Int)

  object DueFormInformation {
    implicit val format = Json.format[DueFormInformation]

    implicit def userDetailsCaptureToSubmit(details : DueFormInformation) : BetaUserInformationSubmit = {
      BetaUserInformationSubmit(
        details.amount,
        details.days,
        details.interest,
       // details.timeNeeded,//change to how long needed
        DateTimeUtils.now)
    }

  }


  case class BetaUserInformationSubmit(amount: Double,
                                        days : Int,
                                       interest: Double,
                                       //timeNeeded: Int,
                                       submissionTime : DateTime)

  object BetaUserInformationSubmit {
    implicit val format = Json.format[BetaUserInformationSubmit]
  }
