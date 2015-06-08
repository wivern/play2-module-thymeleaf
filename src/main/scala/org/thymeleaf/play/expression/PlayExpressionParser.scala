package org.thymeleaf.play.expression

import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression._

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * @author VKoulakov
 * @since 20.03.2015 17:29
 */
class PlayExpressionParser extends JavaTokenParsers with IStandardExpressionParser{

  def boolean : Parser[ThBooleanExpression] = ("true" | "false") ^^ { s => new ThBooleanExpression( s.toLowerCase.equals("true")) }

  def arguments : Parser[String] = "(" ~> (stringLiteral+) ~ (("," ~> stringLiteral)?) <~ ")" ^^ { s => s.toString()}
//  def variable : Parser[IStandardExpression] = """[\\w\\.]+""".r ^^{ s => new VariableExpression( s.toString ) }
  def route : Parser[RouteExpression] = "routes[\\.\\w]+".r ^^ { s => new RouteExpression(s.toString) }

  def link : Parser[IStandardExpression] = "@" ~ route ^^ { case "@" ~ routes => new LinkExpression(routes) }

  def expression : Parser[IStandardExpression] = link | boolean

  override def parseExpression(configuration: Configuration, processingContext: IProcessingContext, input: String): IStandardExpression = parseAll(expression, input) match {
    case Success(result, _) => result
    case NoSuccess(msg, next) =>  throw new IllegalArgumentException(
      "Could not parse '" + input + "' near '" + next.pos.longString + ": " + msg)
  }

}
