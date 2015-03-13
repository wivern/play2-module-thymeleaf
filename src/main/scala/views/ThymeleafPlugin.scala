package views

import org.thymeleaf.TemplateEngine
import org.thymeleaf.templateresolver.{PlayTemplateResolver, ServletContextTemplateResolver}
import play.api.{Application, Plugin}

/**
 * Created by vitaly on 05.03.15.
 */
class ThymeleafPlugin(app: Application) extends Plugin {

  val templateEngine = new TemplateEngine

  override def onStart(): Unit = {
    val resolver = new PlayTemplateResolver()
    resolver.setTemplateMode("XHTML")
    resolver.setPrefix("public/templates/")
    resolver.setSuffix(".html")
    resolver.setCacheTTLMs(3600000L)
    templateEngine.setTemplateResolver(resolver)
  }

  override def onStop(): Unit = {
    templateEngine.clearTemplateCache()
  }
}
