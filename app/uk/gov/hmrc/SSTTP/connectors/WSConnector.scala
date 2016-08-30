package uk.gov.hmrc.SSTTP.connectors

import org.joda.time.DateTime
import play.api.Play.current
import play.api.libs.json.Json
import play.api.libs.ws._
import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
import uk.gov.hmrc.SSTTP.models.DueFormInformation

import scala.concurrent.{ExecutionContext, Future}
import uk.gov.hmrc.play.config.ServicesConfig

object WSConnector extends WSConnector with ServicesConfig {
  override val taxToPayUrl = baseUrl("SSTTP")
}

trait WSConnector {
  val taxToPayUrl: String

  def url(path: String) = s"$taxToPayUrl$path"

  def sendTTP(dueDate: DateTime, numberDays: Int, debtAmount: Double, startDate: DateTime, paymentFrequency: String)
                 (implicit ec: ExecutionContext): Unit = {
    val data = Json.obj(
      "dueDate" -> dueDate,
      "numberDays" -> numberDays.toString,
      "debtAmount" -> debtAmount.toString,
      "startDate" -> startDate,
      "paymentFrequency" -> paymentFrequency
    )
    val futureResponse: Future[WSResponse] = WS.url(url("/TaxToPayData")).post(data)
  }

  def sendCleared(dfi: DueFormInformation)(implicit ec: ExecutionContext): Unit = {
    val data = Json.obj(
      "date" -> dfi.date,
      "cleared" -> dfi.cleared
    )
    val futureResponse: Future[WSResponse] = WS.url(url("")).post(data)
  }

  def getCleared()(implicit ec: ExecutionContext): Future[DueFormInformation] = {
    val holder: WSRequestHolder = WS.url(url("/TaxToPayData"))
    val futureResponse: Future[DueFormInformation] = holder.get().map {
      response =>
        (response.json \ "DueFormInformation").as[DueFormInformation]
    }
    futureResponse
  }
}