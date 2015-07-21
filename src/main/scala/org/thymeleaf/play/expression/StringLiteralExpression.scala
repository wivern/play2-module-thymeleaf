package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{StandardExpressionExecutionContext, IStandardExpression}

/**
 * Created by vitaly on 23.06.15.
 */
case class StringLiteralExpression(value: String) extends IStandardExpression{
  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = value

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = value

  override def getStringRepresentation: String = "\"" + value + "\""
}
