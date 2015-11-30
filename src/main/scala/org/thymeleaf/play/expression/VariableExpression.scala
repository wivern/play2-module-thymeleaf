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

import java.util.Collections

import ognl.{OgnlRuntime, OgnlContext, Ognl}
import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.play.converter.OgnlTypeConverter
import org.thymeleaf.play.ognl.OgnlObjectPropertyAccessor
import org.thymeleaf.standard.expression.{StandardExpressionExecutionContext, IStandardExpression}

case class VariableExpression(expr: String) extends IStandardExpression {

  def apply(variable : String) = new VariableExpression(variable)

  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = {
    var expressionTree: AnyRef = null
    expressionTree = Ognl.parseExpression(expr)

    val evaluationRoot: AnyRef = processingContext.getExpressionSelectionEvaluationRoot
    var contextVariables: java.util.Map[String, AnyRef] = processingContext.getExpressionObjects
    val additionalContextVariables: java.util.Map[String, AnyRef] = computeAdditionalContextVariables(processingContext)
    if (additionalContextVariables != null) {
      contextVariables.putAll(additionalContextVariables)
    }

    val context: OgnlContext = new OgnlContext(contextVariables)
    context.setTypeConverter(new OgnlTypeConverter)
    OgnlRuntime.setPropertyAccessor(classOf[Object], new OgnlObjectPropertyAccessor)
    val result: AnyRef = Ognl.getValue(expressionTree, context, evaluationRoot)

    result match {
      case v : AnyRef  => v
      case None => ""
    }
  }

  protected def computeAdditionalContextVariables(processingContext: IProcessingContext): java.util.Map[String, AnyRef] = Collections.emptyMap()

  def evaluate(processingContext: IProcessingContext, variable: String) : AnyRef = {
    if (processingContext.hasLocalVariable(variable)){
      processingContext.getLocalVariable(variable)
    } else {
      val value = processingContext.getContext.getVariables.get(variable)
      value match {
        case v : AnyRef => v
        case None => processingContext.getExpressionObjects.get (variable)
      }
    }
  }

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???

  override def getStringRepresentation: String = s"{$expr}"
}
