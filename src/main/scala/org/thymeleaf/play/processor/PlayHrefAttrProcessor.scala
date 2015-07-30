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
