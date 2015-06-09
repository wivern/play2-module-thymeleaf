package views

import com.google.inject.Inject
import org.thymeleaf.TemplateEngine
import org.thymeleaf.messageresolver.PlayMessageResolver
import org.thymeleaf.play.PlayDialect
import org.thymeleaf.templateresolver.{PlayTemplateResolver, ServletContextTemplateResolver}
import play.api.{Application, Plugin}

/**
 * Created by vitaly on 05.03.15.
 */
class ThymeleafPlugin @Inject() (app: Application) extends Plugin {

  val templateEngine = new TemplateEngine

  override def onStart(): Unit = {
    val resolver = new PlayTemplateResolver()
    resolver.setTemplateMode("XHTML")
    resolver.setPrefix("public/templates/")
    resolver.setSuffix(".html")
    resolver.setCacheTTLMs(3600000L)
    resolver.setCharacterEncoding("UTF-8")
    resolver.setCacheable(false)
    templateEngine.setTemplateResolver(resolver)
    templateEngine.setMessageResolver(new PlayMessageResolver)
    templateEngine.addDialect(new PlayDialect)
  }

  override def onStop(): Unit = {
    templateEngine.clearTemplateCache()
  }
}
