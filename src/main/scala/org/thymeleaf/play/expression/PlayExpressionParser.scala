package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{IStandardExpression, IStandardExpressionParser}

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * @author VKoulakov
 * @since 20.03.2015 17:29
 */
class PlayExpressionParser extends JavaTokenParsers with IStandardExpressionParser{

  def link : Parser[IStandardExpression] = "@" ~ stringLiteral ^^ {
    case (x~string) => LinkExpression(string)
  }

  override def parseExpression(configuration: Configuration, processingContext: IProcessingContext, input: String): IStandardExpression = parseAll(link, input) match {
    case Success(result, _) => result
    case NoSuccess(msg, next) =>  throw new IllegalArgumentException(
      "Could not parse '" + input + "' near '" + next.pos.longString + ": " + msg)
  }
}