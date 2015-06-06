package org.thymeleaf.play.expression

import ognl.{OgnlContext, BooleanExpression}
import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{BooleanTokenExpression, VariableExpression, IStandardExpression, IStandardExpressionParser}

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * @author VKoulakov
 * @since 20.03.2015 17:29
 */
class PlayExpressionParser extends JavaTokenParsers with IStandardExpressionParser{

  def boolean : Parser[IStandardExpression] = ("true" | "false") ^^ { s => new ThBooleanExpression( s.toLowerCase.equals("true")) }

//  def variable : Parser[IStandardExpression] = """[\\w\\.]+""".r ^^{ s => new VariableExpression( s.toString ) }

  def link : Parser[IStandardExpression] = "@\\s+".r ^^ { s => new LinkExpression(s.toString) }

  override def parseExpression(configuration: Configuration, processingContext: IProcessingContext, input: String): IStandardExpression = parseAll(boolean, input) match {
    case Success(result, _) => result
    case NoSuccess(msg, next) =>  throw new IllegalArgumentException(
      "Could not parse '" + input + "' near '" + next.pos.longString + ": " + msg)
  }
}
