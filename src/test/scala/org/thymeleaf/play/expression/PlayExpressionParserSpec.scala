/*
 * Copyright 2015 Vitaly Koulakov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.thymeleaf.play.expression

import org.scalatest.{FlatSpec, Matchers}
import org.thymeleaf.context.{ProcessingContext, Context}

class PlayExpressionParserSpec extends FlatSpec with Matchers{

  "The parser" should "parse boolean" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "true") == new ThBooleanExpression(true))
  }

  "The parser" should "parse single quoted string" in {
    assert(new PlayExpressionParser().parseExpression(null, null, "'string expression'") == new StringLiteralExpression("'string expression'"))
  }

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

  it should "parse a variable" in {
    val parser = new PlayExpressionParser
    assert(parser.parseExpression(null, null, "${var1}") == VariableExpression("var1"))
    assert(parser.parseExpression(null, null, "${var1.prop1}") == VariableExpression("var1.prop1"))
  }

  it should "proceed variable expression" in {
    val parser = new PlayExpressionParser
    val context = new Context()
    context.setVariable("var1", "this is value")
    context.setVariable("var2", 102)
    val expression = parser.parseExpression(null, new ProcessingContext(context), "${var1}")
    assert(expression.execute(null, new ProcessingContext(context)) == "this is value")

    val pc2 = new ProcessingContext(context)
    val expr2 = parser.parseExpression(null, pc2, "${var2}")
    assert(expr2.execute(null, pc2) == 102)
  }


}
