


package org.thymeleaf.play.processor

import org.thymeleaf.Arguments
import org.thymeleaf.dom.Element
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor.ModificationType
import org.thymeleaf.processor.attr.AbstractSingleAttributeModifierAttrProcessor

/**
 * @author VKoulakov
 * @since 20.03.2015 18:44
 */
object PlayHrefAttrProcessor {
  val ATTR_NAME = "href"
}

class PlayHrefAttrProcessor extends AbstractSingleAttributeModifierAttrProcessor(PlayHrefAttrProcessor.ATTR_NAME) {

  val ATTR_PRECEDENCE = 1000

  override def getTargetAttributeName(arguments: Arguments, element: Element, attributeName: String): String = PlayHrefAttrProcessor.ATTR_NAME

  override def getTargetAttributeValue(arguments: Arguments, element: Element, attributeName: String): String = {
    val attributeValue = element.getAttributeValue(attributeName)
    val configuration = arguments.getConfiguration
    null
  }

  override def removeAttributeIfEmpty(arguments: Arguments, element: Element, attributeName: String, newAttributeName: String): Boolean = false

  override def getModificationType(arguments: Arguments, element: Element, attributeName: String, newAttributeName: String): ModificationType = ModificationType.SUBSTITUTION

  override def recomputeProcessorsAfterExecution(arguments: Arguments, element: Element, attributeName: String): Boolean = false

  override def getPrecedence: Int = ATTR_PRECEDENCE
}
