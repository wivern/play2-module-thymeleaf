package org.thymeleaf.play.expression

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by vitaly on 16.05.15.
 */
class PlayExpressionParserSpec extends FlatSpec with Matchers{

  "The parser" should "parse a link" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "@routes.Application.index")  == LinkExpression("routes.Application.index"))
  }

}