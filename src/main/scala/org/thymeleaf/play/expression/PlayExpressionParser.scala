package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{IStandardExpression, IStandardExpressionParser}

/**
 * @author VKoulakov
 * @since 20.03.2015 17:29
 */
class PlayExpressionParser extends IStandardExpressionParser{
  override def parseExpression(configuration: Configuration, processingContext: IProcessingContext, input: String): IStandardExpression = ???
}
