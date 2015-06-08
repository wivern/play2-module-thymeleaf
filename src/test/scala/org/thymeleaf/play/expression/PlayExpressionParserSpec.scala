package org.thymeleaf.play.expression

import org.scalatest.{FlatSpec, Matchers}
import org.thymeleaf.standard.expression.BooleanTokenExpression

/**
 * Created by vitaly on 16.05.15.
 */
class PlayExpressionParserSpec extends FlatSpec with Matchers{

  "The parser" should "parse boolean" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "true") == new ThBooleanExpression(true))
  }

/*
  "The parser" should "parse route" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "routes.Application.start") == new RouteExpression("routes.Application.start"))
  }
*/


/*
  "The parser" should "parse a method" in {

    assert(new PlayExpressionParser().parseExpression(null, null, "routes.Application.index") == "routes.Application.index")
  } */

  "The parser" should "parse a link" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "@routes.Application.index1")  == LinkExpression(new RouteExpression("routes.Application.index1")))
  }

  it should "throw IllegalArgumentException when bad route" in {
    intercept[IllegalArgumentException]{
      new PlayExpressionParser().parseExpression(null, null, "@routes")
    }
  }


}
