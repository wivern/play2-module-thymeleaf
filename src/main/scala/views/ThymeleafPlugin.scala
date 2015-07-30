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

package views

import com.google.inject.Inject
import org.thymeleaf.TemplateEngine
import org.thymeleaf.messageresolver.PlayMessageResolver
import org.thymeleaf.play.PlayDialect
import org.thymeleaf.play.expression.PlayExpressionParser
import org.thymeleaf.standard.StandardDialect
import org.thymeleaf.templateresolver.{PlayTemplateResolver, ServletContextTemplateResolver}
import play.api.{Configuration, Environment, Application, Plugin}
import play.api.inject.{Binding, Module}

/**
 * Created by vitaly on 05.03.15.
 */


class ThymeleafEngineModule extends Module{
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = Seq(

  )
}

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
    val dialect = new StandardDialect
    dialect.setExpressionParser(new PlayExpressionParser)
    templateEngine.setDialect(dialect)
  }

  override def onStop(): Unit = {
    templateEngine.clearTemplateCache()
  }
}
