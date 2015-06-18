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
