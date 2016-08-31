package uk.gov.hmrc.SSTTP.DueForm


import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.SSTTP.models.DueFormInformation
import play.api.data.format.Formats._

/**
  * Created by MacZ on 26/08/2016.
  */
object dueform {
  val form = Form(
    mapping(
      "amount" -> of[Double],
      "days" -> number,
      "interest" -> of[Double]
    )(DueFormInformation.apply)(DueFormInformation.unapply)
  )
}
