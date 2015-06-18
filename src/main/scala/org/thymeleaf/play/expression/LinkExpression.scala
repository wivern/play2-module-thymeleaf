package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression._


/**
 * @author VKoulakov
 * @since 20.03.2015 19:32
 */
case class LinkExpression (value: RouteExpression) extends IStandardExpression{
  override def getStringRepresentation: String = "@" + value

  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = {
//    val cl = Class.forName("controllers.routes.Assets")
//    cl.getMethod("at", classOf[String], classOf[String])
    "/stylesheets/main.css"
//    controllers.routes.Assets.at("stylesheets", "main.css")
  }

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???
}

