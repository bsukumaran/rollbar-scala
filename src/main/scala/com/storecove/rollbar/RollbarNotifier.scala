package com.storecove.rollbar

import org.json4s.JObject

import scala.collection.mutable
import scala.concurrent.Future

/**
 * Created by acidghost on 06/06/15.
 */
trait RollbarNotifier {

    protected val notifierName = "rollbar-scala"
    protected val notifierVersion = "0.0.1"

    protected var url: String = _
    protected var apiKey: String = _
    protected var environment: String = _
    protected var language: String = _
    protected var platform: String = _

    protected def log(x: Any) = println(s"[${classOf[RollbarNotifier]}] - $x")

    def getUrl: String = url
    def getApiKey: String = apiKey
    def getEnvironment: String = environment
    def getLanguage: String = language
    def getPlatform: String = platform

    def setUrl(url: String): Unit = this.url = url
    def setApiKey(apiKey: String): Unit = this.apiKey = apiKey
    def setEnvironment(environment: String): Unit = this.environment = environment
    def setLanguage(language: String): Unit = this.language = language
    def setPlatform(platform: String): Unit = this.platform = platform

    def notify(level: String, message: String, throwable: Option[Throwable], mdc: mutable.Map[String, String]): Future[String]

    protected[rollbar] def buildPayload(level: String, message: String, throwable: Option[Throwable], mdc: mutable.Map[String, String]): JObject

}
