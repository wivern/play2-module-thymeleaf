package views

import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import play.api.Play

/**
 * @author VKoulakov
 * @since 12.03.2015 19:34
 */
trait ThymeleafModule {
  def view(templateName: String, variables:Map[String, _] = Map()): String = {
    val templateEngine = Play.current.plugin[ThymeleafPlugin].map(_.templateEngine).orElse(sys.error("Thymeleaf plugin was not resolved"))
    val context = new Context()
    templateEngine.get.process(templateName, context)
  }
}
