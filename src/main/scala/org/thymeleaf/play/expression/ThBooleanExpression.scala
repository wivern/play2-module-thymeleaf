package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{StandardExpressionExecutionContext, IStandardExpression}

/**
 * Created by vitaly on 07.06.15.
 */
case class ThBooleanExpression(value : Boolean) extends IStandardExpression{
  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = ???

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???

  override def getStringRepresentation: String = value.toString
}
