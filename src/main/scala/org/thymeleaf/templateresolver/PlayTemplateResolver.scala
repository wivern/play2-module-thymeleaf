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
