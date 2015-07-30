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

package org.thymeleaf.resourceresolver

import java.io.{IOException, InputStream}

import org.thymeleaf.TemplateProcessingParameters
import org.thymeleaf.util.Validate
import play.api.Play

/**
 * @author VKoulakov
 * @since 13.03.2015 14:05
 */
class PlayResourceResolver extends IResourceResolver{
  override def getName: String = "PLAY"

  override def getResourceAsStream(templateProcessingParameters: TemplateProcessingParameters, resourceName: String): InputStream = {
    Validate.notNull(templateProcessingParameters, "Template Processing Parameters cannot be null")
    Validate.notNull(resourceName, "Resource name cannot be null")
    val resource = Play.current.resourceAsStream(resourceName)
    resource match {
      case Some(value) => resource.get
      case None => throw new IOException("Resource " + resourceName + " not found")
    }
  }
}
