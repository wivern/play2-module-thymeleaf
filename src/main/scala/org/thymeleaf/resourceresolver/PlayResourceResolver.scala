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
