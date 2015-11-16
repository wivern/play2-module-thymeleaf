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

import org.thymeleaf.context.Context
import play.api.Play
import play.api.mvc.Request

/**
 * @author VKoulakov
 * @since 12.03.2015 19:34
 */
trait ThymeleafModule {
  def view(templateName: String, variables:Map[String, _] = Map()): String = {
    val templateEngine = Play.current.plugin[ThymeleafPlugin].map(_.templateEngine).orElse(sys.error("Thymeleaf plugin was not resolved"))
    val context = new Context()
    variables.foreach{case(name, value) => context.setVariable(name, value)}
    templateEngine.get.process(templateName, context)
  }
}
