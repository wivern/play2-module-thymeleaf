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
