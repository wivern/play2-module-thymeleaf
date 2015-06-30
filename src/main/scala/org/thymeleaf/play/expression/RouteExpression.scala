package org.thymeleaf.play.expression


import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{StandardExpressionExecutionContext, IStandardExpression}
import scala.reflect.runtime.{universe => ru}

/**
 * Created by vitaly on 08.06.15.
 */
case class RouteExpression(route : String, params : List[IStandardExpression] = null) extends IStandardExpression{

  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = {
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val cls = Class.forName(route)

    None
  }

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???

  override def getStringRepresentation: String = s"@$route" + params match {
    case Some(this.params) => "(" + params + ")"
    case None => ""
  }
}
