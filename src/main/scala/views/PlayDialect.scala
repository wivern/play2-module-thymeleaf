package views

import java.util

import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.dialect.IExpressionEnhancingDialect
import org.thymeleaf.doctype.resolution.IDocTypeResolutionEntry
import org.thymeleaf.doctype.translation.IDocTypeTranslation
import org.thymeleaf.processor.IProcessor
import play.api.Routes

/**
 * Created by vitaly on 19.03.15.
 */
class PlayDialect extends IExpressionEnhancingDialect{
  override def getAdditionalExpressionObjects(processingContext: IProcessingContext): util.Map[String, AnyRef] = Map("routes" -> Routes)

  override def getDocTypeResolutionEntries: util.Set[IDocTypeResolutionEntry] = ???

  override def getExecutionAttributes: util.Map[String, AnyRef] = ???

  override def getDocTypeTranslations: util.Set[IDocTypeTranslation] = ???

  override def getPrefix: String = ???

  override def getProcessors: util.Set[IProcessor] = ???
}
