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

package org.thymeleaf.play

import java.util

import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.dialect.{AbstractDialect, IExpressionEnhancingDialect}
import org.thymeleaf.play.expression.PlayExpressionParser
import org.thymeleaf.play.processor.PlayHrefAttrProcessor
import org.thymeleaf.processor.IProcessor
import org.thymeleaf.standard.expression.StandardExpressions
import play.api.Play

/**
 * Created by vitaly on 19.03.15.
 */
class PlayDialect extends AbstractDialect with IExpressionEnhancingDialect {

  val ROUTES_VARIABLE_NAME = "routes"

  val EXPRESSION_PARSER = new PlayExpressionParser

  //TODO not needed since processor
  override def getAdditionalExpressionObjects(processingContext: IProcessingContext): util.Map[String, AnyRef] = {
    val expressionObjects: util.Map[String, AnyRef] = new util.HashMap
    expressionObjects.put(ROUTES_VARIABLE_NAME, controllers.Assets)
    expressionObjects
  }


  override def getExecutionAttributes: util.Map[String, AnyRef] = {
    val executionAttributes = new util.HashMap[String, AnyRef]()
    executionAttributes.put(StandardExpressions.STANDARD_EXPRESSION_PARSER_ATTRIBUTE_NAME, this.EXPRESSION_PARSER)
    executionAttributes
  }

  override def getProcessors: util.Set[IProcessor] = {
    val processors = new util.HashSet[IProcessor]()
    processors.add(new PlayHrefAttrProcessor)
    processors
  }

  override def getPrefix: String = "th"

}
