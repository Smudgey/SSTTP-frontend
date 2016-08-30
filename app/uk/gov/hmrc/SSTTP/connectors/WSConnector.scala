package uk.gov.hmrc.SSTTP.connectors

/**
  * Created by MacZ on 30/08/2016.
  */

import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
import scala.concurrent.Future

object WSConnector {

  val holder: WSRequestHolder = WS.url("http://localhost:9000/hello-world/enter-your-details")

  val complexHolder: WSRequestHolder =
    holder.withHeaders("Accept" -> "application/json")
      .withRequestTimeout(10000)
      .withQueryString("search" -> "play")

  val futureResponse: Future[WSResponse] = complexHolder.get()
}
