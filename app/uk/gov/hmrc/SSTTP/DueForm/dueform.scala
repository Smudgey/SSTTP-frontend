package uk.gov.hmrc.SSTTP.DueForm


import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.SSTTP.models.DueFormInformation


/**
  * Created by MacZ on 26/08/2016.
  */
object dueform {
  val form = Form(
    mapping(
      "date" -> nonEmptyText,
      "cleared" -> nonEmptyText
    )(DueFormInformation.apply)(DueFormInformation.unapply)
  )
}
