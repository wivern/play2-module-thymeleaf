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

object LinkExpression{

  val LINK_PATTERN = "^\\s*\\@\\{(.+?)\\}\\s*$".r

  def parseLink(input : String) : LinkExpression = {
    input match {
      case LINK_PATTERN(c) => {
//        val baseExpr = Expression.parse(c)
        null
      }
      case _ => null
    }
  }
}