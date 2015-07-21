package org.thymeleaf.play.expression


import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{StandardExpressionExecutionContext, IStandardExpression}
import scala.reflect.runtime.{universe => ru}
import play.core.Router

/**
 * Created by vitaly on 08.06.15.
 */
case class RouteExpression(route : String, params : List[IStandardExpression] = null) extends IStandardExpression{

  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = {
    import java.lang.reflect.Method
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val index = route.lastIndexOf(".")
    val clsName = "controllers.routes" //route.substring(1, index).replace("routes", "controllers") + "$"
    val methodName = route.substring(index+1)
    val cls = Class.forName(clsName)
//    val router = cls.newInstance() //.asInstanceOf[Router.Routes]
    val assets = cls.getField("Assets").get(null)
    val methods = assets.getClass.getDeclaredMethods
    var args:Array[Object] = Array[Object]()
    if (params != null){ args = params.map( _.execute(configuration, processingContext) ).toArray }
//    val methods = router.getClass.getMethods()
    methods.find( _.getName == methodName) match {
      case Some(m) => m.invoke(assets, args)
      case None => throw new Exception(s"Failed to execute route ${route}")
    }
  }

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???

  override def getStringRepresentation: String = s"@$route" + params match {
    case ".+" => "(" + params + ")"
    case null => ""
  }
}
