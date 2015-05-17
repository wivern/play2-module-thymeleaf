package org.thymeleaf.play.expression

import org.thymeleaf.standard.expression.{Expression, AssignationSequence, IStandardExpression, SimpleExpression}

/**
 * @author VKoulakov
 * @since 20.03.2015 19:32
 */
case class LinkExpression private (base : IStandardExpression, parameters : AssignationSequence) extends SimpleExpression{
  override def getStringRepresentation: String = ???
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