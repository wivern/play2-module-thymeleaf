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

package org.thymeleaf.messageresolver

import java.util.Locale

import org.thymeleaf.Arguments
import play.api.Play
import play.api.i18n.{Lang, Messages}

/**
 * Created by vitaly on 18.03.15.
 */
class PlayMessageResolver extends AbstractMessageResolver{

  override def resolveMessage(arguments: Arguments, key: String, messageParameters: Array[AnyRef]): MessageResolution = {
    val locale = arguments.getContext.getLocale
    checkInitialized()
    implicit val messages : Messages = Messages.Implicits.applicationMessages(Lang(locale.toLanguageTag), Play.current)
    val message = Option(Messages(key, messageParameters))
    message match {
      case Some(value) => new MessageResolution(message.get)
      case None => null
    }
  }

}
