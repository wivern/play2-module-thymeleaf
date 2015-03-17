package org.thymeleaf.messageresolver

import org.thymeleaf.Arguments
import play.api.i18n.{Lang, Messages}

/**
 * Created by vitaly on 18.03.15.
 */
class PlayMessageResolver extends AbstractMessageResolver{
  override def resolveMessage(arguments: Arguments, key: String, messageParameters: Array[AnyRef]): MessageResolution = {
    checkInitialized()
    val locale = arguments.getContext.getLocale
    val message = Option(Messages(key, messageParameters)(Lang("ru")))
    message match {
      case Some(value) => new MessageResolution(message.get)
      case None => null
    }
  }
}
