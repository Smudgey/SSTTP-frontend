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

package uk.gov.hmrc.SSTTP.validation

import uk.gov.hmrc.SSTTP.models.DueFormInformation
import play.api.data.Mapping
import play.api.data.validation.{Constraint, Invalid, Valid, ValidationError}
import play.api.data.Forms._
import play.api.i18n.Messages

object BetaUserInformationValidation {

  def firstNameChecker : Mapping[String] = {
    val firstNameConstraint : Constraint[String] = Constraint("constraints.firstName")({
      text =>
        val error = text match {
          case "" => Seq(ValidationError(Messages("page.recorddetails.EnterYourDetails.validate.first-name.empty")))
          case _ => Nil
        }
        if(error.isEmpty) Valid else Invalid(error)
    })
    text.verifying(firstNameConstraint)
  }

  def lastNameChecker : Mapping[String] = {
    val lastNameConstraint : Constraint[String] = Constraint("constraints.lastName")({
      text =>
        val error = text match {
          case "" => Seq(ValidationError(Messages("page.recorddetails.EnterYourDetails.validate.last-name.empty")))
          case _ => Nil
        }
        if(error.isEmpty) Valid else Invalid(error)
    })
    text.verifying(lastNameConstraint)
  }

  def phoneNumberCheck : Mapping[String] = {
    val validPhoneNumber =
      """^(?:(?:\(?(?:0(?:0|11)\)?[\s-]?\(?|\+)44\)?[\s-]?(?:\(?0\)?[\s-]?)?)|(?:\(?0))(?:(?:\d{5}\)?[\s-]?\d{4,5})|(?:\d{4}\)?[\s-]?(?:\d{5}|\d{3}[\s-]?\d{3}))|(?:\d{3}\)?[\s-]?\d{3}[\s-]?\d{3,4})|(?:\d{2}\)?[\s-]?\d{4}[\s-]?\d{4}))(?:[\s-]?(?:x|ext\.?|\#)\d{3,4})?$""".r

    val phoneConstraint : Constraint[String] = Constraint("constraints.phone")({
      text =>
        val error = text.isEmpty match {
          case true => Nil
          case false  => text match {
            case validPhoneNumber() => Nil
            case _ => Seq(ValidationError(Messages("page.recorddetails.EnterYourDetails.validate.phone.invalid")))
          }
        }

        if(error.isEmpty) Valid else Invalid(error)
    })
    text.verifying(phoneConstraint)
  }

  def emailChecker : Mapping[String] = {
    val validEmail = """[A-Za-z0-9\-_.]{1,126}@[A-Za-z0-9\-_.]{1,126}""".r
    val emailConstraint : Constraint[String] = Constraint("constraints.email")({
      text =>
        val error = text match {
          case validEmail() => Nil
          case "" => Seq(ValidationError(Messages("page.recorddetails.EnterYourDetails.validate.email.empty")))
          case _ => Seq(ValidationError(Messages("page.recorddetails.EnterYourDetails.validate.email.invalid")))
        }
        if(error.isEmpty) Valid else Invalid(error)
    })
    text().verifying(emailConstraint)
  }

  def confirmEmailChecker : Mapping[String] = {
    val validEmail = """[A-Za-z0-9\-_.]{1,126}@[A-Za-z0-9\-_.]{1,126}""".r
    val emailConstraint : Constraint[String] = Constraint("constraints.confirmEmail")({
      text =>
        val error = text match {
          case validEmail() => Nil
          case "" => Seq(ValidationError(Messages("page.recorddetails.EnterYourDetails.validate.confirm-email.empty")))
          case _ => Seq(ValidationError(Messages("page.recorddetails.EnterYourDetails.validate.confirm-email.invalid")))
        }
        if(error.isEmpty) Valid else Invalid(error)
    })
    text().verifying(emailConstraint)
  }



}
