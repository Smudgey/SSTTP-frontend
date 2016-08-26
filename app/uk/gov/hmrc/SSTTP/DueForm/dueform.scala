package uk.gov.hmrc.SSTTP.DueForm


import play.api.data.Form
import play.api.data.Forms._
import models._
import uk.gov.hmrc.SSTTP.models.models.DueFormInformation

/**
  * Created by MacZ on 26/08/2016.
  */
object dueform {
  val form = Form(
    tuple(
      "date" -> nonEmptyText,
      "cleared" -> nonEmptyText
    )
  )
}
