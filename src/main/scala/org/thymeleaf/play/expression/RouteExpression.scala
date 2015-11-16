/*
Copyright 2015 Vitaly Koulakov

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package org.thymeleaf.play.expression


import org.thymeleaf.Configuration
import org.thymeleaf.context.IProcessingContext
import org.thymeleaf.standard.expression.{IStandardExpression, StandardExpressionExecutionContext}
import play.api.Play

import scala.reflect.runtime.{universe => ru}

case class RouteExpression(route : String, params : List[IStandardExpression] = null) extends IStandardExpression{

  override def execute(configuration: Configuration, processingContext: IProcessingContext): AnyRef = {
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val index = route.lastIndexOf(".")
    val clsName = "controllers.routes" //route.substring(1, index).replace("routes", "controllers") + "$"
    val methodName = route.substring(index+1)
    val cls = Play.current.classloader.loadClass(clsName)
//    val cls = Class.forName(clsName)
//    val router = cls.newInstance() //.asInstanceOf[Router.Routes]
    val assets = cls.getField("Assets").get(null)
    val methods = assets.getClass.getDeclaredMethods
    var args:Array[Object] = Array[Object]()
    if (params != null){ args = params.map( _.execute(configuration, processingContext) ).toArray }
//    val methods = router.getClass.getMethods()
    methods.find( _.getName == methodName) match {
      case Some(m) => m.invoke(assets, args: _*)
      case None => throw new Exception(s"Failed to execute route ${route}")
    }
  }

  override def execute(configuration: Configuration, processingContext: IProcessingContext, expContext: StandardExpressionExecutionContext): AnyRef = ???

  override def getStringRepresentation: String = s"@$route" + params match {
    case ".+" => "(" + params + ")"
    case null => ""
  }
}
