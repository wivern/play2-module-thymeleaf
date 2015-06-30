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

  def string : Parser[IStandardExpression] = stringLiteral ^^ { s => StringLiteralExpression(s) }

  def parens : Parser[List[IStandardExpression]] = "(" ~> rep1sep(expr, ",") <~ ")"  //^^ { v => ExpressionListExpression(v) }
//  def variable : Parser[IStandardExpression] = """[\\w\\.]+""".r ^^{ s => new VariableExpression( s.toString ) }
  def route : Parser[RouteExpression] = "@routes.[\\w+\\.]+".r ~ opt(parens) ^^ {
      case s ~ None => new RouteExpression(s.toString)
      case s ~ a => new RouteExpression(s.toString, a.get)
  }

  def expr : Parser[IStandardExpression] = boolean | string

  def expression : Parser[IStandardExpression] = route | expr

  override def parseExpression(configuration: Configuration, processingContext: IProcessingContext, input: String): IStandardExpression = parseAll(expression, input) match {
    case Success(result, _) => result
    case NoSuccess(msg, next) =>  throw new IllegalArgumentException(
      "Could not parse '" + input + "' near '" + next.pos.longString + ": " + msg)
  }

}
