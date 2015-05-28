package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression._

/**
 * @author VKoulakov
 * @since 20.03.2015 19:32
 */
case class LinkExpression (value: String) extends IStandardExpression{
  override def getStringRepresentation: String = "@" + value

  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = ???

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???
}

