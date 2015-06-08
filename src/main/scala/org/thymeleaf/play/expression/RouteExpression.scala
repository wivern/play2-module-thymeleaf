package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{StandardExpressionExecutionContext, IStandardExpression}

/**
 * Created by vitaly on 08.06.15.
 */
case class RouteExpression(route : String, params : List[String] = null) extends IStandardExpression{

  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = ???

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???

  override def getStringRepresentation: String = ???
}
