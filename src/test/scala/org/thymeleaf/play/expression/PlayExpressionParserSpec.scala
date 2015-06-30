package org.thymeleaf.play.expression

import org.scalatest.{FlatSpec, Matchers}
import org.thymeleaf.standard.expression.BooleanTokenExpression

class PlayExpressionParserSpec extends FlatSpec with Matchers{

  "The parser" should "parse boolean" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "true") == new ThBooleanExpression(true))
  }


/*
  "The parser" should "parse a method" in {

    assert(new PlayExpressionParser().parseExpression(null, null, "routes.Application.index") == "routes.Application.index")
  } */

/*  "The parser" should "parse a parens" in {
    val parsed = new PlayExpressionParser().parseExpression(null, null, "(\"aa\",\"bb\")").asInstanceOf[ExpressionListExpression]
    assert(parsed.list == ExpressionListExpression(List(new StringLiteralExpression("\"aa\""), new StringLiteralExpression("\"bb\""))).list)
  }*/

  "The parser" should "parse a link" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "@routes.Application.index1")  == RouteExpression("@routes.Application.index1"))
  }

  "The parser" should "parse a link with parameters" in {
    val expr = new PlayExpressionParser().parseExpression(null, null, "@routes.Assets.at(\"public/stylesheets\",\"main.css\")")
    val expr2 = RouteExpression("@routes.Assets.at",
      List(StringLiteralExpression("\"public/stylesheets\""), StringLiteralExpression("\"main.css\"")))
    assert(expr == expr2)
  }

  it should "throw IllegalArgumentException when bad route" in {
    intercept[IllegalArgumentException]{
      new PlayExpressionParser().parseExpression(null, null, "@routes")
    }
  }


}
