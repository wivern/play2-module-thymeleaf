
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

package org.thymeleaf.templateresolver

import org.thymeleaf.exceptions.ConfigurationException
import org.thymeleaf.resourceresolver.{IResourceResolver, PlayResourceResolver}

/**
 * @author VKoulakov
 * @since 13.03.2015 14:13
 */
class PlayTemplateResolver private (resolver : IResourceResolver) extends TemplateResolver(){
  def this() = {
    this(new PlayResourceResolver)
    super.setResourceResolver(resolver)
  }

  override def setResourceResolver(resourceResolver: IResourceResolver): Unit = throw new ConfigurationException("Cannot set resource resolver")
}
